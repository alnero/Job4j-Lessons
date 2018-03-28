package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;
import alnero.StubInput;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;

/**
 * Testing methods of StartUI class of Tracker app with automatic stub input.
 * User actions are implemented via separate package private classes.
 */
public class StartUIStubInputTest {
    /** Common object for task tracker. */
    private TaskTracker taskTracker;
    /** Common dictionary object for stub input strings. */
    private HashMap<String, String[]> stubInputDictionary;
    /** Initial size of tracker storage, will increase automatically if needed. */
    private final int initialTaskTrackerSize = 10;
    /** Common object for automatic stub inputs. */
    private TrackerInput stubInput;

    /** Create common task tracker and stub input dictionary objects before tests. */
    @Before
    public void createCommonTaskTrackerAndStubInputDictionaryObjectsForTesting() {
        this.taskTracker = new TaskTracker(initialTaskTrackerSize);
        this.stubInputDictionary = new HashMap<>();
    }

    /** Test item "0. Add new task" of UI menu, one task created and stored in tracker. */
    @Test
    public void whenAddOneTaskThenOnlyOneTaskStoredInTracker() {
        this.stubInputDictionary.put("stubInputLine", new String[]{"0", "", "", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(1));
    }

    /** Test item "0. Add new task" of UI menu, three tasks created and stored in tracker. */
    @Test
    public void whenAddThreeTasksToTrackerThenThreeTasksAreInTracker() {
        this.stubInputDictionary.put("stubInputLine",
                new String[]{"0", "Test task one", "Test description one",
                             "0", "Test task two", "Test description two",
                             "0", "Test task three", "Test description three",
                             "7"
                }
        );
        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(3));
    }

    /** Test item "0. Add new task" of UI menu.
     * Created task stored in tracker has correct name and description */
    @Test
    public void whenAddTaskWithNameAndDescriptionThenTaskInTrackerHasProperNameAndDescription() {
        this.stubInputDictionary.put("stubInputLine", new String[]{"0", "Test task", "Test description", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "2. Edit task" of UI menu.
     * Trying to edit task without knowing id does not affect task and tracker. */
    @Test
    public void whenTryToEditTaskAndIdIsUnknownThenTaskInTrackerIsNotChanged() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        this.stubInputDictionary.put("stubInputLine", new String[]{"2", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"N"});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "2. Edit task" of UI menu.
     * Trying to edit task not stored in tracker do not affect tracker. */
    @Test
    public void whenTryToEditTaskInEmptyTrackerThenTrackerStaysEmpty() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"2", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(0));
    }

    /** Test item "2. Edit task" of UI menu.
     * Trying to edit task and entering wrong id do not affect task or tracker. */
    @Test
    public void whenTryToEditTaskAndIdIsNotCorrectThenTaskInTrackerIsNotChanged() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long falseTestTaskId = mockTask.getId() + 1;

        this.stubInputDictionary.put("stubInputLine", new String[]{"2", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(falseTestTaskId)});


        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "2. Edit task" of UI menu.
     * Trying to edit task found in tracker by correct id changes task name and description. */
    @Test
    public void whenTryToEditTaskAndIdIsCorrectThenTaskNameAndDescriptionInTrackerAreChanged() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"2", "EDITED Test task", "EDITED Test description", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("EDITED Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("EDITED Test description"));
    }

    /** Test item "3. Delete task" of UI menu.
     * Trying to delete task without knowing id do not delete task or change tracker. */
    @Test
    public void whenTryToDeleteTaskAndIdIsUnknownThenTaskInTrackerIsNotDeleted() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        this.stubInputDictionary.put("stubInputLine", new String[]{"3", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"N"});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is not deleted from Tracker
        Assert.assertThat(this.taskTracker.findAll().length, is(1));

        // Test task is not changed
        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "3. Delete task" of UI menu.
     * Trying to delete task not stored in tracker does not affect tracker. */
    @Test
    public void whenTryToDeleteTaskFromEmptyTrackerThenTrackerStaysEmpty() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"3", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(0));
    }

    /** Test item "3. Delete task" of UI menu.
     * Trying to delete task and entering wrong id do not delete task or change tracker. */
    @Test
    public void whenTryToDeleteTaskAndIdIsNotCorrectThenTaskInTrackerIsNotDeleted() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long falseTestTaskId = mockTask.getId() + 1;

        this.stubInputDictionary.put("stubInputLine", new String[]{"3", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(falseTestTaskId)});


        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is not deleted from Tracker
        Assert.assertThat(this.taskTracker.findAll().length, is(1));

        // Test task is not changed
        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "3. Delete task" of UI menu.
     * When deletion of task found in tracker by correct id is not confirmed, task not deleted. */
    @Test
    public void whenTryToDeleteTaskAndIdIsCorrectButDeletionNotConfirmedThenTaskInTrackerIsNotDeleted() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"3", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y", "N"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});


        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is not deleted from Tracker
        Assert.assertThat(this.taskTracker.findAll().length, is(1));

        // Test task is not changed
        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getName(), is("Test task"));
        Assert.assertThat(taskFromTracker.getDescription(), is("Test description"));
    }

    /** Test item "3. Delete task" of UI menu.
     * When deletion of task found in tracker by correct id is confirmed, task is deleted from tracker. */
    @Test
    public void whenTryToDeleteTaskAndIdIsCorrectAndDeletionConfirmedThenTaskDeletedFromTracker() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"3", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y", "Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});


        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is deleted, Tracker is empty
        Assert.assertThat(this.taskTracker.findAll().length, is(0));
    }

    /** Test item "6. Add comment to task" of UI menu.
     * Trying to add comment to task without knowing id does not affect tracker. */
    @Test
    public void whenTryToAddCommentToTaskAndIdIsUnknownThenTaskHasNoComments() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        this.stubInputDictionary.put("stubInputLine", new String[]{"6", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"N"});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is not changed
        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getComments().length, is(0));
    }

    /** Test item "6. Add comment to task" of UI menu.
     * Trying to add comment to task and entering wrong id do not change the task in tracker. */
    @Test
    public void whenTryToAddCommentToTaskAndIdIsNotCorrectThenTaskHasNoComments() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long falseTestTaskId = mockTask.getId() + 1;

        this.stubInputDictionary.put("stubInputLine", new String[]{"6", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(falseTestTaskId)});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        // Test task is not changed
        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getComments().length, is(0));
    }

    /** Test item "6. Add comment to task" of UI menu.
     * Trying to add comment to task and entering correct id changes task in tracker. */
    @Test
    public void whenTryToAddCommentToTaskAndIdIsCorrectThenCommentIsAddedToTask() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"6", "Test comment", "7"});
        this.stubInputDictionary.put("stubYesNoAnswer", new String[]{"Y"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});

        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Task taskFromTracker = this.taskTracker.findAll()[0];
        Assert.assertThat(taskFromTracker.getComments().length, is(1));
        Assert.assertThat(taskFromTracker.getComments()[0].getContent(), is("Test comment"));
    }

    /** Test item "7. Exit Program" of UI menu.
     * Just exit from tracker app, no changes to tracker happen. */
    @Test
    public void whenWithEmptyTrackerOnlyExitIsChosenInUIMenuThenTrackerIsEmpty() {
        this.stubInputDictionary.put("stubInputLine", new String[]{"7"});
        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(0));
    }

    /** Test input of wrong constants for UI menu items, then exit. Tracker is not changed. */
    @Test
    public void whenWithEmptyTrackerUIMenuConstantsNotChosenProperlyThenTrackerIsEmpty() {
        this.stubInputDictionary.put("stubInputLine", new String[]{"A", "B", "C", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);
        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(this.taskTracker.findAll().length, is(0));
    }
}