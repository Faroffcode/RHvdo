# RHvdo

**RobinHood V-player** is a native Android video player written in Kotlin. It is based on [Next Player](https://github.com/anilbeesetti/nextplayer) and remains licensed under the GNU General Public License v3.0.

## Features

- Native Android video player
- Material 3 interface
- Hardware/software decoder support
- Audio and subtitle track selection
- Playback speed control
- External subtitle support
- Zoom and swipe gestures
- Picture-in-picture mode
- Background playback
- Android TV support
- Network storage support (SMB/FTP/SFTP/WebDAV)
- Play videos from URLs
- Media picker with folder/file views
- Search functionality
- Completely free and open source, with no ads

## Build

Requirements:

- Android Studio with JDK 17
- Android SDK configured for the project

Debug build:

```bash
./gradlew assembleDebug
```

Release builds are produced by the manually triggered GitHub Actions workflow.

## Release process

Releases use one source of truth:

```text
Manual workflow input
        ↓
versionName + versionCode
        ↓
Gradle build
        ↓
APK identity verification
        ↓
Git tag vX.Y.Z
        ↓
GitHub Release
```

The release workflow refuses to create an already-existing tag and verifies that the APK contains:

- applicationId: `io.github.faroffcode.rhvdo`
- versionName: the requested `X.Y.Z`
- versionCode: the requested integer

## Attribution

RHvdo is a fork/derivative work based on **Next Player** by Anil Beeshetti.

Original project: https://github.com/anilbeesetti/nextplayer

RHvdo modifications are distributed under the same GPL-3.0 license. See [LICENSE](LICENSE).

## License

GNU General Public License v3.0. See [LICENSE](LICENSE).
