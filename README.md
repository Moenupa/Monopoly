# Monopoly

COMP3211 Software Engineering Course Project

```
╟──────┬─────┼─────────┼─────────┼─────────┼───────────────────────────────
║ J    │I ddd│         │         │         │
║ U  d │N ddd│ Stanley │ INCOME  │   Wan   │
║ S  d │ JAIL│         │   TAX   │   Chai  │
║ T  d └─────┤         │         │         │
║      d d d │  d d d  │  d d d  │  d d d  │
║   VISITING │  d d d  │  d d d  │  d d d  │
╚════════════╧═════════╧═════════╧═════════╧═══════════════════════════════╝
║
╟──────┬─────┼─────────┼─────────┼─────────┼───────────────────────────────
║ J    │I %d%d%d│         │         │         │
║ U  %d │N %d%d%d│ Stanley │ INCOME  │   Wan   │
║ S  %d │ JAIL│         │   TAX   │   Chai  │
║ T  %d └─────┤         │         │         │
║      %d %d %d │  d d d  │  d d d  │  d d d  │
║   VISITING │  d d d  │  d d d  │  d d d  │
╚════════════╧═════════╧═════════╧═════════╧═══════════════════════════════╝


1: ─ │
2: ┌ └ ┐ ┘
3: ├ ┤ ┴ ┬
4: ┼
```

> ## Main
>
> ### Get Base and Scanner

- Base(UI): Game State
  - Start Menu
  - Game
  - End Game
- Scanner: The only scanner used for uer input
- Printer: Used to print the game board and information

## Start Menu

### Used to choose load privious game or create new one

- Menu
  - New game
  - Load game
  - Quit

## Game

### Main part of the game

- Board
  - Attributes
    - Squire
    - player
    - Round
  - Methods
    - Save and Load
- Player
  - Attributes
    - name
    - money
    - position
    - properties
    - in jail
    - row dice
  - Methods
    - move (salary)
- Square
  - Attributes
    - Effect function
    - only change attributes in Board and player
  - Methods

## End Game

### Print who win the game, show players' money
