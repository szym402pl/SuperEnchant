package me.xiaojibazhanshi.superEnchant;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class SuperEnchantCommand implements CommandExecutor {

    Util utilClass = new Util();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only a player can run this command!");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Usage: /superenchant <enchant> <modifier>");
            return true;
        }

        boolean foundEnchant = false;
        Iterator<Enchantment> iterator = Registry.ENCHANTMENT.iterator();
        Enchantment enchantment = null;

        while (iterator.hasNext()) {
            Enchantment nextEnchant = iterator.next();
            String enchKey = nextEnchant.getKey().getKey();

            if (args[0].equalsIgnoreCase(enchKey)) {
                foundEnchant = true;
                enchantment = nextEnchant;
            }
        }

        if (!foundEnchant) {
            player.sendMessage(ChatColor.RED + "Invalid enchantment!");
            utilClass.makeASound(Sound.ENTITY_VILLAGER_NO, player);
            return true;
        }

        if (player.getInventory().getItemInMainHand() == null
            || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "You're not holding anything!");
            utilClass.makeASound(Sound.ENTITY_VILLAGER_NO, player);
            return true;
        }

        if (!(utilClass.isAValidModifierAfterParsing(args[1], player))) {
            player.sendMessage(ChatColor.RED + "The modifier has to be a number between 1 to 128!");
            utilClass.makeASound(Sound.ENTITY_VILLAGER_NO, player);
        }

        int parsedModifier = Integer.parseInt(args[1]);

        if (player.getInventory().getItemInMainHand().hasItemMeta()
                && player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(enchantment)) {
            player.getInventory().getItemInMainHand().removeEnchantment(enchantment);
        }

        player.getInventory().getItemInMainHand().addUnsafeEnchantment(enchantment, parsedModifier);

        player.sendMessage(ChatColor.GREEN + "Successfully enchanted your item!");
        utilClass.makeASound(Sound.ENTITY_PLAYER_LEVELUP, player);

        return true;
    }
}
