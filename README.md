# MXRank


## Authors

- [@mixeration](https://www.github.com/mixeration)


## Features

- Direct Menu System
- Auto save and storage
- Prefix support
- Management command
- ASkyBlock and SuperiorSkyblock support


## Run Locally

Clone the project

```bash
  git clone https://github.com/mixeration/MXRank.git
```

Go to the project directory

```bash
  cd MXRank
```

You can compile with maven.

```bash
  mvn clean
```


## Roadmap

- Added YAML Storage
- Added auto data creator
- Added rank up menu


## Support

For support, email mixeration@gmail.com or come our [Discord](https://discord.gg/SndCjECD9N).


## API Reference
#### Get all player data
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `getPlayerRankPriority(PlayerUUID)` | `integer` | **Reason for use**: Shows which rank the player is in.  |
| `getPlayerNextRank(PlayerUUID)` | `string` | **Reason for use**: Shows which next rank the player.  |
| `getPlayerCurrentRank(PlayerUUID)` | `string` | **Reason for use**: Shows players current rank.  |
| `isPlayerFinishAllRanks(PlayerUUID)` | `boolean` | **Reason for use**: Indicates whether the player has completed all ranks.  |

