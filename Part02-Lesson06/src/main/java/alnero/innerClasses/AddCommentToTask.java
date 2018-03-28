package alnero.innerClasses;

import alnero.Task;
import alnero.Comment;
import alnero.TaskTracker;
import alnero.TrackerInput;

/**
 * Console interaction and adding a comment to task in tracker.
 * Task for adding a comment is searched by id, if not found app returns to main menu.
 */
class AddCommentToTask implements UserAction {

    @Override
    public int key() {
        return 6;
    }

    @Override
    public String info() {
        return "6. Add comment to task";
    }

    @Override
    public void execute(TaskTracker taskTracker, TrackerInput input) {
        System.out.println(this.info());

        System.out.println("Do you have ID of the task? (Y/N)");
        boolean userHasIdToAddComment = input.positiveAnswerGiven();

        if (userHasIdToAddComment) {
            System.out.println("Please enter task ID (e.g. 41731869482685287):");
            long addCommentTaskId = input.readInputId();

            Task addCommentTask = taskTracker.findById(addCommentTaskId);

            if (addCommentTask == null) {
                System.out.println("Sorry no task found!\n");
            } else {
                System.out.println(addCommentTask);

                System.out.print("Enter your comment for the task: ");
                String inputComment = input.readInputLine();
                Comment userComment = new Comment();
                userComment.setContent(inputComment);

                addCommentTask.addComment(userComment);

                taskTracker.update(addCommentTask);
                System.out.println(addCommentTask);

                System.out.println("Comment added to task successfully!\n");
            }
        } else {
            System.out.println("Please find task ID\n");
        }
    }
}