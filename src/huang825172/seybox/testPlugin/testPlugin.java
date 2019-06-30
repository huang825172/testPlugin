package huang825172.seybox.testPlugin;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class testPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new myListener(), this);
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        Bukkit.getConsoleSender().sendMessage(config.getString("health"));
        runTask();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //return super.onCommand(sender, command, label, args);
        if (command.getName().equalsIgnoreCase("hello") && args[0] != null) {
            Bukkit.broadcastMessage(sender.getName() + " just say Hello! and " + args[0]);
            return true;
        }
        if (command.getName().equalsIgnoreCase("demo")) {
            Player p = Bukkit.getPlayer(sender.getName());
            Location location = p.getLocation();
            runPar(p);
            p.chat("boom");
            p.setMaxHealth(20);
            p.setHealth(20);
            return true;
        }
        return false;
    }

    public void runTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getItemInHand().isSimilar(item.getBook())) {
                        if (player.getHealthScale() - player.getHealth() > 1) {
                            player.setHealth(player.getHealth() + 1);
                        } else {
                            player.sendMessage(ChatColor.YELLOW + getName() + "你很健康");
                        }
                    }
                }
            }
        }.runTaskTimer(this, 10, 10);
    }

    public void runPar(final Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                double r = 1;
                double y = 0;
                Location location = p.getLocation();
                for (double degree = 0; degree < 360 * 2; degree += 3) {
                    double radians = Math.toRadians(degree);
                    double x = r * Math.cos(radians);
                    double z = r * Math.sin(radians);
                    location = Bukkit.getPlayer(p.getName()).getLocation();
                    location.add(x, y, z);

                    if(Math.random()*2 > 0.5){
                        p.spawnParticle(Particle.VILLAGER_HAPPY, location, 1);
                    } else {
                        p.spawnParticle(Particle.DRIP_LAVA, location,1);
                    }
                    y += 0.013;
                    r += 0.01;
                    try {
                        Thread.sleep(3);
                    }catch (Exception e){

                    }
                }
            }
        }.run();
    }
}
