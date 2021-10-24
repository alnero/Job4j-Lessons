package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Testing methods in TaskTracker class.
 */
public class TaskTrackerTest {

    /**
     * Test that when we add single task to a tracker only that task is returned and length of the
     * returned arr is one.
     */
    @Test
    public void whenAddOneTaskToTrackerThenSameTaskIsReturnedFromTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        taskTracker.add(task);

        Assert.assertThat(taskTracker.findAll().length, is(1));
        Assert.assertThat(taskTracker.findAll()[0], is(task));
    }

    /**
     * Test that when we add from [0 to 10] tasks to the tracker, they are properly returned.
     * The size of returned array with tasks is also checked.
     */
    @Test
    public void whenAddFromZeroToTenTasksInTrackerThenCorrectTasksReturnedFromTracker() {
        int numOfTasks = ThreadLocalRandom.current().nextInt(11);

        TaskTracker taskTracker = new TaskTracker(numOfTasks);
        Task[] testArrOfTasks = new Task[numOfTasks];

        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task();

            taskTracker.add(task);
            testArrOfTasks[i] = task;
        }

        Assert.assertThat(taskTracker.findAll().length, is(testArrOfTasks.length));
        Assert.assertThat(taskTracker.findAll(), is(testArrOfTasks));
    }

    /**
     * Test that task storage in tracker is increased when it's full.
     */
    @Test
    public void whenStorageInTrackerIsFullThenItIsIncreasedToStoreMoreTasks() {
        int numOfTasks = ThreadLocalRandom.current().nextInt(11);

        TaskTracker taskTracker = new TaskTracker(numOfTasks / 3);
        Task[] testArrOfTasks = new Task[numOfTasks];

        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task();

            taskTracker.add(task);
            testArrOfTasks[i] = task;
        }

        Assert.assertThat(taskTracker.findAll().length, is(testArrOfTasks.length));
        Assert.assertThat(taskTracker.findAll(), is(testArrOfTasks));
    }

    /**
     * When try to add null as a task to tracker then it is not added and null returned by method.
     */
    @Test
    public void whenAddNullAsTaskToTrackerThenItIsNotAddedToTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = taskTracker.add(null);

        Assert.assertThat(task, is(nullValue()));
        Assert.assertThat(taskTracker.findAll().length, is(0));
    }

    /**
     * When a comment is added to a task in tracker then it is stored in the task and
     * correctly returned from tracker.
     */
    @Test
    public void whenAddCommentToTaskInTrackerThenCorrectTaskWithCommentReturnedFromTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        Comment comment = new Comment();

        taskTracker.add(task);
        taskTracker.addComment(task, comment);

        Assert.assertThat(taskTracker.findAll()[0].getComments()[0], is(comment));
    }

    /**
     * When a comment is added to a task not in the tracker, we get the unchanged task object and
     * nothing is stored in tracker.
     */
    @Test
    public void whenAddCommentToTaskNotInTrackerThenUnchangedTaskReturnedAndNothingChangedInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        Comment comment = new Comment();

        Task unchangedTask = taskTracker.addComment(task, comment);

        Assert.assertThat(unchangedTask, is(task));
        Assert.assertThat(taskTracker.findAll().length, is(0));
    }

    /**
     * When we add the same comment to a task in a tracker twice, the task will have two same comments.
     */

    @Test
    public void whenAddSameCommentToTaskInTrackerTwiceThenTaskWillHaveTwoSameComments() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        Comment comment = new Comment();

        taskTracker.add(task);
        taskTracker.addComment(task, comment);
        taskTracker.addComment(task, comment);

        Assert.assertThat(taskTracker.findAll()[0].getComments()[0], is(comment));
        Assert.assertThat(taskTracker.findAll()[0].getComments()[1], is(comment));
    }

    /**
     * When null is supplied as any argument for addComment method, then null is returned and
     * nothing changes in tracker.
     */
    @Test
    public void whenNullIsUsedAsAnyArgumentToAddCommentMethodThenNullIsReturnedAndNothingChangedInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        Comment comment = new Comment();

        taskTracker.add(task);
        Task taskWhenCommentNull = taskTracker.addComment(task, null);
        Task taskWhenTaskNull = taskTracker.addComment(null, comment);
        Task taskWhenBothArgsNull = taskTracker.addComment(null, null);

        Assert.assertThat(taskWhenCommentNull, is(nullValue()));
        Assert.assertThat(taskWhenTaskNull, is(nullValue()));
        Assert.assertThat(taskWhenBothArgsNull, is(nullValue()));
        Assert.assertThat(taskTracker.findAll()[0].getComments().length, is(0));
    }

    /**
     * When task in tracker is updated, updated task is returned from tracker.
     * To check it the comment will be added to task and then task will be updated in tracker.
     */
    @Test
    public void whenTaskInTrackerUpdatedThenUpdatedTaskIsReturnedFromTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        Comment comment = new Comment();

        taskTracker.add(task);
        task.addComment(comment);
        taskTracker.update(task);

        Assert.assertThat(taskTracker.findAll()[0], is(task));
        Assert.assertThat(taskTracker.findAll()[0].getComments()[0], is(comment));
    }

    /**
     * When update task not in tracker, nothing is changed in tracker.
     */
    @Test
    public void whenUpdateTaskNotInTrackerThenNothingIsChangedInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task taskInTracker = new Task();
        Task taskNotInTracker = new Task();


        taskTracker.add(taskInTracker);
        taskTracker.update(taskNotInTracker);

        Assert.assertThat(taskTracker.findAll().length, is(1));
        Assert.assertThat(taskTracker.findAll()[0], is(taskInTracker));
    }

    /**
     * When null is used as argument in update method, nothing changes in tracker.
     */
    @Test
    public void whenTryToUpdateUsingNullArgumentThenNothingIsChangedInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task taskInTracker = new Task();

        taskTracker.add(taskInTracker);
        taskTracker.update(null);

        Assert.assertThat(taskTracker.findAll().length, is(1));
        Assert.assertThat(taskTracker.findAll()[0], is(taskInTracker));
    }

    /**
     * When delete task from tracker with only one task then tracker is empty.
     */
    @Test
    public void whenDeleteTaskFromTrackerWithOnlyOneTaskThenTrackerIsEmpty() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();

        taskTracker.add(task);
        taskTracker.delete(task);

        Assert.assertThat(taskTracker.findAll().length, is(0));
    }

    /**
     * When delete task from tracker with other tasks, deleted task can't be found in tracker.
     * And other tasks stay in tracker.
     */
    @Test
    public void whenDeleteTaskFromTrackerThenItCantBeFoundInTrackerAndOtherTasksAreInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task taskToDelete = new Task();
        Task taskToKeep1 = new Task();
        Task taskToKeep2 = new Task();

        taskTracker.add(taskToKeep1);
        taskTracker.add(taskToDelete);
        taskTracker.add(taskToKeep2);

        taskTracker.delete(taskToDelete);

        Task[] tasksInTracker = taskTracker.findAll();
        int numOfTasksInTracker = tasksInTracker.length;

        for (int i = 0; i < numOfTasksInTracker; i++) {
            Assert.assertThat(tasksInTracker[i], not(taskToDelete));
        }

        Assert.assertThat(taskTracker.findAll()[0], is(taskToKeep1));
        Assert.assertThat(taskTracker.findAll()[1], is(taskToKeep2));
    }

    /**
     * If you try to delete task which is not in tracker nothing changes in tracker.
     */
    @Test
    public void whenDeleteTaskNotInTrackerThenNothingChangesInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task taskToKeep = new Task();
        taskTracker.add(taskToKeep);

        Task taskToDelete = new Task();
        taskTracker.delete(taskToDelete);

        Assert.assertThat(taskTracker.findAll().length, is(1));
        Assert.assertThat(taskTracker.findAll()[0], is(taskToKeep));
    }

    /**
     * When null is used as argument in delete method, nothing changes in tracker.
     */
    @Test
    public void whenTryToDeleteUsingNullArgumentThenNothingIsChangedInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        taskTracker.add(task);

        taskTracker.delete(null);

        Assert.assertThat(taskTracker.findAll().length, is(1));
        Assert.assertThat(taskTracker.findAll()[0], is(task));
    }

    /**
     * After deletion of tasks from tracker there are no null values returned from tracker.
     */
    @Test
    public void whenDeleteTasksFromTrackerThenThereAreNoNullValuesLeftInTracker() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task taskToKeep1 = new Task();
        Task taskToKeep2 = new Task();
        Task taskToDelete1 = new Task();
        Task taskToDelete2 = new Task();

        taskTracker.add(taskToKeep1);
        taskTracker.add(taskToKeep2);
        taskTracker.add(taskToDelete1);
        taskTracker.add(taskToDelete2);

        taskTracker.delete(taskToDelete1);
        taskTracker.delete(taskToDelete2);

        Task[] tasksInTracker = taskTracker.findAll();
        int numOfTasksInTracker = tasksInTracker.length;

        for (int i = 0; i < numOfTasksInTracker; i++) {
            Assert.assertThat(tasksInTracker[i], not(nullValue()));
        }
    }

    /**
     * When randomly add and delete tasks correct tasks are returned from tracker.
     */
    @Test
    public void whenRandomlyAddAndDeleteTasksThenCorrectTasksReturnedFromTracker() {
        int numOfTasks = ThreadLocalRandom.current().nextInt(51);

        TaskTracker taskTracker = new TaskTracker(numOfTasks);
        Task[] testArrOfTasks = new Task[numOfTasks];

        for (int i = 0; i < numOfTasks; i++) {
            Task task = new Task();

            taskTracker.add(task);
            testArrOfTasks[i] = task;
        }

        for (int i = 0; i < numOfTasks / 2; i++) {
            int indexOfTask = ThreadLocalRandom.current().nextInt(numOfTasks);
            Task taskToDelete = testArrOfTasks[indexOfTask];

            taskTracker.delete(taskToDelete);
            testArrOfTasks[indexOfTask] = null;
        }

        List<Task> list = new ArrayList<>(Arrays.asList(testArrOfTasks));
        list.removeAll(Collections.singleton(null));
        testArrOfTasks = list.toArray(new Task[list.size()]);

        Assert.assertThat(taskTracker.findAll(), is(testArrOfTasks));
    }

    /**
     * When nothing is added to tracker then empty array of tasks is returned from tracker.
     */
    @Test
    public void whenNothingIsAddedToTrackerThenEmptyArrayOfTasksIsReturnedFromTracker() {
        TaskTracker taskTracker = new TaskTracker(1);

        Assert.assertThat(taskTracker.findAll().length, is(0));
    }

    /**
     * When try to find task by a name, then correct task is returned from tracker.
     */
    @Test
    public void whenFindTaskByNameFromTrackerThenCorrectTaskReturned() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        task.setName("Task for Bender!");

        taskTracker.add(task);

        Assert.assertThat(taskTracker.findByName("Task for Bender!")[0], is(task));
    }

    /**
     * When try to find several tasks with same name, then correct tasks are returned from tracker.
     */
    @Test
    public void whenFindSeveralTasksByNameFromTrackerThenCorrectTasksReturned() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setName("Task for Bender!");
        task2.setName("Task for Bender!");

        taskTracker.add(task1);
        taskTracker.add(task2);

        Assert.assertThat(taskTracker.findByName("Task for Bender!")[0], is(task1));
        Assert.assertThat(taskTracker.findByName("Task for Bender!")[1], is(task2));
    }

    /**
     * When try to find several tasks with same name using substring, then correct tasks are returned from tracker.
     */
    @Test
    public void whenFindSeveralTasksBySubstringFromTrackerThenCorrectTasksReturned() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task1 = new Task();
        Task task2 = new Task();
        task1.setName("Task for Bender!");
        task2.setName("Task for Bender!");

        taskTracker.add(task1);
        taskTracker.add(task2);

        Assert.assertThat(taskTracker.findByName("Bender")[0], is(task1));
        Assert.assertThat(taskTracker.findByName("Bender")[1], is(task2));
    }

    /**
     * When try to find by name task which is not in tracker then empty array of task is returned.
     */
    @Test
    public void whenTryToFindByNameTaskNotInTrackerThenEmptyArrayOfTasksIsReturned() {
        TaskTracker taskTracker = new TaskTracker(1);

        Task taskNotInTracker = new Task();
        taskNotInTracker.setName("Task NOT in tracker.");

        Task taskInTracker = new Task();
        taskTracker.add(taskInTracker);

        Assert.assertThat(taskTracker.findByName("Task NOT in tracker.").length, is(0));
    }

    /**
     * When try to find task by name using null as argument, then null is returned by method.
     */
    @Test
    public void whenTryToFindTaskByNameUsingNullAsArgumentThenNullReturned() {
        TaskTracker taskTracker = new TaskTracker(1);

        Assert.assertThat(taskTracker.findByName(null), is(nullValue()));
    }

    /**
     * When try to find task located in tracker by an id, then correct task is returned from tracker.
     */
    @Test
    public void whenFindTaskInTrackerByIdThenCorrectTaskReturned() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        long taskId = task.getId();

        taskTracker.add(task);

        Assert.assertThat(taskTracker.findById(taskId), is(task));
        Assert.assertThat(taskTracker.findById(taskId).getId(), is(taskId));
    }

    /**
     * When try to find task NOT in tracker by an id, then null is returned.
     */
    @Test
    public void whenFindTaskNotInTrackerByIdThenNullReturned() {
        TaskTracker taskTracker = new TaskTracker(1);
        Task task = new Task();
        long taskId = task.getId();

        Assert.assertThat(taskTracker.findById(taskId), is(nullValue()));
    }
}
