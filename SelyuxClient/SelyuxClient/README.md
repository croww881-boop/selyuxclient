# Selyux Client

Client utility mod buatan sendiri untuk Minecraft 1.21.11 (Fabric, Yarn mappings).
Tema GUI hitam-merah.

## Fitur
- Movement: Auto Sprint
- Render: Fullbright, Zoom (tahan C)
- HUD: Watermark, FPS, Coordinates, ArrayList
- Click GUI: tekan Right Shift (bisa diubah di Controls)

## Build
1. Install JDK 21.
2. Buka folder ini di IntelliJ IDEA (Gradle project), atau jalankan `gradle wrapper` lalu:
   `./gradlew build`
3. Jar ada di `build/libs/selyuxclient-1.0.0.jar` (taruh di folder mods bersama Fabric API).
4. Test langsung: `./gradlew runClient`

## Struktur
- `module/` : sistem module (Module, ModuleManager, impl/)
- `gui/`    : ClickGuiScreen + ModuleButton
- `Theme`   : semua warna ada di satu file, ubah di sini untuk ganti tema
