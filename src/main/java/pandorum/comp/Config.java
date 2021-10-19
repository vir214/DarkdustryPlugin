package pandorum.comp;

import arc.util.Strings;
import pandorum.struct.Tuple2;

public class Config {

    public int alertDistance = 150;

    /** Необходимое количество игроков для успешного завершения голосования. */
    public float voteRatio = 0.6f;

    /** Ёмкость массива, хранящего информацию о действиях с тайлом. Может сильно влиять на трату ОЗУ */
    public int historyLimit = 6;

    /** Время, через которое запись в истории тайла будет удалена. По умолчанию 30 минут. Записывается в миллисекундах */
    public long expireDelay = 1800000;

    /** Время голосования через /nominate. В секундах */
    public float voteDuration = 150f;

    /** Время голосования через /votekick. В секундах */
    public float votekickDuration = 40f;

    public String hubIp = "darkdustry.ml:6567";

    public PluginType type = PluginType.def;  

    public String DiscordWebhookLink = null;

    public Tuple2<String, Integer> parseIp() {
        String ip = hubIp;
        int port = 6567;
        if (ip.contains(":") && Strings.canParsePositiveInt(ip.split(":")[1])) {
            String[] parts = ip.split(":");
            ip = parts[0];
            port = Strings.parseInt(parts[1]);
        }
        return Tuple2.of(ip, port);
    }

    public enum PluginType {
        /** Тип для серверов с режимом выживания или атаки */
        def,

        /** Тип для PvP серверов */
        pvp,

        /** Тип для серверов с режимом песочницы */
        sand,

        /** Тип для любого другого сервера */
        other
    }
}
