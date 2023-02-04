package de.derio.randomtp;

import de.derio.randomtp.commands.RTP;
import de.derio.randomtp.utils.Config;
import de.derio.randomtp.utils.Languages;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;

    @Override
    public void onEnable() {
        getCommand("randomteleport").setExecutor(new RTP());
        plugin = this;
        Config config = new Config();
        Languages lang = new Languages();
        lang.setFirst();
        config.setLang();

    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin() {
        return plugin;
    }
}
