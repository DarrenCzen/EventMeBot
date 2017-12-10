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
                if (eventName.equals("")) {
                    // User send /create
                    eventName = message_text.substring(8);

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(FormatPrinter.eventLister(eventName));
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Event is already created.");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (message_text.equals("/clear")) {
                if (!eventName.equals("")) {
                    // User send /clear
                    eventName = "";

                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Event has been cleared.");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Event not created yet.");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (message_text.equals("/attending")) {
                // User send /attending
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(username);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                // Unknown command
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Unknown command");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBotUsername() {
        // TODO
        // Return bot username
        return "EventMeBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "461749335:AAGRiMG5X4sDMkCVHqDnL4XB22N2ff8tvXc";
    }
}
