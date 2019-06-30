package huang825172.seybox.testPlugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class item {
    public static ItemStack getBook() {
        ItemStack book = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("§b回血秘籍");
        ArrayList lore = new ArrayList();
        lore.add("§b手持每秒回一血");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        book.setItemMeta(meta);
        return book;
    }
}
