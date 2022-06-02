package pandorum.listeners.filters;

import mindustry.net.Administration.ActionFilter;
import mindustry.net.Administration.ActionType;
import mindustry.net.Administration.PlayerAction;
import pandorum.features.history.entry.HistoryEntry;
import pandorum.features.history.entry.RotateEntry;

import static pandorum.PluginVars.*;

public class ActionManager implements ActionFilter {

    public boolean allow(PlayerAction action) {
        if (historyEnabled() && action.type == ActionType.rotate) {
            HistoryEntry entry = new RotateEntry(action);
            history.putLinkedTiles(action.tile, entry);
        }

        return true;
    }
}
