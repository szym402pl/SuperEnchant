package me.xiaojibazhanshi.superEnchant;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Util {

    public void makeASound(Sound sound, Player player) {
        player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
    }

    public boolean isAValidModifierAfterParsing(String argument, Player player) {

        int parsedModifier;

        try {
            parsedModifier = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            return false;
        }

        if (parsedModifier < 1 || parsedModifier > 127) {
            return false;
        }

        return true;
    }

}
