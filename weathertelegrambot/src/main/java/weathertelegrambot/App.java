package weathertelegrambot;

import weathertelegrambot.bot.Bot;
import weathertelegrambot.service.MessageReciever;
import weathertelegrambot.service.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final int PRIORITY_FOR_SENDER = 1;
    private static final int PRIORITY_FOR_RECEIVER = 3;
    private static final String BOT_ADMIN = "1751261250";

    public static void main(String[] args) {
        ApiContextInitializer.init();

        Bot weatherTellingBot = new Bot("weather_telling_bot", "1905552468:AAG3P7y2By2wy5pIjcp4Xf_8Ju_uldckSK8");

        MessageReciever messageReciever = new MessageReciever(weatherTellingBot);
        MessageSender messageSender = new MessageSender(weatherTellingBot);

        weatherTellingBot.botConnect();

        Thread receiver = new Thread(messageReciever);
        receiver.setDaemon(true);
        receiver.setName("MsgReciever");
        receiver.setPriority(PRIORITY_FOR_RECEIVER);
        receiver.start();

        Thread sender = new Thread(messageSender);
        sender.setDaemon(true);
        sender.setName("MsgSender");
        sender.setPriority(PRIORITY_FOR_SENDER);
        sender.start();

        sendStartReport(weatherTellingBot);
    }

    private static void sendStartReport(Bot bot) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(BOT_ADMIN);
        sendMessage.setText("Запустился");
        bot.sendQueue.add(sendMessage);
    }


}

