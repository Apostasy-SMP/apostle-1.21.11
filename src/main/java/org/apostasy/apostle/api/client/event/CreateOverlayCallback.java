package org.apostasy.apostle.api.client.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.*;

/**
 * @author Chemthunder
 */
public interface CreateOverlayCallback {
    Event<CreateOverlayCallback> EVENT = EventFactory.createArrayBacked(CreateOverlayCallback.class,
            events -> (
                    player
            ) -> {
                List<CreateOverlayCallback> sortedEvents = new ArrayList<>(Arrays.asList(events));
                sortedEvents.sort(Comparator.comparingInt(CreateOverlayCallback::getPriority));
                for (CreateOverlayCallback event : sortedEvents) {
                    Optional<Pair<Identifier, Float>> overlay = event.getOverlay(player);
                    if (overlay.isPresent()) {
                        return overlay;
                    }
                }
                return Optional.empty();
            }
    );

    default int getPriority() {
        return 1000;
    }

    Optional<Pair<Identifier, Float>> getOverlay(PlayerEntity player);
}
