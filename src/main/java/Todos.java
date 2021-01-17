import java.util.List;
import java.util.ArrayList;

/**
 * Store all the todos in a list DS
 */
public class Todos {
    private List<Item> todos;

    /**
     * Initialize the todo list with an empty ArrayList
     */
    public Todos() {
        todos = new ArrayList<Item>();
    }

    /**
     * Add the given item into the list
     * @param item The item to be added to the todo list
     */
    public void addItem(String item) {
        todos.add(new Item(item));
    }

    /**
     * Get the given item done, print out the result for reference
     * @param index 1-based index number referring to the items in the list
     */
    public void getItemDone(int index) {
        todos.get(index - 1).setDone(true);
        System.out.println("Nice! I have marked this item as done: ");
        System.out.print("----");
        System.out.println(todos.get(index - 1));
    }

    /**
     * Print out the todo list in a user-friendly format
     */
    public void printList() {
        System.out.println("These are the tasks in your list so far:");
        for (int i = 1; i <= todos.size(); i++) {
            String doneMark = todos.get(i - 1).isDone()? "X": " ";
            System.out.println(String.format("%d.[%s] %s", i, doneMark, todos.get(i - 1)));
        }
    }
}