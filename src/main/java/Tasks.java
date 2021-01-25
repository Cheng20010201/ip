import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONArray;

/**
 * Store all the tasks in a list DS
 */
public class Tasks {
    private List<Task> tasks;

    /**
     * Initialize the task list with an empty ArrayList
     */
    public Tasks() {
        tasks = new ArrayList<Task>();
    }

    /*
    public void addTask(String userInput) {
        try {
            Task task = null;

            // It is guaranteed that either one of the three blocks will be entered
            // therefore the final value for task can never be null
            if (FormatChecker.likeAddingToDo(userInput)) {
                // may throw exception
                String content = InputInformationExtractor.getToDoContent(userInput);
                task = new ToDo(content);
            } else if (FormatChecker.likeAddingDeadline(userInput)) {
                String content = InputInformationExtractor.getDeadlineContent(userInput);
                LocalDate by = InputInformationExtractor.getDeadlineTime(userInput);
                task = new Deadline(content, by);
            } else if (FormatChecker.likeAddingEvent(userInput)) {
                String content = InputInformationExtractor.getEventContent(userInput);
                LocalDate time = InputInformationExtractor.getEventTime(userInput);
                task = new Event(content, time);
            }
            tasks.add(task);

            System.out.println("Got it. I have added this task to your list:");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (ToDoException e) {
            System.out.println("Sorry, the format for adding a todo task is \'todo [task name]\'. " +
                    "Please retry :')");
        } catch (DeadlineException e) {
            System.out.println("Sorry, the format for adding a deadline task is \'deadline [task name] /by time\'. " +
                    "Please retry :')");
        } catch (EventException e) {
            System.out.println("Sorry, the format for adding an event task is \'event [task name] /at time\'. " +
                    "Please retry :')");
        }
    }

     */

    public void addTask(Task t) throws Exception {
        tasks.add(t);
    }

    public Task removeTask(int i) throws IndexOutOfBoundsException {
        Task t = tasks.remove(i - 1);
        return t;
    }

    public Task getTaskDone(int i) throws IndexOutOfBoundsException {
        tasks.get(i).setDone(true);
        Task t  = tasks.get(i - 1);
        return t;
    }

    public String listTasks(LocalDate date) {
        String message = "";
        // tasks is not null, but maybe empty
        for (int i = 1; i <= tasks.size(); i++) {
            if (date != null) {
                // list on a specific date
                // date is not null here
                if (date.equals(tasks.get(i - 1).getDate())) {
                    // getDate() is not null here
                    message += String.format("%d. %s\n", i, tasks.get(i - 1));
                }
            } else {
                message += String.format("%d. %s\n", i, tasks.get(i - 1));
            }
        }
        if (message.equals("")) {
            message = "You do not have any task right now. Please add one first. :')";
        } else {
            message = "Here are all your tasks:\n" + message + "Good luck. :D";
        }
        return message;
    }

    /**
     * Get the given task done, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    /*private void getTaskDone(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            tasks.get(index - 1).setDone(true);
            System.out.println("Nice! I have marked this task as done:");
            System.out.print("---- ");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }*/

    /**
     * Get the given task done, represented by the user input string
     * @param userInput Assumed to be of the format of trying to get tasks done
     */
    public void getTaskDone(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            getTaskDone(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for getting task done is \'done [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Delete the given task, print out the result for reference
     * @param index 1-based index number referring to the specific task in the list
     */
    private void deleteTask(int index) {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        try {
            Task task = tasks.remove(index - 1);
            System.out.println("Noted! The task has successfully been removed.");
            System.out.print("---- ");
            System.out.println(task);
            reportTotalNumberOfTasks();
        } catch (IndexOutOfBoundsException e) {
            reportInvalidIndexNumber();
        }
    }

    /**
     * Get the given task deleted, represented by the user input string
     * @param userInput Assumed to be of the format of trying to delete a task
     */
    public void deleteTask(String userInput) {
        try {
            int index = InputInformationExtractor.getIndexArgument(userInput);
            deleteTask(index);
        } catch (Exception e) {
            // index argument not found
            System.out.println("Sorry, the format for deleting a task is \'delete [a valid task number]\'. " +
                    "Please retry :')");
        }
    }

    /**
     * Print out the list in a user-friendly format
     */
    public void printList() {
        if (tasks.size() == 0) {
            reportNoTaskRightNow();
            return;
        }
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i, tasks.get(i - 1)));
        }
    }

    /**
     * Get the total number of tasks now
     * @return The total number of tasks now, including done and undone tasks
     */
    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Print the report for total number of tasks, including done and undone tasks
     */
    public void reportTotalNumberOfTasks() {
        String noun = tasks.size() <= 1? "task": "tasks";
        System.out.println(String.format("Now you have %d %s in total. Good Luck.", tasks.size(), noun));
    }


    /**
     * @param userInput Second argument of userInput is assumed to be of 'yyyy-mm-dd'
     */
    public void printTasksOnDate(String userInput) {
        LocalDate date = LocalDate.parse(userInput.split(" ")[1]);
        boolean hasTask = false;
        for (Task t : tasks) {
            if (t instanceof ToDo) {
                continue;
            }
            // t is either deadline or event
            if (date.equals(t.getDate())) {
                if (!hasTask) {
                    hasTask = true;
                    System.out.println(String.format("You have the following tasks on %s.",
                            date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))));
                }
                System.out.println(t);
            }
        }
        if (!hasTask) {
            System.out.println(String.format("You do not have any task on %s. :')",
                    date.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))));
        }
    }

    /**
     * Print the report for error message: invalid index number of the list
     */
    private void reportInvalidIndexNumber() {
        System.out.println("Sorry, the task number you specified is not valid.\n" +
                "Try enter \'list\' to see the range of task numbers you can enter.");
    }

    /**
     * Print the report for error message:
     * no task right now, no delete/get done operation can be performed
     */
    private void reportNoTaskRightNow() {
        System.out.println("Sorry, there is no task right now, please add one first :')");
    }

    public JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < tasks.size(); i++) {
            jsonArray.add(tasks.get(i).toJsonObject());
        }
        return jsonArray;
    }

    public int getSize() {
        return tasks.size();
    }
}