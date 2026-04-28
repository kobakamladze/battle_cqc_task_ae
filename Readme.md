Basic logic:
    Board of sea battle is synchronized with 2d array that represents
state of the elements (CellState enum). Every time after hit, code is looking for
CellState.HIT value in 2d array, gets its neighbors and tries to hit them.