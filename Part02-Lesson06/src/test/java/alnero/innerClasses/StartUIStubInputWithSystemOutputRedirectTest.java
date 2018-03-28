package alnero.innerClasses;

import alnero.Task;
import alnero.TaskTracker;
import alnero.TrackerInput;
import alnero.StubInput;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

/**
 * Testing methods of StartUI class of Tracker app with automatic stub input and
 * redirection of default system output to mock stream.
 * User actions are implemented via separate package private classes.
 */
public class StartUIStubInputWithSystemOutputRedirectTest {
    /** Common object for task tracker. */
    private TaskTracker taskTracker;
    /** Common dictionary object for stub input strings. */
    private HashMap<String, String[]> stubInputDictionary;
    /** Initial size of tracker storage, will increase automatically if needed. */
    private final int initialTaskTrackerSize = 10;
    /** Common object for automatic stub inputs. */
    private TrackerInput stubInput;
    /** Object to save original default system output. */
    private PrintStream originalSystemOut;
    /** Mock output stream object for redirection of default system output. */
    private ByteArrayOutputStream outputContent;

    /** Create common task tracker and stub input dictionary objects before tests. */
    @Before
    public void createCommonTaskTrackerAndStubInputDictionaryObjectsForTesting() {
        this.taskTracker = new TaskTracker(initialTaskTrackerSize);
        this.stubInputDictionary = new HashMap<>();
    }

    /** Save original default system output and redirect to mock output stream. */
    @Before
    public void redirectSystemOutputToSeparateStream() {
        this.originalSystemOut = System.out;
        this.outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.outputContent));
    }

    /** Reinstate original default system output. */
    @After
    public void reinstateSystemOutputToDefault() {
        System.setOut(this.originalSystemOut);
    }

    /** Test item "1. Show all tasks" of UI menu, all tasks from tracker are printed to output. */
    @Test
    public void whenTryToShowAllTasksStoredInTrackerThenTrackerPrintToOutputAllTasks() {
        Task mockTaskOne = new Task();
        mockTaskOne.setName("Test task one");
        mockTaskOne.setDescription("Test description one");

        Task mockTaskTwo = new Task();
        mockTaskTwo.setName("Test task two");
        mockTaskTwo.setDescription("Test description two");

        Task mockTaskThree = new Task();
        mockTaskThree.setName("Test task three");
        mockTaskThree.setDescription("Test description three");

        this.taskTracker.add(mockTaskOne);
        this.taskTracker.add(mockTaskTwo);
        this.taskTracker.add(mockTaskThree);

        this.stubInputDictionary.put("stubInputLine", new String[]{"1", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);

        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(outputContent.toString(), containsString(mockTaskOne.toString()));
        Assert.assertThat(outputContent.toString(), containsString(mockTaskTwo.toString()));
        Assert.assertThat(outputContent.toString(), containsString(mockTaskThree.toString()));
    }

    /** Test item "4. Find task by ID" of UI menu.
     *  When task is not found by id, the task is not printed to output. */
    @Test
    public void whenTryToFindTaskNotInTrackerByIdThenTaskNotPrintedInOutput() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"4", "7"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});
        this.stubInput = new StubInput(this.stubInputDictionary);

        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(outputContent.toString(), not(containsString(mockTask.toString())));
    }

    /** Test item "4. Find task by ID" of UI menu.
     * When task stored in tracker is found by id, the task is printed to output. */
    @Test
    public void whenTryToFindTaskStoredInTrackerByIdThenTaskPrintedInOutput() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        long testTaskId = mockTask.getId();

        this.stubInputDictionary.put("stubInputLine", new String[]{"4", "7"});
        this.stubInputDictionary.put("stubInputId", new String[]{Long.toString(testTaskId)});
        this.stubInput = new StubInput(this.stubInputDictionary);

        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(outputContent.toString(), containsString(mockTask.toString()));
    }

    /** Test item "4. Find task by ID" of UI menu.
     * When task stored in tracker is not found by name, the task is not printed to output. */
    @Test
    public void whenTryToFindTaskByNonMatchingNameThenNoTaskPrintedInOutput() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        this.stubInputDictionary.put("stubInputLine", new String[]{"5", "WRONG SEARCH STRING", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);

        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(outputContent.toString(), not(containsString(mockTask.toString())));
    }

    /** Test item "4. Find task by ID" of UI menu.
     * When task stored in tracker is found by name, the task is printed to output. */
    @Test
    public void whenTryToFindTaskByMatchingNameThenTaskPrintedInOutput() {
        Task mockTask = new Task();
        mockTask.setName("Test task");
        mockTask.setDescription("Test description");

        this.taskTracker.add(mockTask);

        this.stubInputDictionary.put("stubInputLine", new String[]{"5", "Test", "7"});
        this.stubInput = new StubInput(this.stubInputDictionary);

        new StartUI(this.taskTracker, this.stubInput).init();

        Assert.assertThat(outputContent.toString(), containsString(mockTask.toString()));
    }

    /** Test item "6. Add comment to task" of UI menu.
     * When comment is added to task stored in tracker, the task with the new comment is printed to output. */
    @Test
    public void whenTryToAddCommentToTaskAndIdIsCorrectThenTaskWithCommentIsPrintedInOutput() {
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

        Assert.assertThat(outputContent.toString(), containsString(mockTask.toString()));
    }
}