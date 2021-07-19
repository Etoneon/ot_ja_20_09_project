package weathertelegrambot.handler;

import weathertelegrambot.bot.Bot;
import weathertelegrambot.command.ParsedCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.objects.Update;

public class DefaultHandler extends AbstractHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultHandler.class);

    public DefaultHandler(Bot bot) {
        super(bot);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        return "";
    }
}
