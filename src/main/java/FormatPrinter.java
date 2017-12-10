import java.util.ArrayList;

public class FormatPrinter {
    public static final String mainEvent = "Event Name: \n";
    public static final String mainAttendees = "Attending:\n";
    public static final String mainMaybes = "Maybe Attending:\n";
    public static final String mainNonAttendees = "Not Attending:\n";

    public static final String MESSAGE_EVENT_NOT_CREATED = "Event not created yet.";
    public static final String eventListing = " has been listed. "
            + "Please join the event by typing\n"
            + "/attending, /notattending, /maybeattending.";

    public static String eventCreator(String original) {
        return original + eventListing;
    }

    public static String eventLister(String eventName, ArrayList<String> attendees, ArrayList<String> maybes, ArrayList<String> nonAttendees) {
        String newString = mainEvent + eventName + "\n" + "\n";

        newString = newString + compileAttendees(mainAttendees,attendees);
        newString = newString + compileAttendees(mainMaybes,maybes);
        newString = newString + compileAttendees(mainNonAttendees,nonAttendees);

        return newString;
    }

    public static String compileAttendees(String attendantType, ArrayList<String> people) {
        String compiledString = "";

        compiledString = compiledString + attendantType;

        for (String person : people) {
            compiledString = compiledString + person + "\n";
        }

        compiledString = compiledString + "\n";

        return compiledString;
    }
}
