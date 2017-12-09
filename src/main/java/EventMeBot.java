import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class EventMeBot extends TelegramLongPollingBot{

    public static String mainEvent = "Event Name: \n";
    public static String mainLocation = "Location: \n";
    public static String mainDate = "Date: \n";
    public static String mainTime = "Time: \n";
    public static String mainAttendees = "Attending:\n \n";
    public static String mainMaybes = "Maybe Attending:\n \n";
    public static String mainNonAttendees = "Not Attending:\n \n";

    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();

            if (message_text.equals("/start")) {
                // User send /start
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
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
