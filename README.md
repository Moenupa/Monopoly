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

## Main

### Get Base and Scanner

- Base (UI): Game State
  - Start Menu
  - Game
  - End Game
- Scanner: The only scanner used for uer input
- Printer: Used to print the game board and information

## Start Menu

### Used to choose load privious game or create new one

- Menu
  - New game
    - Create new Board for Game
  - Continue
    - Select which Board to be load
  - Quit

## Game

### Main part of the game

- Board
  - Attributes
    - Squires
    - Players
    - Round
    - Player Index (Whose turn)
  - Methods
    - Operations to attributes
    - Initialization
    - Get saved game list
    - Save
    - Load
- Player
  - Attributes
    - Name
    - Money
    - Position (square index: 0, 1, ...)
    - Properties
    - In jail state / cool down (Max turns remaining no move)
    - Bankrupted state
  - Methods
    - Operations to attributes
    - Move (Salary)
    - Go to Jail
    - Row dice
- Square
  - Attributes
    - (Position?)
    - Name
    - Price
    - Rent
    - Owner
  - Methods
    - Execute (effect of the square)
      - Change attributes in Board and Player
    - Jail Effect

## End Game

### Print who win the game, show all players' money

# Shortage

- [ ] Input checking
  - nextInt(): If user input is string (not int)
