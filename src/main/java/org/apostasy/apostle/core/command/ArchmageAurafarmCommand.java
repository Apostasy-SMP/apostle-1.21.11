package org.apostasy.apostle.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author Chemthunder
 */
public class ArchmageAurafarmCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("aurafarm")
                .then(literal("value")
                        .then(argument("state", BoolArgumentType.bool())
                                .executes(context -> {
                                    PlayerEntity player = context.getSource().getPlayer();

                                    if (player != null) {
                                        player.setAttached(
                                                ApostleAttachmentTypes.IS_ARCHMAGE,
                                                BoolArgumentType.getBool(context, "state")
                                        );
                                    }
                                    return 1;
                                })
                        )
                )
        );
    }
}
