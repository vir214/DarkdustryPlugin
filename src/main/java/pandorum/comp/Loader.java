package pandorum.comp;

import arc.Events;
import arc.util.Reflect;
import mindustry.core.NetServer;
import mindustry.game.EventType.*;
import mindustry.net.Administration;
import mindustry.net.Packets.ConnectPacket;
import pandorum.PandorumPlugin;
import pandorum.discord.BotMain;
import pandorum.events.*;
import pandorum.events.filters.ActionFilter;
import pandorum.events.filters.ChatFilter;
import pandorum.events.handlers.ConnectPacketHandler;
import pandorum.events.handlers.InvalidCommandResponse;
import pandorum.events.handlers.MenuHandler;

import static mindustry.Vars.net;
import static mindustry.Vars.netServer;

public class Loader {

    public static void init() {
        PandorumPlugin.writeBuffer = Reflect.get(NetServer.class, netServer, "writeBuffer");
        PandorumPlugin.outputBuffer = Reflect.get(NetServer.class, netServer, "outputBuffer");

        net.handleServer(ConnectPacket.class, ConnectPacketHandler::handle);

        netServer.admins.addActionFilter(ActionFilter::filter);
        netServer.admins.addChatFilter(ChatFilter::filter);
        netServer.invalidHandler = InvalidCommandResponse::response;

        Events.on(AdminRequestEvent.class, AdminRequestListener::call);
        Events.on(BlockBuildEndEvent.class, BlockBuildEndListener::call);
        Events.on(BuildSelectEvent.class, BuildSelectListener::call);
        Events.on(ConfigEvent.class, ConfigListener::call);
        Events.on(DepositEvent.class, DepositListener::call);
        Events.on(GameOverEvent.class, GameOverListener::call);
        Events.on(PlayerJoin.class, PlayerJoinListener::call);
        Events.on(PlayerLeave.class, PlayerLeaveListener::call);
        Events.on(ServerLoadEvent.class, ServerLoadListener::call);
        Events.on(TapEvent.class, TapListener::call);
        Events.on(WithdrawEvent.class, WithdrawListener::call);
        Events.on(WorldLoadEvent.class, WorldLoadListener::call);
        Events.run(Trigger.update, TriggerUpdateListener::update);

        Administration.Config.motd.set("off");
        Administration.Config.interactRateWindow.set(3);
        Administration.Config.interactRateLimit.set(50);
        Administration.Config.interactRateKick.set(1000);
        Administration.Config.showConnectMessages.set(false);
        Administration.Config.strict.set(true);
        Administration.Config.enableVotekick.set(true);

        MenuHandler.init();
        Icons.init();
        BotMain.start();
    }
}
