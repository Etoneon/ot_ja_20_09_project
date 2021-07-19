package weathertelegrambot.ability;

import weathertelegrambot.bot.Bot;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;

@ToString
public class Notify implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Notify.class);
    private static final int MILLISEC_IN_SEC = 1000;

    Bot bot;
    long delayInMillisec;
    String chatID;

    public Notify(Bot bot, String chatID, long delayInMillisec) {
        this.bot = bot;
        this.chatID = chatID;
        this.delayInMillisec = delayInMillisec;
        logger.debug("CREATE. " + toString());
    }

    @Override
    public void run() {
        logger.info("RUN. " + toString());
        bot.sendQueue.add(getFirstMessage());
        try {
            Thread.sleep(delayInMillisec);
            bot.sendQueue.add(Stickers.FUNNY_JIM_CARREY.getSendSticker(chatID));
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("FIHISH. " + toString());
    }

    private SendMessage getFirstMessage() {
        return new SendMessage(chatID, "I will send you notify after " + delayInMillisec / MILLISEC_IN_SEC + "sec");
    }

    private SendMessage getSecondMessage() {
        return new SendMessage(chatID, "This is notify message. Thanks for using :)");
    }
}