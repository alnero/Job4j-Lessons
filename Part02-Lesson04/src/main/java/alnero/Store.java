package alnero;

public interface Store extends AutoCloseable {
    void init();
    Task add(Task task);
    Task addComment(Task task, Comment comment);
    Task findById(long taskId);
    Task[] findByName(String taskName);
    Task[] findAll();
    void update(Task task);
    void delete(Task task);
}
