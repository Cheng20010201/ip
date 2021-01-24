import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent an Event item, which is a child of Task
 */
public class Event extends Task {
    private LocalDate period;

    /**
     * Initialize an Event with a time period
     * @param name The name of the event
     * @param period The date of the event, assumed to be 'yyyy-mm-dd'
     */
    public Event(String name, LocalDate period) {
        super(name);
        this.period = period;
    }

    /**
     * toString method overriding the one in class Task
     * @return a user-friendly String representation of the Event item
     */
    @Override
    public String toString() {
        String doneMark = isDone? "X": " ";
        return String.format("[E][%s] %s (at: %s)", doneMark, name,
                period.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
    }
}