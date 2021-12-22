package pandorum.events.filters;

import arc.struct.Seq;
import mindustry.net.Administration.ActionType;
import mindustry.net.Administration.PlayerAction;
import mindustry.world.Tile;
import pandorum.PandorumPlugin;
import pandorum.entry.HistoryEntry;
import pandorum.entry.RotateEntry;

public class ActionFilter {

    public static boolean filter(final PlayerAction action) {
        if (PandorumPlugin.config.historyEnabled() && action.type == ActionType.rotate) {
            HistoryEntry entry = new RotateEntry(action);
            Seq<Tile> linkedTiles = action.tile.getLinkedTiles(new Seq<>());
            for (Tile tile : linkedTiles) {
                PandorumPlugin.history[tile.x][tile.y].add(entry);
            }
        }
        return true;
    }
}
