package org.silvercatcher.reforged.items.weapons;

import org.silvercatcher.reforged.api.AReloadable;
import org.silvercatcher.reforged.api.ReforgedAdditions;
import org.silvercatcher.reforged.entities.EntityBulletMusket;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMusket extends AReloadable {
	
	public ItemMusket() {
		super("musket");
	}
	
	@Override
	public void shoot(World world, EntityLivingBase player, ItemStack stack) {
		world.spawnEntityInWorld(new EntityBulletMusket(world, player, stack));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		setAmmo(ReforgedAdditions.MUSKET_BULLET);
		return super.onItemRightClick(stack, world, player);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		
		return (repair.getItem() == Items.iron_ingot);
	}
	
	@Override
	public int getItemEnchantability() {
		
		return ToolMaterial.IRON.getEnchantability();
	}
	
	@Override
	public void registerRecipes() {
	
		GameRegistry.addShapelessRecipe(new ItemStack(this),
				new ItemStack(ReforgedAdditions.MUSKET_BARREL),
				new ItemStack(ReforgedAdditions.GUN_STOCK));
	}

	@Override
	public float getHitDamage() {
		return 2f;
	}
	
	@Override
	public int getItemEnchantability(ItemStack stack) {
		return ToolMaterial.IRON.getEnchantability();
	}

	@Override
	public int getReloadTotal() {
		return 45;
	}
}