package com.rebuild.vcore;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public final class VCoreScreen extends Screen {
    private final List<ButtonWidget> moduleButtons = new ArrayList<>();
    private int scroll;
    private String category = "All";

    public VCoreScreen() { super(Text.literal("V-Core")); }

    @Override protected void init() { rebuildButtons(); }

    private List<ModuleRegistry.Entry> visibleEntries() {
        List<ModuleRegistry.Entry> result = new ArrayList<>();
        for (ModuleRegistry.Entry e : VCoreClient.MODULES.all().values()) {
            if (category.equals("All") || category.equals(e.category)) result.add(e);
        }
        return result;
    }

    private void rebuildButtons() {
        clearChildren(); moduleButtons.clear();
        int panelTop = height / 2 - 112;
        int y = panelTop + 66 - scroll;
        int x = width / 2 - 104;
        for (ModuleRegistry.Entry entry : visibleEntries()) {
            if (y >= panelTop + 60 && y < panelTop + 190) {
                ButtonWidget b = ButtonWidget.builder(label(entry), button -> {
                    if (entry.implemented) {
                        VCoreClient.MODULES.toggle(entry.name);
                        button.setMessage(label(entry));
                    }
                }).dimensions(x, y, 208, 19).build();
                b.active = entry.implemented;
                addDrawableChild(b); moduleButtons.add(b);
            }
            y += 22;
        }
        int tabY = panelTop + 34;
        String[] tabs = {"All", "Combat", "Movement", "Player", "Render", "Misc"};
        int tabX = width / 2 - 116;
        for (String tab : tabs) {
            final String chosen = tab;
            ButtonWidget b = ButtonWidget.builder(Text.literal(tab), button -> {
                category = chosen; scroll = 0; rebuildButtons();
            }).dimensions(tabX, tabY, 38, 18).build();
            addDrawableChild(b); tabX += 40;
        }
    }

    private Text label(ModuleRegistry.Entry e) {
        return Text.literal(e.name + "  " + (e.implemented ? (e.enabled ? "[ON]" : "[OFF]") : "[NOT PORTED]"));
    }

    @Override public boolean mouseScrolled(double mouseX, double mouseY,
                                           double horizontalAmount, double verticalAmount) {
        int count = visibleEntries().size();
        int maxScroll = Math.max(0, count * 22 - 130);
        scroll = Math.max(0, Math.min(maxScroll, scroll - (int)(verticalAmount * 22)));
        rebuildButtons(); return true;
    }

    @Override public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int left = width / 2 - 125, top = height / 2 - 112;
        context.fill(left, top, left + 250, top + 224, 0xED171B29);
        context.fill(left, top, left + 250, top + 3, 0xFF55C8FF);
        context.drawCenteredTextWithShadow(textRenderer, "V-CORE", width / 2, top + 10, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer, "Fabric 1.21.11  |  Right Shift: close",
                width / 2, top + 207, 0xFFB9C6D8);
        context.drawCenteredTextWithShadow(textRenderer, "Enabled modules save automatically",
                width / 2, top + 194, 0xFFFFC56B);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override public boolean shouldPause() { return false; }
}
