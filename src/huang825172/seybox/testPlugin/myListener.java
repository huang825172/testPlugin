package huang825172.seybox.testPlugin;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class myListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.broadcastMessage("§bWelcome to the server!"+ event.getPlayer().getName() +"From testPlugin.");
    }

    @EventHandler
    public void blockbreak(BlockBreakEvent event){
        //event.getPlayer().sendMessage(event.getPlayer().getName() + " just break "+ String.valueOf(event.getBlock().getLocation().getX()));
        Bukkit.getWorld("world").createExplosion(event.getBlock().getLocation(),2);
        Player p = event.getPlayer();
        Block b = event.getBlock();
        p.getInventory().addItem(new ItemStack(b.getType()));
        p.teleport(b.getLocation().multiply(2));
    }

    @EventHandler
    public void changeHand(PlayerDropItemEvent event){
        Player p = event.getPlayer();
        //出现时间，停止时间，消失时间 b蓝 a绿
        p.sendTitle("§bHello","§asubtitle",10,30,10);
        p.sendMessage("You just change hand");
        p.setLevel(100);
        p.setBedSpawnLocation(event.getPlayer().getLocation());
        BaseComponent[] bc = new ComponentBuilder("liaotiankuang")
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("xuan ting").create())).create();
        p.spigot().sendMessage(bc);
        for(int i=0; i<p.getInventory().getSize();i++){
            if(!p.getInventory().contains(item.getBook())){
                p.getInventory().addItem(item.getBook());
                p.sendMessage(ChatColor.AQUA+"你获得了回血书");
            }
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"time set day");
        Inventory inv = Bukkit.createInventory(null, 54, "交易");
        ItemStack itemStack = new ItemStack(Material.APPLE);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§b传送苹果");
        ArrayList lore = new ArrayList();
        lore.add("§a点击你就传送");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);

        inv.addItem(itemStack);
        inv.setItem(16,itemStack);
        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        if(event.getRawSlot() == 0){
            Player player = (Player)event.getWhoClicked();
            player.teleport(player.getLocation().add(1,4,1));
            event.setCancelled(true);
            player.closeInventory();
        }
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        p.spawnParticle(Particle.DRIP_LAVA,p.getLocation().add(0,1,0),10);
        p.spawnParticle(Particle.DRIP_WATER,p.getLocation().add(0,2,0),10);
        //p.getWorld().getBlockAt(p.getLocation().add(0,3,0)).setType(Material.STONE);
    }
}
