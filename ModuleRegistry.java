package com.rebuild.vcore;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import net.fabricmc.loader.api.FabricLoader;

public final class ModuleRegistry {
    public static final class Entry {
        public final String name, category, note;
        public final boolean implemented;
        public boolean enabled;
        Entry(String name, String category, boolean implemented, String note) {
            this.name = name; this.category = category; this.implemented = implemented; this.note = note;
        }
    }

    private final Map<String, Entry> entries = new LinkedHashMap<>();

    public void registerDefaults() {
        // Inventory reconstructed from class names in the supplied V-Core archive.
        // Aura, AimBot and TriggerBot are intentionally excluded.
        // Combat
        add("AntiBot", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AntiAim", "Player", false, "Listed from original archive; behavior not yet ported");
        add("AutoBuff", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoCart", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoCrystal", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoGApple", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoTotem", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoTrap", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("AutoWeb", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("BowSpam", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("Criticals", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("CrystalOptimizer", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("EMaceHelper", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("ElytraTarget", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("HitBox", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("MaceSwap", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("Macedmg", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("Reach", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("SelfTrap", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("TargetStrafe", "Combat", false, "Listed from original archive; behavior not yet ported");
        add("WallsBypass", "Combat", false, "Listed from original archive; behavior not yet ported");
        // Movement
        add("AirStuck", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("AntiWeb", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("AutoSprint", "Movement", true, "Implemented in this rebuild");
        add("AutoWalk", "Movement", true, "Implemented: holds forward while enabled");
        add("Blink", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("ElytraBoost", "Movement", true, "Implemented in this rebuild");
        add("ElytraMotion", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("ElytraPlus", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("Flight", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("FreeLook", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("GuiMove", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("MoveFix", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("NoFall", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("NoSlow", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("Phase", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("Speed", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("TridentBoost", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("Velocity", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("WaterSpeed", "Movement", false, "Listed from original archive; behavior not yet ported");
        add("WindJump", "Movement", false, "Listed from original archive; behavior not yet ported");
        // Misc
        add("AntiAFK", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AntiAttack", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AntiCrash", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AntiServerRP", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AutoAuth", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AutoDungeon", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AutoFarm", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("AutoTpAccept", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("ChestStealer", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("ClickGui", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("ClientSettings", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("ClientSound", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("ClientSpoof", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("CombatLeave", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("FakePlayer", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("FixHP", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("LagNotifier", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("MessageAppend", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("NameProtect", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("Notifications", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("Nuker", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("RPC", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("Spammer", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("TotemPopCounter", "Misc", false, "Listed from original archive; behavior not yet ported");
        add("UnHook", "Misc", false, "Listed from original archive; behavior not yet ported");
        // Player
        add("AutoArmor", "Player", false, "Listed from original archive; behavior not yet ported");
        add("AutoEat", "Player", false, "Listed from original archive; behavior not yet ported");
        add("AutoRespawn", "Player", true, "Implemented: requests respawn after death");
        add("AutoTool", "Player", false, "Listed from original archive; behavior not yet ported");
        add("ClickAction", "Player", false, "Listed from original archive; behavior not yet ported");
        add("DurabilityAlert", "Player", false, "Listed from original archive; behavior not yet ported");
        add("ElytraReplace", "Player", false, "Listed from original archive; behavior not yet ported");
        add("ElytraSwap", "Player", false, "Listed from original archive; behavior not yet ported");
        add("FreeCam", "Player", false, "Listed from original archive; behavior not yet ported");
        add("HotbarReplenish", "Player", false, "Listed from original archive; behavior not yet ported");
        add("InventoryCleaner", "Player", false, "Listed from original archive; behavior not yet ported");
        add("ItemHelper", "Player", false, "Listed from original archive; behavior not yet ported");
        add("ItemScroller", "Player", false, "Listed from original archive; behavior not yet ported");
        add("NoDelay", "Player", false, "Listed from original archive; behavior not yet ported");
        add("NoEntityTrace", "Player", false, "Listed from original archive; behavior not yet ported");
        add("NoInteract", "Player", false, "Listed from original archive; behavior not yet ported");
        add("NoPush", "Player", false, "Listed from original archive; behavior not yet ported");
        add("PearlChaser", "Player", false, "Listed from original archive; behavior not yet ported");
        add("PerfectDelay", "Player", false, "Listed from original archive; behavior not yet ported");
        add("PortalInventory", "Player", false, "Listed from original archive; behavior not yet ported");
        add("TapeMouse", "Player", false, "Listed from original archive; behavior not yet ported");
        // Render
        add("Animations", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Arrows", "Render", false, "Listed from original archive; behavior not yet ported");
        add("AspectRatio", "Render", false, "Listed from original archive; behavior not yet ported");
        add("BlockESP", "Render", false, "Listed from original archive; behavior not yet ported");
        add("ChunkAnimation", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Crosshair", "Render", false, "Listed from original archive; behavior not yet ported");
        add("ESP", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Fullbright", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Hat", "Render", false, "Listed from original archive; behavior not yet ported");
        add("HitParticles", "Render", false, "Listed from original archive; behavior not yet ported");
        add("HudEditor", "Render", false, "Listed from original archive; behavior not yet ported");
        add("ItemESP", "Render", false, "Listed from original archive; behavior not yet ported");
        add("ItemPhysics", "Render", false, "Listed from original archive; behavior not yet ported");
        add("JumpCircle", "Render", false, "Listed from original archive; behavior not yet ported");
        add("LogoutSpots", "Render", false, "Listed from original archive; behavior not yet ported");
        add("NoCameraClip", "Render", false, "Listed from original archive; behavior not yet ported");
        add("NoRender", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Particles", "Render", false, "Listed from original archive; behavior not yet ported");
        add("PopChams", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Prediction", "Render", false, "Listed from original archive; behavior not yet ported");
        add("StorageEsp", "Render", false, "Listed from original archive; behavior not yet ported");
        add("TargerESP", "Render", false, "Listed from original archive; behavior not yet ported");
        add("Tooltips", "Render", false, "Listed from original archive; behavior not yet ported");
        add("TotemAnimation", "Render", false, "Listed from original archive; behavior not yet ported");
        add("ViewModel", "Render", false, "Listed from original archive; behavior not yet ported");
        add("WorldTweaks", "Render", false, "Listed from original archive; behavior not yet ported");
        add("XRay", "Render", false, "Listed from original archive; behavior not yet ported");
    }

    private void add(String name, String category, boolean implemented, String note) {
        entries.put(name, new Entry(name, category, implemented, note));
    }
    private Path configPath() {
        return FabricLoader.getInstance().getConfigDir().resolve("vcore-rebuild.properties");
    }

    public void load() {
        Path path = configPath();
        if (!Files.isRegularFile(path)) return;
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            properties.load(in);
            for (Entry entry : entries.values()) {
                entry.enabled = entry.implemented && Boolean.parseBoolean(properties.getProperty(entry.name, "false"));
            }
        } catch (IOException ignored) {
            // A damaged config should never prevent the client from starting.
        }
    }

    public void save() {
        Properties properties = new Properties();
        for (Entry entry : entries.values()) {
            if (entry.implemented) properties.setProperty(entry.name, Boolean.toString(entry.enabled));
        }
        Path path = configPath();
        try {
            Files.createDirectories(path.getParent());
            try (OutputStream out = Files.newOutputStream(path)) {
                properties.store(out, "VCore Rebuild module states");
            }
        } catch (IOException ignored) {
            // Config persistence is best-effort; never crash Minecraft over it.
        }
    }

    public boolean enabled(String name) {
        Entry e = entries.get(name); return e != null && e.implemented && e.enabled;
    }
    public void toggle(String name) {
        Entry e = entries.get(name); if (e != null && e.implemented) { e.enabled = !e.enabled; save(); }
    }
    public Map<String, Entry> all() { return entries; }
}
