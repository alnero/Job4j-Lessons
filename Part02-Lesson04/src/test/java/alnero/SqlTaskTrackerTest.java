package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Testing methods in SqlTaskTracker class.
 */
public class SqlTaskTrackerTest {
    /** Common sql task tracker object for test. */
    private SqlTaskTracker sqlTaskTracker;

    /** Create common object and init DB connection. */
    @Before
    public void init() {
        this.sqlTaskTracker = new SqlTaskTracker();
        this.sqlTaskTracker.init();
        Task[] allTasksInTracker = sqlTaskTracker.findAll();
        for (Task task : allTasksInTracker) {
            sqlTaskTracker.delete(task);
        }
    }

    /**
     * Test that when we add task to a tracker that task is returned.
     */
    @Test
    public void whenAddOneTaskToTrackerThenSameTaskIsReturnedFromTracker() {
        Task task = new Task();
        this.sqlTaskTracker.add(task);
        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];

        Assert.assertEquals(lastAddedTask, task);
    }

    /**
     * Test that when we add from [0 to 10] tasks to the tracker, they are properly returned.
     * The size of returned array with tasks is also checked.
     */
    @Test
    public void whenAddFromZeroToTenTasksInTrackerThenCorrectTasksReturnedFromTracker() {
        int numOfTasks = ThreadLocalRandom.current().nextInt(11); // get ints from 0 to 10 inclusive
        Task[] testArrOfTasks = new Task[numOfTasks];
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;
        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task();
            sqlTaskTracker.add(task);
            testArrOfTasks[i] = task;
        }

        Task[] allTasksFromDB = this.sqlTaskTracker.findAll();
        Task[] onlyAddedTasks = Arrays.copyOfRange(allTasksFromDB, qtyOfTaskBeforeAdd, allTasksFromDB.length);
        Assert.assertThat(onlyAddedTasks.length, is(testArrOfTasks.length));
        Assert.assertThat(onlyAddedTasks, is(testArrOfTasks));
    }

    /**
     * When try to add null as a task to tracker then it is not added and null returned by method.
     */
    @Test
    public void whenAddNullAsTaskToTrackerThenItIsNotAddedToTracker() {
        Task task = sqlTaskTracker.add(null);
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;

        Assert.assertThat(task, is(nullValue()));
        Assert.assertThat(sqlTaskTracker.findAll().length, is(qtyOfTaskBeforeAdd));
    }

    /**
     * When a comment is added to a task in tracker then it is stored in the task and
     * correctly returned from tracker.
     */
    @Test
    public void whenAddCommentToTaskInTrackerThenCorrectTaskWithCommentReturnedFromTracker() {
        Task task = new Task();
        Comment comment = new Comment();
        sqlTaskTracker.add(task);
        sqlTaskTracker.addComment(task, comment);

        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Assert.assertThat(lastAddedTask.getComments()[0], is(comment));
    }

    /**
     * When a comment is added to a task not in the tracker, we get the unchanged task object and
     * nothing is stored in tracker.
     */
    @Test
    public void whenAddCommentToTaskNotInTrackerThenUnchangedTaskReturnedAndNothingChangedInTracker() {
        Task task = new Task();
        Comment comment = new Comment();
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;
        Task unchangedTask = sqlTaskTracker.addComment(task, comment);

        Assert.assertThat(unchangedTask, is(task));
        Assert.assertThat(sqlTaskTracker.findAll().length, is(qtyOfTaskBeforeAdd));
    }

    /**
     * When we add the same comment to a task in a tracker twice, the task will have the same comment but with different ids.
     */
    @Test
    public void whenAddSameCommentToTaskInTrackerTwiceThenTaskWillHaveTwoSameComments() {
        Task task = new Task();
        Comment comment = new Comment();
        sqlTaskTracker.add(task);
        sqlTaskTracker.addComment(task, comment);
        sqlTaskTracker.addComment(task, comment);

        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Comment comment1 = lastAddedTask.getComments()[0];
        Comment comment2 = lastAddedTask.getComments()[1];
        // ids of comments after save to DB are not equal
        Assert.assertNotEquals(comment1.getId(), comment2.getId());
        // contents of comments is equal
        Assert.assertEquals(comment1.getContent(), comment2.getContent());
        Assert.assertEquals(comment1.getCreateDate(), comment2.getCreateDate());
    }

    /**
     * When null is supplied as any argument for addComment method, then null is returned and
     * nothing changes in tracker.
     */
    @Test
    public void whenNullIsUsedAsAnyArgumentToAddCommentMethodThenNullIsReturnedAndNothingChangedInTracker() {
        Task task = new Task();
        Comment comment = new Comment();
        sqlTaskTracker.add(task);
        Task taskWhenCommentNull = sqlTaskTracker.addComment(task, null);
        Task taskWhenTaskNull = sqlTaskTracker.addComment(null, comment);
        Task taskWhenBothArgsNull = sqlTaskTracker.addComment(null, null);

        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Assert.assertThat(lastAddedTask.getComments().length, is(0));
        Assert.assertThat(taskWhenCommentNull, is(nullValue()));
        Assert.assertThat(taskWhenTaskNull, is(nullValue()));
        Assert.assertThat(taskWhenBothArgsNull, is(nullValue()));
    }

    /**
     * When task in tracker is updated, updated task is returned from tracker.
     * To check it the comment will be added to task and then task will be updated in tracker.
     */
    @Test
    public void whenTaskInTrackerUpdatedThenUpdatedTaskIsReturnedFromTracker() {
        Task task = new Task();
        Comment comment = new Comment();
        sqlTaskTracker.add(task); // add task to tracker
        task.addComment(comment); // change task
        sqlTaskTracker.update(task); // update task in tracker

        // task in tracker is updated and has a comment
        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Assert.assertThat(lastAddedTask, is(task));
        Assert.assertThat(lastAddedTask.getComments()[0], is(comment));
    }

    /**
     * When update task not in tracker, nothing is changed in tracker.
     */
    @Test
    public void whenUpdateTaskNotInTrackerThenNothingIsChangedInTracker() {
        Task taskInTracker = new Task();
        Task taskNotInTracker = new Task();
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;
        sqlTaskTracker.add(taskInTracker);
        sqlTaskTracker.update(taskNotInTracker);

        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Assert.assertThat(tasks.length - qtyOfTaskBeforeAdd, is(1));
        Assert.assertThat(lastAddedTask, is(taskInTracker));
    }

    /**
     * When null is used as argument in update method, nothing changes in tracker.
     */
    @Test
    public void whenTryToUpdateUsingNullArgumentThenNothingIsChangedInTracker() {
        Task taskInTracker = new Task();
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;
        sqlTaskTracker.add(taskInTracker);
        sqlTaskTracker.update(null);

        Task[] tasks = this.sqlTaskTracker.findAll();
        Task lastAddedTask = tasks[tasks.length - 1];
        Assert.assertThat(tasks.length - qtyOfTaskBeforeAdd, is(1));
        Assert.assertThat(lastAddedTask, is(taskInTracker));
    }

    /**
     * When delete task from tracker then task is deleted.
     */
    @Test
    public void whenDeleteTaskFromTrackerThenTaskIsDeleted() {
        Task task = new Task();
        int qtyOfTaskBeforeAdd = sqlTaskTracker.findAll().length;
        sqlTaskTracker.add(task);
        sqlTaskTracker.delete(task);

        Assert.assertThat(sqlTaskTracker.findAll().length, is(qtyOfTaskBeforeAdd));
    }

    /**
     * When delete task from tracker with other tasks, deleted task can't be found in tracker.
     * And other tasks stay in tracker.
     */
    @Test
    public void whenDeleteTaskFromTrackerThenItCantBeFoundInTrackerAndOtherTasksAreInTracker() {
        Task taskToDelete = new Task();
        Task taskToKeep1 = new Task();
        Task taskToKeep2 = new Task();
        sqlTaskTracker.add(taskToKeep1);
        sqlTaskTracker.add(taskToDelete);
        sqlTaskTracker.add(taskToKeep2);
        sqlTaskTracker.delete(taskToDelete);

        Task[] tasksInTracker = sqlTaskTracker.findAll();
        for (Task task : tasksInTracker) {
            Assert.assertThat(task, not(taskToDelete));
        }
        Assert.assertTrue(Arrays.asList(tasksInTracker).contains(taskToKeep1));
        Assert.assertTrue(Arrays.asList(tasksInTracker).contains(taskToKeep2));
    }

    /**
     * If you try to delete task which is not in tracker nothing changes in tracker.
     */
    @Test
    public void whenDeleteTaskNotInTrackerThenNothingChangesInTracker() {
        // add task to stay in tracker
        Task taskToKeep = new Task();
        sqlTaskTracker.add(taskToKeep);
        int qtyOfTaskBeforeDelete = sqlTaskTracker.findAll().length;
        // try to delete task which is not in tracker
        Task taskToDelete = new Task();
        sqlTaskTracker.delete(taskToDelete);

        Assert.assertThat(sqlTaskTracker.findAll().length, is(qtyOfTaskBeforeDelete));
        Assert.assertTrue(Arrays.asList(sqlTaskTracker.findAll()).contains(taskToKeep));
    }

    /**
     * When null is used as argument in delete method, nothing changes in tracker.
     */
    @Test
    public void whenTryToDeleteUsingNullArgumentThenNothingIsChangedInTracker() {
        Task taskToKeep = new Task();
        sqlTaskTracker.add(taskToKeep);
        int qtyOfTaskBeforeDelete = sqlTaskTracker.findAll().length;
        sqlTaskTracker.delete(null);

        Assert.assertThat(sqlTaskTracker.findAll().length, is(qtyOfTaskBeforeDelete));
        Assert.assertTrue(Arrays.asList(sqlTaskTracker.findAll()).contains(taskToKeep));
    }

    /**
     * After deletion of tasks from tracker there are no null values returned from tracker.
     */
    @Test
    public void whenDeleteTasksFromTrackerThenThereAreNoNullValuesLeftInTracker() {
        Task taskToKeep1 = new Task();
        Task taskToKeep2 = new Task();
        Task taskToDelete1 = new Task();
        Task taskToDelete2 = new Task();
        sqlTaskTracker.add(taskToKeep1);
        sqlTaskTracker.add(taskToKeep2);
        sqlTaskTracker.add(taskToDelete1);
        sqlTaskTracker.add(taskToDelete2);
        sqlTaskTracker.delete(taskToDelete1);
        sqlTaskTracker.delete(taskToDelete2);

        Task[] tasksInTracker = sqlTaskTracker.findAll();
        for (Task task : tasksInTracker) {
            Assert.assertThat(task, not(nullValue()));
        }
    }

    /**
     * When randomly add and delete tasks correct tasks are returned from tracker.
     */
    @Test
    public void whenRandomlyAddAndDeleteTasksThenCorrectTasksReturnedFromTracker() {
        int numOfTasks = ThreadLocalRandom.current().nextInt(51); // ints [0 to 50]
        List<Task> testListOfTasks = new ArrayList<>();
        // add tasks
        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task();
            sqlTaskTracker.add(task);
            testListOfTasks.add(task);
        }
        // randomly delete half of tasks
        for (int i = 0; i < numOfTasks / 2; i++) {
            int indexOfTask = ThreadLocalRandom.current().nextInt(testListOfTasks.size());
            Task taskToDelete = testListOfTasks.remove(indexOfTask);
            sqlTaskTracker.delete(taskToDelete);
        }

        Assert.assertTrue(Arrays.asList(sqlTaskTracker.findAll()).containsAll(testListOfTasks));
    }

    /**
     * When nothing is added to tracker then empty array of tasks is returned from tracker.
     */
    @Test
    public void whenNothingIsAddedToTrackerThenEmptyArrayOfTasksIsReturnedFromTracker() {
        Assert.assertThat(sqlTaskTracker.findAll().length, is(0));
    }

    /**
     * When try to find task by a name, then correct task is returned from tracker.
     */
    @Test
    public void whenFindTaskByNameFromTrackerThenCorrectTaskReturned() {
        Task task = new Task();
        task.setName("Task for Bender!");
        sqlTaskTracker.add(task);

        Assert.assertThat(sqlTaskTracker.findByName("Task for Bender!")[0], is(task));
    }

    /**
     * When try to find several tasks with same name, then correct tasks are returned from tracker.
     */
    @Test
    public void whenFindSeveralTasksByNameFromTrackerThenCorrectTasksReturned() {
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setName("Task for Bender!");
        task2.setName("Task for Bender!");
        sqlTaskTracker.add(task1);
        sqlTaskTracker.add(task2);

        Assert.assertThat(sqlTaskTracker.findByName("Task for Bender!")[0], is(task1));
        Assert.assertThat(sqlTaskTracker.findByName("Task for Bender!")[1], is(task2));
    }

    /**
     * When try to find several tasks with same name using substring, then correct tasks are returned from tracker.
     */
    @Test
    public void whenFindSeveralTasksBySubstringFromTrackerThenCorrectTasksReturned() {
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setName("Task for Bender!");
        task2.setName("Task for Bender!");
        sqlTaskTracker.add(task1);
        sqlTaskTracker.add(task2);

        Assert.assertThat(sqlTaskTracker.findByName("Bender")[0], is(task1));
        Assert.assertThat(sqlTaskTracker.findByName("Bender")[1], is(task2));
    }

    /**
     * When try to find by name task which is not in tracker then empty array of task is returned.
     */
    @Test
    public void whenTryToFindByNameTaskNotInTrackerThenEmptyArrayOfTasksIsReturned() {
        Task taskNotInTracker = new Task();
        taskNotInTracker.setName("Task NOT in tracker.");
        Task taskInTracker = new Task();
        sqlTaskTracker.add(taskInTracker);

        Assert.assertThat(sqlTaskTracker.findByName("Task NOT in tracker.").length, is(0));
    }

    /**
     * When try to find task by name using null as argument, then null is returned by method.
     */
    @Test
    public void whenTryToFindTaskByNameUsingNullAsArgumentThenNullReturned() {
        Assert.assertThat(sqlTaskTracker.findByName(null), is(nullValue()));
    }

    /**
     * When try to find task located in tracker by id, then correct task is returned from tracker.
     */
    @Test
    public void whenFindTaskInTrackerByIdThenCorrectTaskReturned() {
        Task task = new Task();
        sqlTaskTracker.add(task);
        long taskId = task.getId();

        Assert.assertThat(sqlTaskTracker.findById(taskId), is(task));
        Assert.assertThat(sqlTaskTracker.findById(taskId).getId(), is(taskId));
    }

    /**
     * When try to find task NOT in tracker by an id, then null is returned.
     */
    @Test
    public void whenFindTaskNotInTrackerByIdThenNullReturned() {
        Task task = new Task();
        long taskId = task.getId();

        Assert.assertThat(sqlTaskTracker.findById(taskId), is(nullValue()));
    }
}
