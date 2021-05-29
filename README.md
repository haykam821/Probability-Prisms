# Probability Prisms

[![GitHub release](https://img.shields.io/github/release/haykam821/Probability-Prisms.svg?style=popout&label=github)](https://github.com/haykam821/Probability-Prisms/releases/latest)
[![CurseForge](https://img.shields.io/static/v1?style=popout&label=curseforge&message=project&color=6441A4)](https://www.curseforge.com/minecraft/mc-mods/probability-prisms)
[![Discord](https://img.shields.io/static/v1?style=popout&label=chat&message=discord&color=7289DA)](https://discord.gg/YtnXecuAwF)

Adds blocks that run functions from a tag when they are broken.

Probability Prisms requires the [Fabric modloader](https://fabricmc.net/use/) and [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api).

## Installation

1. Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api) if it is not installed.
2. Download Probability Prisms from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/probability-prisms/files) or [GitHub](https://github.com/haykam821/Probability-Prisms/releases).
3. Place the downloaded file in your `mods` folder.

## Usage

When this mod is installed, probability prisms will be available in creative mode or by using commands. When broken or activated by redstone, probability prisms will execute one function at random from the `#probabilityprisms:break_probability_prism` function tag if it is not empty.

## Custom Probability Prisms

Probability prisms have an `unstable` boolean property that controls whether they activate once broken. By default, probability prisms are unstable.

The function tag used can be overridden for an individual probability prism by setting the `FunctionTagId` data of the block entity to the identifier of another function tag.

Functions executed by probability prisms have a command source similar to command blocks, with a custom name: `[?]`.
