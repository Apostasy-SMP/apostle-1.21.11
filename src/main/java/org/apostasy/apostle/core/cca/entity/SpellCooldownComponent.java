package org.apostasy.apostle.core.cca.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class SpellCooldownComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<SpellCooldownComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("spell_cooldown"),
            SpellCooldownComponent.class
    );
    private final PlayerEntity player;

    private List<CooldownEntry> entries = new ArrayList<>();

    public SpellCooldownComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        for (CooldownEntry entry : entries) {
            if (entry.cooldown > 0) {
                entries.remove(entry);
                entries.add(new CooldownEntry(entry.spell, entry.cooldown - 1));
            } else {

            }
        }
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readData(ReadView readView) {
        entries = readView.read("Entries", CooldownEntry.CODEC.listOf()).orElse(new ArrayList<>());
    }

    public void writeData(WriteView writeView) {
        if (entries != null) {
            writeView.put("Entries", CooldownEntry.CODEC.listOf(), entries);
        }
    }

    public List<CooldownEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<CooldownEntry> entries) {
        this.entries = entries;
        sync();
    }

    public void put(Spell spell, int cooldown) {
        List<CooldownEntry> placedEntries = new ArrayList<>(this.getEntries());
        placedEntries.add(new CooldownEntry(spell, cooldown));
        this.setEntries(placedEntries);
    }

    public record CooldownEntry(Spell spell, Integer cooldown) {
        public static final Codec<CooldownEntry> CODEC = RecordCodecBuilder.create(codec -> codec.group(
                ApostleRegistries.SPELL.getCodec().fieldOf("spell").forGetter(CooldownEntry::spell),
                Codec.INT.optionalFieldOf("cooldown", 0).forGetter(CooldownEntry::cooldown)
        ).apply(codec, CooldownEntry::new));

        public static final PacketCodec<ByteBuf, CooldownEntry> PACKET_CODEC = PacketCodecs.codec(CODEC);
    }
}
