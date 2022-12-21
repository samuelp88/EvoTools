package com.samuelp88.evotools.handlers;

import com.samuelp88.evotools.items.BaseItemWrapper;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryItemBuilder {
    private Function<ItemStack, BaseItemWrapper> wrappingConstructor;
    private Supplier<BaseItemWrapper> instantiatingConstructor;
    public RegistryItemBuilder setWrapConstructor(Function<ItemStack, BaseItemWrapper> wrapConstructor) {
        this.wrappingConstructor = wrapConstructor;
        return this;
    }

    public RegistryItemBuilder setInstantiateConstructor(Supplier<BaseItemWrapper> instantiateConstructor) {
        this.instantiatingConstructor = instantiateConstructor;
        return this;
    }

    public BaseItemWrapper build() {
        return instantiatingConstructor.get();
    }

    public BaseItemWrapper build(ItemStack itemStack) {
        return wrappingConstructor.apply(itemStack);
    }
}
