import com.vdurmont.emoji.EmojiParser;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
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
import java.util.List;

import static java.lang.Math.toIntExact;



//Смайлики, используемые в сообщениях и кнопках
enum Icon {

    GLASS(":champagne:"),
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

    public void sendMessageReply (Message message, String text){
        SendMessage s = new SendMessage();
        s.enableMarkdown(true); //разметка
        s.setChatId(message.getChatId().toString()); //Определяем, в какой чат отправляем ответ
        s.setReplyToMessageId(message.getMessageId()); //Определяем, на какое сообщение отвечаем
        s.setText(text);
        setMainButtons(s);

        try{
            execute(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Имплементированый метод Bot
    //Для приема сообщений. Получение обновлений через LongPool (не через веб хук)
    //Обработчик сообщений
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()){
            Long chatId = update.getMessage().getChatId();
            switch(message.getText()){
                case "\uD83C\uDF7EСгенерировать тост":
                    try {
                        execute(sendProfessionInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "\uD83D\uDE4FПомощь":
                    sendMessageReply(message, "Чтобы получить тост:\n" +
                            "1)\tнажмите кнопку «Сгенерировать тост»\n" +
                            "2)\tзатем выберите профессию, кликнув по нужному номеру профессии\n" +
                            "3)\tдалее выберите жанр, кликнув по номеру жанра.\n" +
                            "По всем вопросам и пожеланиям пишите @mrm\\_avelari");
                    break;
                case "/":
                    try {
                        execute(sendGenreInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    sendMessageReply(message, "Привет! Нажми кнопку в меню \"Сгенерировать тост\"");
                    break;
            }
        } else if (update.hasCallbackQuery())
        {
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            CallbackQuery d = update.getCallbackQuery();
            EditMessageText new_message = sendGenreInlineKeyBoardMessage(chat_id).setMessageId(toIntExact(message_id));
            String text = d.getData();
            String textMessage;
            switch (text){
                case "Полицейский":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Полицейский, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Программист":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Программист, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Врач":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Врач, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Бухгалтер":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Бухгалтер, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Учитель":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Учитель, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Строитель":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Строитель, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Инженер":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Инженер, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Психолог":
                    SqlRequests.findProfession(text);
                    try {
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    try {
                        execute(new SendMessage().setText("Вы выбрали профессию Психолог, теперь выберите жанр тоста выше")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Стихи":
                case "Диалог":
                case "Душевные":
                    textMessage = SqlRequests.findGenre(text);
                    try {
                        execute(new SendMessage().setText(textMessage)
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case "РАНДОМ":
                    textMessage = SqlRequests.getRandomGenre();
                    try {
                        execute(new SendMessage().setText(textMessage)
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public void setMainButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(keyboardMarkup); //связываем сообщение с клавиатурой
        keyboardMarkup.setSelective(true); //для всех пользователей
        keyboardMarkup.setResizeKeyboard(true); //подгонка размера клавиатуры под количество кнопок
        keyboardMarkup.setOneTimeKeyboard(false); //не скрывать клавиатуру

        ArrayList<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboard1Row = new KeyboardRow();
        KeyboardRow keyboard6Row = new KeyboardRow();

        keyboard1Row.add(new KeyboardButton(Icon.GLASS.get() + "Сгенерировать тост"));
        keyboard6Row.add(new KeyboardButton(Icon.HELP.get() + "Помощь"));

        keyboardRowList.add(keyboard1Row);
        keyboardRowList.add(keyboard6Row);
        keyboardMarkup.setKeyboard(keyboardRowList);
    }

    public static SendMessage sendProfessionInlineKeyBoardMessage(long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("1").setCallbackData("Полицейский"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("2").setCallbackData("Программист"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("3").setCallbackData("Врач"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("4").setCallbackData("Бухгалтер"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("5").setCallbackData("Учитель"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("6").setCallbackData("Строитель"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("7").setCallbackData("Инженер"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("8").setCallbackData("Психолог"));
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList); //добавляем кнопки в разметку инлайн клавиатуры
        return new SendMessage()
                .setChatId(chatId)
                .setText("Выбери ПРОФЕССИЮ из списка:\n1. Полицейский\n2. Программист\n3. Врач\n4. Бухгалтер\n5. Учитель\n6. Строитель\n7. Инженер\n8. Психолог")
                .setReplyMarkup(inlineKeyboardMarkup); //добавлем разметку в сообщение
    }

    public static EditMessageText sendGenreInlineKeyBoardMessage(long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("1").setCallbackData("Стихи"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("2").setCallbackData("Диалог"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("3").setCallbackData("Душевные"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("РАНДОМ").setCallbackData("РАНДОМ"));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList); //добавляем кнопки в разметку инлайн клавиатуры
        return new EditMessageText().setText("Выбери ЖАНР из списка:\n1. Стихи \n2. Диалог \n3. Душевные").setChatId(chatId).setReplyMarkup(inlineKeyboardMarkup); //добавлем разметку в сообщение
    }

    public String getBotUsername() {
        return "ProCheersBot";
    }

    public String getBotToken() {
        return "1079943231:AAGrKBrtMtI5GR0ETd-lvygZLFSwxchODNs";
    }
}
