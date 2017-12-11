import java.util.ArrayList;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class EventMeBot extends TelegramLongPollingBot{

    ArrayList<String> attendees = new ArrayList<String>();
    ArrayList<String> maybeAttendees = new ArrayList<String>();
    ArrayList<String> nonAttendees = new ArrayList<String>();
    public static String eventName = "";

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();

            if (message_text.startsWith("/create")) {
                if (eventName.equals("") && !message_text.substring(8).equals("")) {
                    eventName = message_text.substring(8);

                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.eventCreator(eventName));
                    executeMessage(message);
                } else if (eventName.equals("") && message_text.substring(8).equals("")) {
                    SendMessage message = createNewSendMessage(chat_id, "Event is not written properly for "
                            + "/create.\n E.g. /create Event Name");
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, "Event is already created.");
                    executeMessage(message);
                }
            } else if (message_text.equals("/clear")) {
                if (!eventName.equals("")) {
                    eventName = "";
                    attendees.clear();
                    nonAttendees.clear();
                    maybeAttendees.clear();
                    SendMessage message = createNewSendMessage(chat_id, "Event has been cleared.");
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.MESSAGE_EVENT_NOT_CREATED);
                    executeMessage(message);
                }
            } else if (message_text.equals("/list")) {
                if (!eventName.equals("")) {
                    String listedString = FormatPrinter.eventCreator(eventName) + "\n"
                            + FormatPrinter.eventLister(eventName, attendees, maybeAttendees, nonAttendees);
                    SendMessage message = createNewSendMessage(chat_id, listedString);
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.MESSAGE_EVENT_NOT_CREATED);
                    executeMessage(message);
                }
            } else if (message_text.equals("/attending") || message_text.equals("/attending@EventMeBot")) {
                if (!eventName.equals("")) {
                    if (!attendees.contains(username)) {
                        attendees.add(username);
                        nonAttendees.remove(username);
                        maybeAttendees.remove(username);
                    }
                    String listedString = FormatPrinter.eventLister(eventName, attendees, maybeAttendees, nonAttendees);
                    SendMessage message = createNewSendMessage(chat_id, listedString);
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.MESSAGE_EVENT_NOT_CREATED);
                    executeMessage(message);
                }
            } else if (message_text.equals("/notattending") || message_text.equals("/notattending@EventMeBot")) {
                if (!eventName.equals("")) {
                    if (!nonAttendees.contains(username)) {
                        attendees.remove(username);
                        nonAttendees.add(username);
                        maybeAttendees.remove(username);
                    }
                    String listedString = FormatPrinter.eventLister(eventName, attendees, maybeAttendees, nonAttendees);
                    SendMessage message = createNewSendMessage(chat_id, listedString);
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.MESSAGE_EVENT_NOT_CREATED);
                    executeMessage(message);
                }
            } else if (message_text.equals("/maybeattending") || message_text.equals("/maybeattending@EventMeBot")) {
                if (!eventName.equals("")) {
                    if (!maybeAttendees.contains(username)) {
                        attendees.remove(username);
                        nonAttendees.remove(username);
                        maybeAttendees.add(username);
                    }
                    String listedString = FormatPrinter.eventLister(eventName, attendees, maybeAttendees, nonAttendees);
                    SendMessage message = createNewSendMessage(chat_id, listedString);
                    executeMessage(message);
                } else {
                    SendMessage message = createNewSendMessage(chat_id, FormatPrinter.MESSAGE_EVENT_NOT_CREATED);
                    executeMessage(message);
                }
            } else {
                // Unknown command
                SendMessage message = createNewSendMessage(chat_id, "Unknown Command");
                executeMessage(message);
            }
        }
    }

    /**
     * Returns Bot Username
     * @return String
     */
    public SendMessage createNewSendMessage(long chat_id, String textMessage) {
        // Create a message object object
        return new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText(textMessage);
    }

    /**
     * Executes message sent to bot.
     */
    private void executeMessage(SendMessage message) {
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns Bot Username
     * @return String
     */
    public String getBotUsername() {
        return "EventMeBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "461749335:AAGRiMG5X4sDMkCVHqDnL4XB22N2ff8tvXc";
    }
}
