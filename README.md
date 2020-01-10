# Important!

If running this plugin from source, you will need the <a href="https://poggit.pmmp.io/p/DEvirion">DEvirion</a> plugin and the <a href="https://poggit.pmmp.io/ci/Atomization/gui/gui">gui library</a> **ONLY** if you wish to use forms as the method to display stats; 
**AND** you will need <a href="https://poggit.pmmp.io/ci/poggit/libasynql/libasynql">libasynql</a> if you wish to sync data across all your servers!

# AfterLife Features
Fully featured kill/death scoring plugin with custom death event!

<p align="center">
 <a href="http://hits.dwyl.io/TimelessMC/Afterlife"><img src="http://hits.dwyl.io/TimelessMC/Afterlife.svg"></a>
</p>

 - [x] The ability to sync data across multiple servers in a network
 - [x] Score points on Kill! `(+ gain xp)`
 - [x] Loose xp on Death!
 - [x] Calculates kill/death ratio 
 - [x] Level up when achieved specified amount of XP `(see config)`
 - [x] Commands to see your or another players' stats `(uses forms or standard text)`
 - [ ] Enable floating texts to see leaderboard of stats `(see commands)`
 - [ ] Add commands to easily change settings in config
 - [ ] Add Level up timer `(level up over time, so stay online to level up!)`
 - [ ] Add Longest Bow Kills & Hits Leaderboards
 - [ ] Add Display Levels beside name in chat and name-tag

# Commands
| Command | Usage | Description |
| ------- | ----- | ----------- |
| `/stats` | `/stats <player>` | Shows yours or another players stats. |
| `/setlearderboard` | `/setleaderboard <type>` | Creates a floating text at players location. |
`/setleaderboard` not yet implemented...

# Full Config
```yml
# enable floating texts.
# true: false:
texts-enabled: true

# how many players to display
texts-top: 5

# setts the title for each leaderboard
texts-title:
  levels: "&b< PvP Levels Leaderboard >"
  kills: "&b< Kills Leaderboard >"
  kdr: "&b< K/D Ratio Leaderboard >"
  streaks: "&b< Top Killstreaks >"


# choose to use "form" or "standard" message to display stats
# methods => "form" "standard"
view-stats: "form"


# choose between 'custom' or 'default'
# custom bypasses the death 'main menu' screen and default does not
death-method: "custom"


# use built in level up system that adds levels on kill and removes level on death
# choose 'false' if you already have a level up plugin
# true: false:
use-levels: true


# use level up timer
# adds xp over time
# (example) stay online to gain xp
use-level-up-timer: true


# amount of xp to be given on kill
add-xp-amount: 50


# amount of xp to be lost on death
loose-xp-amount: 10


# how much xp is required for level up
xp-levelup-amount: 1000


# Disables PvP at spawn... uses server default level,
# if want to use custom level set this to false and use (no-PvP-in-level)
no-PvP-at-spawn: true

# disables PvP in specified world
# works if no-PvP-at-spawn: is set to false
# add as many worlds as you like!
no-PvP-in-level:
  - "world1"
  - "world2"
  - "world3"


# connect to mongo db database - depend on "NukkitDb" plugin
database: ""
collection: ""


#-----------------------------------------------------------------------------------------------------------------------
# CONFIG VERSION!!! do not change...
version: 2
#-----------------------------------------------------------------------------------------------------------------------
```
# 💰 Credits
Icon made by Freepik from www.flaticon.com is licensed by CC 3.0 BY