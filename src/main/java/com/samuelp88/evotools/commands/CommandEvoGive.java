package com.samuelp88.evotools.commands;

import com.samuelp88.evotools.handlers.ItemRegistryHandler;
import com.samuelp88.evotools.items.BaseItemWrapper;
import com.samuelp88.evotools.items.EvoAxeWrapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEvoGive implements CommandExecutor {
    private final ItemRegistryHandler itemRegistry;

    public CommandEvoGive(ItemRegistryHandler itemRegistry) {
        this.itemRegistry = itemRegistry;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1) return false;

        String itemIdentifier = args[0];
        if(sender instanceof Player) {
            Player player = (Player) sender;
            BaseItemWrapper itemWrapper = itemRegistry
                    .getWrappedItem(itemIdentifier);

            if(itemWrapper != null) {
                player.getInventory()
                        .addItem(itemWrapper.getItemStack());

                return true;
            }
        }

        return false;
    }
}
