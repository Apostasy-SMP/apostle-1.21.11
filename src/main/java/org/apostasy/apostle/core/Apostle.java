package org.apostasy.apostle.core;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.command.ArchmageAurafarmCommand;
import org.apostasy.apostle.core.index.*;
import org.apostasy.apostle.core.index.magic.RitualRecipes;
import org.apostasy.apostle.core.index.magic.Rituals;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.index.magic.Spells;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.apostasy.apostle.core.item.StaffItem;
import org.apostasy.apostle.core.item.TomeItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Apostle implements ModInitializer {
	public static final String MOD_ID = "apostle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		ApostleItems.init();
		ApostleEntityTypes.init();
		ApostleRegistries.init();
		ApostleItemGroups.init();
		ApostleComponentTypes.init();
		ApostleCriterions.init();
		ApostleAttachmentTypes.init();
		ApostleTrackedData.init();
		ApostleParticleTypes.init();
		ApostleStatusEffects.init();

		Schools.init();
		Spells.init();
		Rituals.init();
		RitualRecipes.init();

		BetterItemTooltipEvent.EVENT.register(new TomeItem.Tooltip());
		BetterItemTooltipEvent.EVENT.register(new SpellScrollItem.Tooltip());
		BetterItemTooltipEvent.EVENT.register(new StaffItem.Tooltip());

		CommandRegistrationCallback.EVENT.register(new ArchmageAurafarmCommand());

		LOGGER.info("Hello Fabric world!");
	}

	public static void grantAchievement(TickCriterion criterion, Entity entity) {
		if (entity instanceof ServerPlayerEntity serverPlayer) {
			criterion.trigger(serverPlayer);
		}
	}

	public static <T> ItemStack createStackWithComponent(ItemConvertible item, ComponentType<T> component, T value) {
		ItemStack stack = new ItemStack(item);
		stack.set(component, value);
		return stack;
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
