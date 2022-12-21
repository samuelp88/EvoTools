package com.samuelp88.evotools.items;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EvoShovelWrapper extends EvoToolWrapper{
    public final static String itemIdentifier = "shovel";
    private final static String displayName = "Evo Shovel";
    private final static Material[] evoMaterials = new Material[] {
            Material.WOOD_SPADE,
            Material.STONE_SPADE,
            Material.IRON_SPADE,
            Material.DIAMOND_SPADE,
            Material.GOLD_SPADE
    };

    public EvoShovelWrapper() {
        super(displayName, itemIdentifier, new ItemStack(evoMaterials[0]));
        this.itemStack.setDurability((this.getItemStack().getType().getMaxDurability()));
        updateProgressLore();
    }
    public EvoShovelWrapper(ItemStack itemStack) {
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
