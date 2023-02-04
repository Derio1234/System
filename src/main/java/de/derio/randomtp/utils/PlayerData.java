package de.derio.randomtp.utils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {
    private File fb;
    private File dir;
    private YamlConfiguration config;
    public PlayerData(UUID uuid) {
        dir = new File("./plugins/rtp/savings");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        fb = new File(dir, uuid.toString() +".yml");
        try {
            fb.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(fb);

    }
    public void setNextUse(long cooldown){
        config.set("cooldown", System.currentTimeMillis()+cooldown);
        try {
            config.save(fb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double getCooldown(){
        return config.getDouble("cooldown");
    }
}

