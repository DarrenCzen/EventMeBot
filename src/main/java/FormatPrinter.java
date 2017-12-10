public class FormatPrinter {
    public static String mainEvent = "Event Name: \n";
    public static String mainLocation = "Location: \n";
    public static String mainDate = "Date: \n";
    public static String mainTime = "Time: \n";
    public static String mainAttendees = "Attending:\n \n";
    public static String mainMaybes = "Maybe Attending:\n \n";
    public static String mainNonAttendees = "Not Attending:\n \n";

    public static String eventListing = " has been listed. "
            + "Please join the event by typing\n"
            + "/attending, /notattending, /maybeattending.";

    public static String eventLister(String original) {
        return original + eventListing;
    }
}
