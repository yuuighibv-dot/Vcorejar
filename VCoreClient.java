package com.rebuild.vcore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public final class VCoreClient implements ClientModInitializer {
    public static final ModuleRegistry MODULES = new ModuleRegistry();
    private static KeyBinding openMenu;

    @Override
    public void onInitializeClient() {
        MODULES.registerDefaults();
        MODULES.load();
        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.vcore_rebuild.open_menu", InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT, KeyBinding.Category.MISC));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenu.wasPressed()) client.setScreen(new VCoreScreen());
            applyAutoSprint(client);
            applyAutoWalk(client);
            applyAutoRespawn(client);
            applyElytraBoost(client);
        });
    }

    private static void applyAutoSprint(MinecraftClient client) {
        if (client.player == null || client.options == null || !MODULES.enabled("AutoSprint")) return;
        if (client.options.forwardKey.isPressed() && !client.player.isSneaking()
                && client.player.getHungerManager().getFoodLevel() > 6) {
            client.player.setSprinting(true);
        }
    }

    private static void applyAutoWalk(MinecraftClient client) {
        if (client.options == null || !MODULES.enabled("AutoWalk")) return;
        // Do not force the key state back to false when disabled: that would
        // interfere with a physical key press or another input handler.
        client.options.forwardKey.setPressed(true);
    }

    private static void applyAutoRespawn(MinecraftClient client) {
        if (!MODULES.enabled("AutoRespawn") || client.player == null) return;
        if (client.player.isDead()) client.player.requestRespawn();
    }

    private static void applyElytraBoost(MinecraftClient client) {
        if (client.player == null || client.world == null || !MODULES.enabled("ElytraBoost")) return;
        if (!client.player.isGliding()) return;
        Vec3d look = client.player.getRotationVec(1.0F);
        Vec3d velocity = client.player.getVelocity();
        Vec3d forward = new Vec3d(look.x, Math.max(-0.12, look.y), look.z)
                .normalize().multiply(0.018);
        client.player.setVelocity(velocity.add(forward));
    }
}
