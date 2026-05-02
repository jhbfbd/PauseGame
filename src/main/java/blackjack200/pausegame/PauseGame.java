package blackjack200.pausegame;

import cn.nukkit.plugin.PluginBase;

public final class PauseGame extends PluginBase {

    @Override
    public void onEnable() {
        getServer().getCommandMap().register(getName(), new PauseGameCommand());
        getLogger().info("PauseGame enabled");
    }
}
