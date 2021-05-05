package alnero;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class SqlTaskTracker implements Store {
    /** DB connection instance. **/
    private Connection connection;

    public SqlTaskTracker() {

    }

    public SqlTaskTracker(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void init() {
        try (InputStream in = SqlTaskTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Task add(Task task) {
        if (task == null) {
            return null;
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO task(name, description, create_date) VALUES (?, ?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, new Timestamp(task.getCreateDate().getTime()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.changeId(generatedKeys.getLong(1));
                }
            }
            for (Comment comment : task.getComments()) {
                this.addComment(task, comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task addComment(Task task, Comment comment) {
        if (task == null || comment == null) {
            return null;
        }
        long taskId = task.getId();
        if (this.findById(taskId) != null) {
            try (PreparedStatement statement =
                         connection.prepareStatement("INSERT INTO comment(content, create_date, task_id) VALUES (?, ?, ?)",
                                 Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, comment.getContent());
                statement.setTimestamp(2, new Timestamp(comment.getCreateDate().getTime()));
                statement.setLong(3, taskId);
                statement.execute();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comment.changeId(generatedKeys.getLong(1));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return task;
        }
        return task;
    }

    @Override
    public Task findById(long taskId) {
        Task result = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT "
                             + "t.id as task_id, "
                             + "t.name as task_name, "
                             + "t.description as task_description, "
                             + "t.create_date as task_create_date, "
                             + "c.id as comment_id, "
                             + "c.content as comment_content, "
                             + "c.create_date as comment_create_date "
                             + "FROM task t LEFT JOIN comment c ON t.id = c.task_id WHERE t.id = ?")) {
            statement.setLong(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (result == null) {
                        result = new Task();
                        result.changeId(resultSet.getLong("task_id"));
                        result.setName(resultSet.getString("task_name"));
                        result.setDescription(resultSet.getString("task_description"));
                        result.setCreateDate(new Date(resultSet.getTimestamp("task_create_date").getTime()));
                    }
                    long commentId = resultSet.getLong("comment_id");
                    if (commentId != 0) {
                        Comment comment = new Comment();
                        comment.changeId(commentId);
                        comment.setContent(resultSet.getString("comment_content"));
                        comment.setCreateDate(new Date(resultSet.getTimestamp("comment_create_date").getTime()));
                        result.addComment(comment);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Task[] findByName(String taskName) {
        if (taskName == null) {
            return null;
        }
        Map<Long, Task> resultMap = new HashMap<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT "
                             + "t.id as task_id, "
                             + "t.name as task_name, "
                             + "t.description as task_description, "
                             + "t.create_date as task_create_date, "
                             + "c.id as comment_id, "
                             + "c.content as comment_content, "
                             + "c.create_date as comment_create_date "
                             + "FROM task t LEFT JOIN comment c ON t.id = c.task_id WHERE t.name LIKE ?")) {
            statement.setString(1, "%" + taskName + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = null;
                    long taskId = resultSet.getLong("task_id");
                    if (!resultMap.containsKey(taskId)) {
                        task = new Task();
                        task.changeId(taskId);
                        task.setName(resultSet.getString("task_name"));
                        task.setDescription(resultSet.getString("task_description"));
                        task.setCreateDate(new Date(resultSet.getTimestamp("task_create_date").getTime()));
                        resultMap.put(taskId, task);
                    } else {
                        task = resultMap.get(taskId);
                    }
                    long commentId = resultSet.getLong("comment_id");
                    if (commentId != 0) {
                        Comment comment = new Comment();
                        comment.changeId(commentId);
                        comment.setContent(resultSet.getString("comment_content"));
                        comment.setCreateDate(new Date(resultSet.getTimestamp("comment_create_date").getTime()));
                        task.addComment(comment);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap.values().toArray(new Task[0]);
    }

    @Override
    public Task[] findAll() {
        Map<Long, Task> resultMap = new HashMap<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT "
                             + "t.id as task_id, "
                             + "t.name as task_name, "
                             + "t.description as task_description, "
                             + "t.create_date as task_create_date, "
                             + "c.id as comment_id, "
                             + "c.content as comment_content, "
                             + "c.create_date as comment_create_date "
                             + "FROM task t LEFT JOIN comment c ON t.id = c.task_id")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = null;
                    long taskId = resultSet.getLong("task_id");
                    if (!resultMap.containsKey(taskId)) {
                        task = new Task();
                        task.changeId(taskId);
                        task.setName(resultSet.getString("task_name"));
                        task.setDescription(resultSet.getString("task_description"));
                        task.setCreateDate(new Date(resultSet.getTimestamp("task_create_date").getTime()));
                        resultMap.put(taskId, task);
                    } else {
                        task = resultMap.get(taskId);
                    }
                    long commentId = resultSet.getLong("comment_id");
                    if (commentId != 0) {
                        Comment comment = new Comment();
                        comment.changeId(commentId);
                        comment.setContent(resultSet.getString("comment_content"));
                        comment.setCreateDate(new Date(resultSet.getTimestamp("comment_create_date").getTime()));
                        task.addComment(comment);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap.values().toArray(new Task[0]);
    }

    @Override
    public void update(Task task) {
        if (task == null) {
            return;
        }
        long taskId = task.getId();
        Comment[] comments = task.getComments();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE task SET name = ?, description = ?, create_date = ? WHERE id = ?")) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, new Timestamp(task.getCreateDate().getTime()));
            statement.setLong(4, taskId);
            statement.executeUpdate();
            if (comments.length != 0) {
                for (Comment comment : comments) {
                    this.addComment(task, comment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Task task) {
        if (task == null) {
            return;
        }
        long taskId = task.getId();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM comment c WHERE c.task_id = ?; DELETE FROM task t WHERE t.id = ?")) {
            statement.setLong(1, taskId);
            statement.setLong(2, taskId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
