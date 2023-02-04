package de.derio.randomtp.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
public class Config {
    private File dir;
    private File fb;
    private YamlConfiguration config;
    public Config() {
        dir = new File("./plugins/rtp");
        if (!dir.exists()){
            dir.mkdirs();
        }
        fb = new File(dir, "config.yml");
        try {
            fb.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(fb);
    }
    public int getMsg(String path){
        return config.getInt(path);
    }
    public int getCooldown(){
        return config.getInt("cooldown");
    }
    public String getLang(){
        return config.getString("lang");
    }
    public void setLang(){
        if (config.getBoolean("first")){
            return;
        }
        config.set("lang" ,"en");
        config.set("Supported langugages:", "en,de");
        config.set("Boundx", 1000);
        config.set("Boundxnegative", -1000);
        config.set("Boundz", 1000);
        config.set("Boundznegative", -1000);
        config.set("cooldown", 300000);
        config.set("first", true);
        save();
        return;
    }
    public void save(){
        try {
            config.save(fb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
