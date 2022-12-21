package com.samuelp88.evotools.handlers;

import com.samuelp88.evotools.items.BaseItemWrapper;
import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.function.Function;

public class ItemRegistryHandler {
    private HashMap<String, RegistryItemBuilder> registeredItems = new HashMap<>();

    public ItemRegistryHandler register(
            String itemIdentifier,
            RegistryItemBuilder itemBuilder
    ) {
        registeredItems.put(itemIdentifier, itemBuilder);
        return this;
    }

    public BaseItemWrapper getWrappedItem(ItemStack itemStack) {
        String itemIdentifier = NBT.get(itemStack, nbt -> nbt.getString("itemIdentifier"));
        return registeredItems.get(itemIdentifier).build(itemStack);
    }

    public BaseItemWrapper getWrappedItem(String itemIdentifier) {
        RegistryItemBuilder itemBuilder = registeredItems.get(itemIdentifier);
        if(itemBuilder == null)
            return null;
        else
            return itemBuilder.build();
    }
}
