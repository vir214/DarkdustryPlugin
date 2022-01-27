package pandorum.models;

import arc.func.Cons;
import com.mongodb.BasicDBObject;
import mindustry.maps.Map;
import pandorum.database.MongoDataBridge;

public class MapModel extends MongoDataBridge<MapModel> {

    public String name;

    public int upVotes = 0;
    public int downVotes = 0;

    public long playTime = 0L;
    public int gamesPlayed = 0;
    public int bestWave = 0;

    public static void find(Map map, Cons<MapModel> cons) {
        if (map != null) find(map.name(), cons);
    }

    public static void find(String name, Cons<MapModel> cons) {
        findAndApplySchema(MapModel.class, new BasicDBObject("name", name), cons);
    }
}
