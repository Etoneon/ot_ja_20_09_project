package weathertelegrambot.handler;

import weathertelegrambot.bot.Bot;
import weathertelegrambot.command.ParsedCommand;
import org.telegram.telegrambots.api.objects.Update;

public abstract class AbstractHandler {
    Bot bot;

    AbstractHandler(Bot bot) {
        this.bot = bot;
    }

    public abstract String operate(String chatId, ParsedCommand parsedCommand, Update update);
}
