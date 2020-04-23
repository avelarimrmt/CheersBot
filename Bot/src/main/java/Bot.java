import bl.HibernateUtil;
import com.vdurmont.emoji.EmojiParser;

import models.ProfessionEntity;
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
import service.ProfessionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;

//Смайлики, используемые в сообщениях и кнопках
enum Icon {

    GLASS(":champagne:"),
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

    public void sendMessageReply (Message message, String text){
        SendMessage s = new SendMessage();
        s.enableMarkdown(true); //разметка
        s.setChatId(message.getChatId().toString()); //Определяем, в какой чат отправляем ответ
        s.setReplyToMessageId(message.getMessageId()); //Определяем, на какое сообщение отвечаем
        s.setText(text);
        //String msg = "SELECT value_toast FROM toasts ORDER BY RANDOM() limit 1;";
        setMainButtons(s);

        try{
            //setMainButtons(s); //появление клавиатуры после сообщения
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
        /*ProfessionService professionService = new ProfessionService();
        ProfessionEntity prof = null;
        try {
            prof = professionService.getById(2L);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(prof);
        HibernateUtil.shutdown();*/
        if(message != null && message.hasText()){
            Long chatId = update.getMessage().getChatId();
            switch(message.getText()){
                case "Сгенерировать тост":
                    try {
                        execute(sendProfessionInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                /*case "\uD83C\uDF7EМои тосты":
                    sendMessageReply(message, "Это ваши сохраненные тосты");
                    break;
                case "⭐Избранное":
                    sendMessageReply(message, "Здесь ваши самые любимые тосты!");
                    break;
                case "\uD83C\uDFC6ТОП тостов":
                    sendMessageReply(message, "Посмотрите самые популярные тосты за месяц!");
                    break;
                case "✍Добавить свой тост":
                    sendMessageReply(message, "Напишите свой тост и добавьте его для всех пользователей!");
                    break;
                case "✌Мой рейтинг":
                    sendMessageReply(message, "Здесь ваш рейтинг тостов");
                    break;*/
                case "\uD83D\uDE4FПомощь":
                    sendMessageReply(message, "Чтобы получить тост:\n" +
                            "1)\tнажмите кнопку «Сгенерировать тост»\n" +
                            "2)\tзатем выберите профессию, кликнув по нужному номеру профессии\n" +
                            "3)\tдалее выберите жанр, кликнув по номеру жанра или нажмите на кнопку «РАНДОМ», чтобы не ограничиваться жанром.\n" +
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
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            CallbackQuery d = update.getCallbackQuery();
            EditMessageText new_message = sendGenreInlineKeyBoardMessage(chat_id).setMessageId(toIntExact(message_id));
            String text = d.getData();
            ProfessionEntity messageProf = new ProfessionEntity();
            String textMessage;
            switch (text){
                case "Полицейский":
                    SqlRequests.findProfession(text);
                    //SqlRequests.findProfession(d.getData());
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
                case "Стихи":
                    textMessage = SqlRequests.findGenre("Стихи");
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
/*        KeyboardRow keyboard2Row = new KeyboardRow();
        KeyboardRow keyboard3Row = new KeyboardRow();
        KeyboardRow keyboard4Row = new KeyboardRow();
        KeyboardRow keyboard5Row = new KeyboardRow();*/
        KeyboardRow keyboard6Row = new KeyboardRow();

        keyboard1Row.add(new KeyboardButton("Сгенерировать тост"));
/*        keyboard2Row.add(new KeyboardButton(Icon.GLASS.get() + "Мои тосты"));
        keyboard2Row.add(new KeyboardButton(Icon.STAR.get() + "Избранное"));
        keyboard3Row.add(new KeyboardButton(Icon.CUP.get() + "ТОП тостов"));
        keyboard4Row.add(new KeyboardButton(Icon.WRITE.get() + "Добавить свой тост"));
        keyboard5Row.add(new KeyboardButton(Icon.PEACE.get() + "Мой рейтинг"));*/
        keyboard6Row.add(new KeyboardButton(Icon.HELP.get() + "Помощь"));

        keyboardRowList.add(keyboard1Row);
/*        keyboardRowList.add(keyboard2Row);
        keyboardRowList.add(keyboard3Row);
        keyboardRowList.add(keyboard4Row);
        keyboardRowList.add(keyboard5Row);*/
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
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList); //добавляем кнопки в разметку инлайн клавиатуры
        return new SendMessage().setChatId(chatId).setText("Выбери ПРОФЕССИЮ из списка:\n1. Полицейский\n2. Программист\n3. Врач\n4. Бухгалтер").setReplyMarkup(inlineKeyboardMarkup); //добавлем разметку в сообщение
    }

    public static EditMessageText sendGenreInlineKeyBoardMessage(long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("1").setCallbackData("Стихи"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("2").setCallbackData("Диалог"));
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("РАНДОМ").setCallbackData("Рандом"));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList); //добавляем кнопки в разметку инлайн клавиатуры
        return new EditMessageText().setText("Выбери ЖАНР из списка:\n1. Стихи \n2. Диалог").setChatId(chatId).setReplyMarkup(inlineKeyboardMarkup); //добавлем разметку в сообщение
    }

    public String getBotUsername() {
        return "ProCheersBot";
    }

    public String getBotToken() {
        return "1079943231:AAGrKBrtMtI5GR0ETd-lvygZLFSwxchODNs";
    }
}
