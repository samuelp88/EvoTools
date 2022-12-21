package com.samuelp88.evotools;

import com.samuelp88.evotools.commands.CommandEnderChest;
import com.samuelp88.evotools.commands.CommandEvoGive;
import com.samuelp88.evotools.handlers.ItemRegistryHandler;
import com.samuelp88.evotools.handlers.RegistryItemBuilder;
import com.samuelp88.evotools.items.*;
import com.samuelp88.evotools.listeners.PlayerEventsListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EvoTools extends JavaPlugin {
    private final Logger logger = Bukkit.getLogger();
    private final ItemRegistryHandler itemRegistry = new ItemRegistryHandler();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("evogive").setExecutor(new CommandEvoGive(itemRegistry));
        this.getCommand("ec").setExecutor(new CommandEnderChest());
        this.itemRegistry
            .register(EvoAxeWrapper.itemIdentifier, new RegistryItemBuilder()
                    .setInstantiateConstructor(EvoAxeWrapper::new)
                    .setWrapConstructor(EvoAxeWrapper::new))
            .register(EvoShovelWrapper.itemIdentifier, new RegistryItemBuilder()
                    .setInstantiateConstructor(EvoShovelWrapper::new)
                    .setWrapConstructor(EvoShovelWrapper::new))
            .register(EvoSwordWrapper.itemIdentifier, new RegistryItemBuilder()
                    .setInstantiateConstructor(EvoSwordWrapper::new)
                    .setWrapConstructor(EvoSwordWrapper::new))
            .register(EvoPickaxeWrapper.itemIdentifier, new RegistryItemBuilder()
                    .setInstantiateConstructor(EvoPickaxeWrapper::new)
                    .setWrapConstructor(EvoPickaxeWrapper::new));


        this.getServer()
            .getPluginManager()
            .registerEvents(new PlayerEventsListener(itemRegistry), this);
        logger.info(ChatColor.GREEN + "Enabled " + this.getName());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info(ChatColor.RED + "Disabled " + this.getName());
    }
}
