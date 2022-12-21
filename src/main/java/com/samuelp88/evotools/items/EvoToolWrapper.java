package com.samuelp88.evotools.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class EvoToolWrapper extends BaseItemWrapper {
    public EvoToolWrapper(String displayName, String itemIdentifier, ItemStack itemStack) {
        super(displayName, itemIdentifier, itemStack);
        this.itemStack.setDurability((this.getItemStack().getType().getMaxDurability()));
        updateProgressLore();
    }
    public EvoToolWrapper(ItemStack itemStack) {
        super(itemStack);
    }

    protected short getDurabilityPercentage() {
        short maxDurability = this.itemStack.getType().getMaxDurability();
        short maxPercentage = 100;
        return (short) (maxPercentage - (this.itemStack.getDurability() * maxPercentage / maxDurability));
    }

    protected void incrementDurabilityByPercentage(short percentage) {
        short durabilityIncreaseValue = (short) (this.itemStack.getType().getMaxDurability() / 100f * percentage);
        short durability = (short) (this.itemStack.getDurability() - durabilityIncreaseValue);
        short calculatedDurability = (short) Math.max(0, durability);
        this.itemStack.setDurability(calculatedDurability);
    }

    protected void updateProgressLore() {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(
                Arrays.asList("Progress: " + getDurabilityPercentage() + "%")
        );

        this.itemStack.setItemMeta(itemMeta);
    }
    protected void evolve() {
        Material[] evoMaterials = getEvoMaterials();
        Material targetMaterial = this.itemStack.getType();
        int materialIndex = Arrays.asList(evoMaterials).indexOf(this.itemStack.getType());
        if(materialIndex >= 0 && materialIndex < evoMaterials.length-1) {
            targetMaterial = evoMaterials[materialIndex+1];
            this.itemStack.setType(targetMaterial);
            this.itemStack.setDurability(this.itemStack.getType().getMaxDurability());

        }
    }

    protected abstract Material[] getEvoMaterials();
}
