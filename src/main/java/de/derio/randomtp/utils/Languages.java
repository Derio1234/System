package de.derio.randomtp.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Languages {
    private File dir;
    private File fb;
    private File fbe;
    private YamlConfiguration de;
    private YamlConfiguration en;
    public Languages() {
        dir = new File("./plugins/rtp/lang");
        if (!dir.exists()){
            dir.mkdirs();
        }
        fb = new File(dir, "de.yml");
        fbe = new File(dir, "en.yml");
        try {
            fb.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fbe.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        de = YamlConfiguration.loadConfiguration(fb);
        en = YamlConfiguration.loadConfiguration(fbe);
    }
    public void setFirst(){
            if (!de.isSet("title.title")) {
                de.set("title.title", "&aRandomTeleport");
            }
            if (!en.isSet("title.title")) {
                en.set("title.title", "&aRandomTeleport");
            }
            if (!de.isSet("title.subtitle")) {
                de.set("title.subtitle",  "&r[x] [y] [z]");
            }
            if (!en.isSet("title.subtitle")) {
                en.set("title.subtitle", "&r[x] [y] [z]");
                }
            if (!de.isSet("msg.onteleport")) {
                de.set("msg.onteleport",  "&aDu wurdest teleportiert");
            }
            if (!en.isSet("msg.onteleport")) {
                en.set("msg.onteleport", "&aYou have been teleported!");
            }
            if (!de.isSet("msg.noperm")) {
                de.set("msg.noperm",  "&cDazu hast du keine Rechte!");
            }
            if (!en.isSet("msg.noperm")) {
                en.set("msg.noperm", "&cYou dont have the permission to execute this command");
            }
            if (!de.isSet("msg.oncooldown")) {
                de.set("msg.oncooldown", "&7Du kannst diesen Befehl erst in &a[0] &7Sekunden erneut ausführen");
            }
            if (!en.isSet("msg.oncooldown")) {
                en.set("msg.oncooldown", "&7You can´t use this command, please wait &a[0] &7seconds");
            }
            if (!de.isSet("msg.onreload")) {
                de.set("msg.onreload", "&aDie Dateien wurden neu geladen!");
            }
            if (!en.isSet("msg.onreload")) {
                en.set("msg.onreload", "&athe Configuration has been reloaded!");
            }
            save();
    }
    public String getMsgDE(String path){
        Config config = new Config();
        if (config.getLang() == null){
            return en.getString(path);
        }
        switch (config.getLang()){
            case "de":
                return de.getString(path);
            default:
                return en.getString(path);
        }
    }
    public String getMsgEN(String path){
        Config config = new Config();
        if (config.getLang() == null){
            return en.getString(path);
        }
        switch (config.getLang()){
            case "de":
                return de.getString(path);
            default:
                return en.getString(path);
        }
    }
    public void reloadConfig(){
        if(!fb.exists()) {
            try {
                fb.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!fbe.exists()) {
            try {
                fbe.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        en = YamlConfiguration.loadConfiguration(fbe);
        de = YamlConfiguration.loadConfiguration(fb);
    }
    public void save(){
        try {
            de.save(fb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            en.save(fbe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
