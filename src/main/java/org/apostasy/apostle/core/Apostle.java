package org.apostasy.apostle.core;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.item.SpellScrollItem;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.index.*;
import org.apostasy.apostle.core.index.core.Schools;
import org.apostasy.apostle.core.index.core.Spells;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Chemthunder
 */
public class Apostle implements ModInitializer {
	public static final String MOD_ID = "apostle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TrackedDataHandler<List<ItemStack>> ITEM_STACK_LIST = TrackedDataHandler.create(ItemStack.OPTIONAL_LIST_PACKET_CODEC);

	public void onInitialize() {
		ApostleItems.init();
		ApostleEntityTypes.init();
		ApostleRegistries.init();
		ApostleItemGroups.init();
		ApostleComponentTypes.init();

		Schools.init();
		Spells.init();

		BetterItemTooltipEvent.EVENT.register(new TomeItem.Tooltip());
		BetterItemTooltipEvent.EVENT.register(new SpellScrollItem.Tooltip());

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
