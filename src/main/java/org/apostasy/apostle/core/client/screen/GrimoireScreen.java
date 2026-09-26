package org.apostasy.apostle.core.client.screen;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.apostasy.apostle.core.item.component.GrimoireComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Chemthunder
 */
public class GrimoireScreen extends Screen {
    private final ItemStack grimoire;
    private final List<Function<DrawContext, Drawable>> drawableFunctions = new ArrayList<>();

    private int currentPage = 0;

    public GrimoireScreen(ItemStack grimoire) {
        super(Text.empty());
        this.grimoire = grimoire;
    }

    protected void init() {
        this.drawableFunctions.clear();

        PlayerEntity player = client.player;
        GrimoireComponent book = grimoire.get(ApostleComponentTypes.GRIMOIRE);

        if (book != null) {
            if (player != null) {
                ButtonWidget cycleFwd = ButtonWidget.builder(Text.literal("Next"), (button -> {
                    if (currentPage < book.list().size() - 1) {
                        currentPage++;
                    } else {
                        currentPage = 0;
                    }
                })).build();

                cycleFwd.setDimensions(48, 16);
                cycleFwd.setPosition(width / 2 - (cycleFwd.getWidth() / 2) + 30, height - 40);
                drawableFunctions.add(context -> cycleFwd);
                this.addDrawableChild(cycleFwd);

                ButtonWidget cycleBwd = ButtonWidget.builder(Text.literal("Back"), (button -> {
                    if (currentPage > 0) {
                        currentPage--;
                    } else {
                        currentPage = book.list().size() - 1;
                    }
                })).build();

                cycleBwd.setDimensions(48, 16);
                cycleBwd.setPosition(width / 2 - (cycleBwd.getWidth() / 2) - 30, height - 40);
                drawableFunctions.add(context -> cycleBwd);
                this.addDrawableChild(cycleBwd);
            }
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        GrimoireComponent stack = grimoire.get(ApostleComponentTypes.GRIMOIRE);
        drawableFunctions.forEach(func -> func.apply(context).render(context, mouseX, mouseY, deltaTicks));

        if (stack != null) {
            context.drawGuiTexture(
                    RenderPipelines.GUI_TEXTURED,
                    Apostle.id("screen/book"),
                    width / 2 - 90,
                    height / 2 - 90,
                    256,
                    256,
                    ColorHelper.withAlpha(0.5F, 0xFFffffff)
            );

            ItemStack disp = stack.list().get(currentPage);
            Spell spell = SpellScrollItem.getSpellStack(disp);

            if (spell != null) {
                for (Item item : spell.getIngredients()) {
                    int index = spell.getIngredients().indexOf(item);

                    context.drawText(
                            client.textRenderer,
                            spell.getName(),
                            20,
                            5,
                            0xFFffffff,
                            true
                    );

                    context.drawText(
                            client.textRenderer,
                            Text.literal("- ").append(item.getName()),
                            20,
                            15 + (index * 10),
                            0xFFffffff,
                            true
                    );
                }
            }
        }
    }
}
