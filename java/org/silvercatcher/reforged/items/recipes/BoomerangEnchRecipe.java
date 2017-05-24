package org.silvercatcher.reforged.items.recipes;

import org.silvercatcher.reforged.api.CompoundTags;
import org.silvercatcher.reforged.api.ReforgedAdditions;
import org.silvercatcher.reforged.items.weapons.ItemBoomerang;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BoomerangEnchRecipe implements IRecipe {

	private ItemStack output = null;

	private static void printInventory(String name, InventoryCrafting inventory) {

		if (Minecraft.getMinecraft().theWorld != null) {

			System.out.append(name);
			System.out.append(":\t[");
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				System.out.append(inventory.getStackInSlot(i) + ",");
			}
			System.out.append("]");
			System.out.println();
		}
	}

	@Override
	public boolean matches(InventoryCrafting inventory, World world) {

		// printInventory("match", inventory);

		int boomerangs = 0;
		int gold = 0;
		int diamonds = 0;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {

			ItemStack stack = inventory.getStackInSlot(i);

			if (stack != null) {
				if (stack.getItem() instanceof ItemBoomerang
						&& !CompoundTags.giveCompound(stack).getBoolean(CompoundTags.ENCHANTED)) {
					boomerangs++;
					output = stack.copy();
				} else if (stack.getItem() == Items.gold_ingot) {
					gold++;
				} else if (stack.getItem() == Items.diamond) {
					diamonds++;
				} else {
					// we don't want any other stuff!
					return false;
				}
			}
		}

		return boomerangs == 1 && gold == 6 && diamonds == 2;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {

		// printInventory("result", inventory);

		int size = inventory.getSizeInventory();

		NBTTagCompound compound = CompoundTags.giveCompound(output);

		output.addEnchantment(ReforgedAdditions.goalseeker, 1);
		compound.setBoolean(CompoundTags.ENCHANTED, true);
		return output;
	}

	@Override
	public int getRecipeSize() {

		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() {
		ItemStack output = new ItemStack(ReforgedAdditions.DIAMOND_BOOMERANG);
		NBTTagCompound compound = CompoundTags.giveCompound(output);
		output.addEnchantment(ReforgedAdditions.goalseeker, 1);
		compound.setBoolean(CompoundTags.ENCHANTED, true);
		return this.output != null ? this.output : output;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inventory) {
		// printInventory("remain", inventory);
		return ForgeHooks.defaultRecipeGetRemainingItems(inventory);
	}
}