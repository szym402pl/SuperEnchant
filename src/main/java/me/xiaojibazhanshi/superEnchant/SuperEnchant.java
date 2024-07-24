package me.xiaojibazhanshi.superEnchant;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperEnchant extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Thanks for using my plugin! ~XiaoJibaZhanshi");

        getCommand("superenchant").setExecutor(new SuperEnchantCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
