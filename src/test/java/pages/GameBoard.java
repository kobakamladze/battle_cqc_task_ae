package pages;

import base.BaseScreen;
import base.elements.CellElement;
import base.elements.CellElements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CellCoordinate;
import utils.CellState;
import utils.Randomizer;
import utils.waiters.SmartWait;

public class GameBoard extends BaseScreen {

    private static final Logger log = LoggerFactory.getLogger(GameBoard.class.getSimpleName());

    private static final int CELL_ROW_COUNT = 10;
    private static final int CELL_COLUMN_COUNT = 10;
    private static final String HIT_CELL_LOC = ".battlefield-cell__hit";
    private static final String KILL_CELL_LOC = ".battlefield-cell__done";
    private static final String MISS_CELL_LOC = ".battlefield-cell__miss";
    private static final String OPPONENTS_BATTLEFIELD_LOC = ".battlefield__rival";
    private static final String OPPONENTS_BOARD = ".battlefield.battlefield__rival";
    private static final String WAITING_OPPONENTS_BOARD_LOC = "battlefield__wait";
    private static final String EMPTY_CELL_LOC = ".battlefield-cell__empty";
    private static final String CELL_LOC = ".battlefield-cell-content";
    private static final String CELL_GRID = ".battlefield-table-placeholder";

    CellElements hitCells;
    CellElements killedCells;
    CellElements emptyCells;
    CellElements missedCells;
    static CellState[][] cellsGrid = new CellState[CELL_ROW_COUNT][CELL_COLUMN_COUNT];

    public GameBoard() {
        super(By.cssSelector(".battlefield__rival" + " " + CELL_GRID), "Cells grid");
        syncBoard();
    }

    public void strike() {
        CellElement targetCell;
        CellCoordinate hitCheck = findHitCell();
        if (hitCheck != null){
            CellCoordinate emptyNeighborCoordinate = findEmptyNeighbor(hitCheck.x, hitCheck.y);
            if (emptyNeighborCoordinate != null) {
                targetCell = emptyCells.getCellByCoordinates(emptyNeighborCoordinate.x, emptyNeighborCoordinate.y);
                targetCell.click();
            }
        } else {
            targetCell = emptyCells.getCell(Randomizer.getRandom(emptyCells.getAmount()));
            targetCell.click();
        }
    }

    public boolean isMyTurn() {
        try {
            SmartWait.waitFor(ExpectedConditions.not(
                    ExpectedConditions.attributeContains(
                            By.cssSelector(OPPONENTS_BOARD),
                            "class",
                            WAITING_OPPONENTS_BOARD_LOC
                    )
            ), 5);
            return true;
        } catch (Exception e) {
            log.info("Not your turn");
            return false;
        }
    }

    private void syncBoard() {
        missedCells = new CellElements(By.cssSelector(OPPONENTS_BATTLEFIELD_LOC + " " + MISS_CELL_LOC + " " + CELL_LOC),
                "Miss cells");
        hitCells = new CellElements(By.cssSelector(OPPONENTS_BATTLEFIELD_LOC + " " + KILL_CELL_LOC + " " + CELL_LOC),
                "Hit cells");
        killedCells = new CellElements(By.cssSelector(OPPONENTS_BATTLEFIELD_LOC + " " + HIT_CELL_LOC + " " + CELL_LOC),
                "Killed cells");
        emptyCells = new CellElements(By.cssSelector(OPPONENTS_BATTLEFIELD_LOC + " " + EMPTY_CELL_LOC + " " + CELL_LOC),
                "Empty cells");
        fillCellsGrid(emptyCells);
        fillCellsGrid(missedCells);
        fillCellsGrid(killedCells);
        fillCellsGrid(hitCells);
    }

    private void fillCellsGrid(CellElements cells) {
        cells.getAllElements().forEach(e -> {
            int y = e.getCoordinates().y;
            int x = e.getCoordinates().x;
            cellsGrid[y][x] = e.getCellState();
        });
    }

    private CellCoordinate findHitCell() {
        syncBoard();
        System.out.println("=== GRID ===");
        for (Object[] row : cellsGrid) {
            for (Object o : row) {
                System.out.print(o + ", ");
            }
            System.out.println();
        }
        for (int r = 0; r < CELL_ROW_COUNT; r++) {
            for (int c = 0; c < CELL_COLUMN_COUNT; c++) {
                if (cellsGrid[r][c] == CellState.HIT) {
                    if (r - 1 >= 0 && r + 1 <= 9 && c - 1 >= 0 && c + 1 <= 9) {
                        if (cellsGrid[r + 1][c] == CellState.HIT || cellsGrid[r][c + 1] == CellState.HIT ||
                                cellsGrid[r - 1][c] == CellState.HIT || cellsGrid[r][c - 1] == CellState.HIT ||
                                cellsGrid[r + 1][c] == CellState.MISS || cellsGrid[r][c + 1] == CellState.MISS ||
                                cellsGrid[r - 1][c] == CellState.MISS || cellsGrid[r][c - 1] == CellState.MISS) {
                            continue;
                        }
                    }
                    return new CellCoordinate(r, c);
                }
            }
        }
        return null;
    }

    private CellCoordinate findEmptyNeighbor(int x, int y) {
        if (x - 1 >= 0) {
            if (cellsGrid[x-1][y] == CellState.EMPTY) {
                return new CellCoordinate(x-1, y);
            }
        } if (x + 1 <= 9) {
            if (cellsGrid[x+1][y] == CellState.EMPTY) {
                return new CellCoordinate(x+1, y);
            }
        } if (y - 1 >= 0) {
            if (cellsGrid[x][y-1] == CellState.EMPTY) {
                return new CellCoordinate(x, y-1);
            }
        } if (y + 1 <= 9) {
            if (cellsGrid[x][y+1] == CellState.EMPTY) {
                return new CellCoordinate(x, y+1);
            }
        }
        return null;
    }
}