package pandorum.commands.discord;

import arc.util.Strings;
import mindustry.maps.Map;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import pandorum.models.MapModel;
import pandorum.util.Utils;

import java.awt.*;

import static pandorum.discord.Bot.err;
import static pandorum.util.Search.findMap;

public class MapCommand {
    public static void run(final String[] args, final Message message) {
        Map map = findMap(args[0]);
        if (map == null) {
            err(message.getChannel(), ":mag: карта не найдена.", "Проверьте правильность ввода.");
            return;
        }

        MapModel.find(map, mapModel -> {
            try {
                message.getChannel().sendMessageEmbeds(new EmbedBuilder()
                        .setColor(Color.yellow)
                        .setTitle(Strings.format(":map: @", Strings.stripColors(map.name())))
                        .setFooter(Strings.format("@x@", map.width, map.height))
                        .setDescription(Strings.stripColors(map.description()))
                        .setAuthor(Strings.stripColors(map.author()))
                        .addField(":mailbox_with_mail: рейтинг:", Strings.format(":green_circle: @ | @ :red_circle:", mapModel.upVotes, mapModel.downVotes), true)
                        .addField(":clock1: время игры:", Strings.format("@ минут", Utils.secondsToMinutes(mapModel.playTime)), true)
                        .addField(":100: лучшая волна:", String.valueOf(mapModel.bestWave), true)
                        .addField(":checkered_flag: игр сыграно:", String.valueOf(mapModel.gamesPlayed), true)
                        .build()).addFile(map.file.file()).queue();
            } catch (Exception e) {
                err(message.getChannel(), ":x: ошибка.", "Получить карту с сервера не удалось.");
            }
        });
    }
}
