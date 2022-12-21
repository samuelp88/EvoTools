package com.samuelp88.evotools.listeners;

import com.samuelp88.evotools.handlers.ItemRegistryHandler;
import com.samuelp88.evotools.items.BaseItemWrapper;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventsListener implements Listener {
    private final ItemRegistryHandler itemRegistry;
    public PlayerEventsListener(ItemRegistryHandler itemRegistry) {
        this.itemRegistry = itemRegistry;
    }
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        ItemStack eventItem = event.getItem();
        if(eventItem == null || isNotPluginItem(eventItem)) return;

        BaseItemWrapper wrappedItem = itemRegistry.getWrappedItem(eventItem);
        wrappedItem.onInteract(event);
    }

    @EventHandler
    public void onPlayerItemDamaged(PlayerItemDamageEvent event) {
        ItemStack eventItem = event.getItem();
        if(eventItem == null || isNotPluginItem(eventItem)) return;

        BaseItemWrapper wrappedItem = itemRegistry.getWrappedItem(eventItem);
        wrappedItem.onDamaged(event);
    }

    private boolean isNotPluginItem(ItemStack itemStack) {
        String itemCode = NBT.get(itemStack, nbt -> nbt.getString("itemIdentifier"));
        return itemCode.isEmpty();
    }
}
