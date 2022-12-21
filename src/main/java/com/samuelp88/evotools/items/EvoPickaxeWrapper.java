package com.samuelp88.evotools.items;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EvoPickaxeWrapper extends EvoToolWrapper{
    public final static String itemIdentifier = "pickaxe";
    private final static String displayName = "Evo Pickaxe";
    private final static Material[] evoMaterials = new Material[] {
            Material.WOOD_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.GOLD_PICKAXE
    };

    public EvoPickaxeWrapper() {
        super(displayName, itemIdentifier, new ItemStack(evoMaterials[0]));
        this.itemStack.setDurability((this.getItemStack().getType().getMaxDurability()));
        updateProgressLore();
    }
    public EvoPickaxeWrapper(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    protected Material[] getEvoMaterials() {
        return evoMaterials;
    }
    @Override
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR) {

        }
    }
    @Override
    public void onDamaged(PlayerItemDamageEvent event) {
        event.setCancelled(true);
        incrementDurabilityByPercentage((short) 33);

        if(this.itemStack.getDurability() == 0)
            evolve();

        updateProgressLore();
    }
}
