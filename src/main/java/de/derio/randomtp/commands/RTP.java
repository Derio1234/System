package de.derio.randomtp.commands;

import de.derio.randomtp.Main;
import de.derio.randomtp.utils.Config;
import de.derio.randomtp.utils.Languages;
import de.derio.randomtp.utils.PlayerData;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class RTP implements CommandExecutor {
    Config config = new Config();
    protected int cooldown = config.getCooldown();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config.setLang();
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        double y = 320;
        double x = getRandomNumberUsingInts(config.getMsg("Boundxnegative"),config.getMsg("Boundx")) + 0.5;
        double z = getRandomNumberUsingInts(config.getMsg("Boundxnegative"), config.getMsg("Boundx")) + 0.5;
        PlayerData data = new PlayerData(p.getUniqueId());
        Languages msg = new Languages();
        String leftcooldown = String.valueOf((int) ((data.getCooldown() - System.currentTimeMillis()) / 1000));
                if (p.hasPermission("rtp.use")) {
                    if (data.getCooldown() > System.currentTimeMillis()) {

                            p.sendMessage(msg.getMsgDE("msg.oncooldown").replace("[0]",leftcooldown).replace("&", "§"));

                        return false;
                    }
                    for (int i = 320; i >= -63; i--) {
                        if (new Location(p.getWorld(), x, i - 1, z).getBlock().getType() == Material.AIR) {
                            
                        } else {
                            Chunk chunk = new Location(p.getWorld(), x, y, z).getChunk();
                            chunk.load(true);
                            p.teleport(new Location(p.getWorld(), x, i, z));
                            p.sendTitle(msg.getMsgDE("title.title").replace("&","§"), msg.getMsgDE("title.subtitle").replace("&","§").replace("[x]", String.valueOf(x)).replace("[y]", String.valueOf(y)).replace("[z]", String.valueOf(z)), 20, 20, 20);
                            data.setNextUse(config.getCooldown());
                            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                            i = -63;
                            p.sendMessage(msg.getMsgDE("msg.onteleport").replace("&","§"));
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    chunk.unload();
                                }
                            }.runTaskLater(Main.getPlugin(), 60);
                        }


                    }


                    return false;
                }else {
                    p.sendMessage(msg.getMsgDE("msg.noperm").replace("&","§"));
                }



        return false;
    }
    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}

