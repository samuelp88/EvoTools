package com.samuelp88.evotools.items;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class BaseItemWrapper {
    protected final String displayName;
    protected final ItemStack itemStack;
    public BaseItemWrapper(String displayName, String itemIdentifier, ItemStack itemStack) {
        this.itemStack = itemStack;
        this.displayName = displayName;
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.itemStack.setItemMeta(itemMeta);
        this.setItemIdentifier(itemIdentifier);
    }

    public BaseItemWrapper(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.displayName = itemStack.getItemMeta().getDisplayName();
    }

    protected void setItemIdentifier(String itemIdentifier) {
        NBT.modify(this.itemStack, nbt -> {
            nbt.setString("itemIdentifier", itemIdentifier);
        });
    }

    public String getItemIdentifier() {
        return NBT.get(this.itemStack, nbt -> nbt.getString("itemIdentifier"));
    }
    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    // events
    public void onInteract(PlayerInteractEvent event) { }
    public void onDamaged(PlayerItemDamageEvent event) { }
}
