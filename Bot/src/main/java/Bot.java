import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;

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
        KeyboardRow keyboardFstRow = new KeyboardRow();

        keyboardFstRow.add(new KeyboardButton("/help")); //добавляем первую кнопку
        keyboardFstRow.add(new KeyboardButton("/settings")); //добавляем вторую кнопку

        keyboardRowList.add(keyboardFstRow);
        keyboardMarkup.setKeyboard(keyboardRowList);

    }

    public String getBotUsername() {
        return "ProCheersBot";
    }

    public String getBotToken() {
        return "1079943231:AAGrKBrtMtI5GR0ETd-lvygZLFSwxchODNs";
    }
}
