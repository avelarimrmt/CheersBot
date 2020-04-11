import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;

//Смайлики, используемые в сообщениях и кнопках
enum Icon {

    GLASSES(":clinking_glass:"),
    STAR(":star:"),
    CUP(":trophy:"),
    WRITE(":writing_hand:"),
    PEACE(":v:"),
    HELP(":pray:");

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Icon(String value) {
        this.value = value;
    }
    }


public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args){
        ApiContextInitializer.init(); //инициализаруем АПИ
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        //Регистрируем Бота
        try{
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage s = new SendMessage();
        s.enableMarkdown(true); //разметка
        s.setChatId(message.getChatId().toString()); //Определяем, в какой чат отправляем ответ
        s.setReplyToMessageId(message.getMessageId()); //Определяем, на какое сообщение отвечаем
        s.setText(text);
        //String msg = "SELECT value_toast FROM toasts ORDER BY RANDOM() limit 1;";
        
        try{
            setButtons(s);
            execute(s);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    //Имплементированый метод Bot
    //Для приема сообщений. Получение обновлений через LongPool (не через веб хук)

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch(message.getText()){
                case "/help":
                    sendMsg(message, "Чем могу помочь?");
                            break;
                case "/settings":
                        sendMsg(message, "Что будем настраивать?");
                        break;
                default:
                    sendMsg(message, "Привет!");
                    break;
            }
        }
    }

    public void setButtons (SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup); //связываем сообщение с клавиатурой
        keyboardMarkup.setSelective(true); //для всех пользователей
        keyboardMarkup.setResizeKeyboard(true); //подгонка размера клавиатуры под количество кнопок
        keyboardMarkup.setOneTimeKeyboard(false); //не скрывать клавиатуру

        ArrayList<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboard1Row = new KeyboardRow();
        KeyboardRow keyboard2Row = new KeyboardRow();
        KeyboardRow keyboard3Row = new KeyboardRow();
        KeyboardRow keyboard4Row = new KeyboardRow();
        KeyboardRow keyboard5Row = new KeyboardRow();
        KeyboardRow keyboard6Row = new KeyboardRow();

        keyboard1Row.add(new KeyboardButton(Icon.GLASSES.get() + "Мои тосты"));
        keyboard2Row.add(new KeyboardButton(Icon.STAR.get() + "Избранное"));
        keyboard3Row.add(new KeyboardButton(Icon.CUP.get() + "ТОП тостов"));
        keyboard4Row.add(new KeyboardButton(Icon.WRITE.get() + "Добавить свой тост"));
        keyboard5Row.add(new KeyboardButton(Icon.PEACE.get() + "Мой рейтинг"));
        keyboard6Row.add(new KeyboardButton(Icon.HELP.get() + "Помощь"));

        keyboardRowList.add(keyboard1Row);
        keyboardRowList.add(keyboard2Row);
        keyboardRowList.add(keyboard3Row);
        keyboardRowList.add(keyboard4Row);
        keyboardRowList.add(keyboard5Row);
        keyboardRowList.add(keyboard6Row);
        keyboardMarkup.setKeyboard(keyboardRowList);

        //Инлайн Клавиатура
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        //inlineKeyboardButton.setText()
    }

    public String getBotUsername() {
        return "ProCheersBot";
    }

    public String getBotToken() {
        return "1079943231:AAGrKBrtMtI5GR0ETd-lvygZLFSwxchODNs";
    }
}
