# VCore Rebuild — Minecraft 1.21.11 / Fabric

A clean-room Fabric client rebuild based on the supplied V-Core source/assets archive. This is not the original client binary patched in place.

## Target
- Minecraft 1.21.11
- Fabric Loader 0.18.1+
- Fabric API 0.141.1+1.21.11
- Java 21
- Click GUI: Right Shift

## Working modules in this source
- **AutoSprint** — sprints while moving forward when hunger/sneak conditions allow.
- **AutoWalk** — continuously holds forward while enabled.
- **AutoRespawn** — requests respawn after death.
- **ElytraBoost** — applies a small look-direction velocity increment while gliding.

Module enabled states are loaded from and saved to `config/vcore-rebuild.properties`. The ClickGUI includes the original module inventory, but entries marked `[NOT PORTED]` are intentionally disabled placeholders and do not claim to implement their original behavior.

## PvP coverage
The source inventory includes modules associated with both sword/axe PvP and Crystal/Mace/Elytra PvP. Their names in the GUI are not equivalent to working features: only the four modules above have verified implementations in this rebuild. AutoCrystal, AutoTotem, Reach, HitBox, Aura, AimBot, TriggerBot and other unported combat modules are not functional. This avoids shipping misleading or crash-prone stubs.

## Build
Run with JDK 21 and an internet connection:

```bash
gradle build
```

Output: `build/libs/VCore-Rebuild-1.21.11-1.0.0-rebuild.jar`

The current execution environment has Java 21 but no Gradle executable/wrapper and cannot fetch external build dependencies, so a JAR cannot be compiled or runtime-tested here. The source archive is ready for building in a normal Fabric development environment.
