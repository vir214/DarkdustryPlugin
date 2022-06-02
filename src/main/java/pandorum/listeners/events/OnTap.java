package pandorum.listeners.events;

import arc.func.Cons;
import mindustry.game.EventType.TapEvent;
import pandorum.components.Bundle;
import pandorum.features.history.entry.HistoryEntry;

import static pandorum.PluginVars.*;
import static pandorum.util.Search.findLocale;

public class OnTap implements Cons<TapEvent> {

    public void get(TapEvent event) {
        if (historyEnabled() && activeHistoryPlayers.contains(event.player.uuid()) && event.tile != null) {
            history.getAll(event.tile.x, event.tile.y, historyEntries -> {
                StringBuilder historyString = new StringBuilder(Bundle.format("history.title", findLocale(event.player.locale), event.tile.x, event.tile.y));

                for (HistoryEntry entry : historyEntries) {
                    historyString.append("\n").append(entry.getMessage(event.player));
                }

                if (historyEntries.isEmpty()) {
                    historyString.append(Bundle.format("history.empty", findLocale(event.player.locale)));
                }

                event.player.sendMessage(historyString.toString());
            });
        }
    }
}
