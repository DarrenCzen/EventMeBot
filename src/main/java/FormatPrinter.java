import java.util.ArrayList;

public class FormatPrinter {
    public static final String mainEvent = "Event Name: \n";
    public static final String mainLocation = "Location: \n";
    public static final String mainDate = "Date: \n";
    public static final String mainTime = "Time: \n";
    public static final String mainAttendees = "Attending:\n \n";
    public static final String mainMaybes = "Maybe Attending:\n \n";
    public static final String mainNonAttendees = "Not Attending:\n \n";

    public static final String MESSAGE_EVENT_NOT_CREATED = "Event not created yet.";
    public static final String eventListing = " has been listed. "
            + "Please join the event by typing\n"
            + "/attending, /notattending, /maybeattending.";

    public static String eventCreator(String original) {
        return original + eventListing;
    }

    public static String eventLister(ArrayList<String> attendees, ArrayList<String> maybes, ArrayList<String> nonAttendees) {
        String newString = "";

        return newString;
    }
}
