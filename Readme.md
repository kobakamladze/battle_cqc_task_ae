# Battleship Automation Framework

## Game Logic

The opponent's board is represented as a 2D array, where each cell represents `CellState` enum value:

| `EMPTY` | Empty cell |
| `HIT` | Hit but not killed |
| `KILL` | Ship killed |
| `MISS` | missed |

### Strategy
After every move the 2D array is synchronized with the recent state of the board,
before every strike methods are looking for HIT state element in 2D array, if found,
method gets its neighbors and tries to hit them.

P.S - 0 usage of AI
