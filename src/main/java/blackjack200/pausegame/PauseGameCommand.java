package blackjack200.pausegame;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.network.protocol.LevelEventPacket;
import cn.nukkit.utils.TextFormat;

import java.util.Locale;

public final class PauseGameCommand extends Command {

    public PauseGameCommand() {
        super("pausegame", "Pause player's game", "/pausegame <pause|resume> <player>", new String[]{"pg"});
        setPermission("pausegame.pause");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(getUsage());
            return true;
        }

        String action = args[0].toLowerCase(Locale.ROOT);
        if (!action.equals("pause") && !action.equals("resume") && !action.equals("p") && !action.equals("r")) {
            sender.sendMessage(getUsage());
            return true;
        }

        String targetName = args[1];
        Player target = Server.getInstance().getPlayer(targetName);
        if (target == null) {
            sender.sendMessage(TextFormat.RED + "Player '" + targetName + "' is not online");
            return true;
        }

        LevelEventPacket packet = new LevelEventPacket();
        packet.evid = LevelEventPacket.EVENT_GLOBAL_PAUSE;
        packet.data = (action.equals("pause") || action.equals("p")) ? 1 : 0;
        packet.x = 0;
        packet.y = 0;
        packet.z = 0;
        target.dataPacket(packet);

        sender.sendMessage(TextFormat.GREEN + "Success " + action + " Player '" + target.getName() + "' Game");
        return true;
    }
}
