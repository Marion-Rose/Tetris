package                 game;
import                  java.util.Random;

public class            Grid {
    
    public char[][]     _grid = new char[23][12];
    private Piece       _waitingPiece;
    private Piece       _activePiece;
    
    public              Grid() {
        
        initGrid();
    }
    
    private void        initGrid() {

        int             i;
        int             j;
        
        i = -1;
        while (++i <= 22) {
            j = -1;
            while (++j <= 11) {
                if (i == 22) {
                    this._grid[i][j] = 1;
                }
                else {
                    this._grid[i][j] = 0;
                }
            }
        }
        this.addPiece(true);
    }
    
    public void         addPiece(boolean firstPiece) {
        
        Random          rand = new Random();
        
        if (firstPiece == true) {
            this._activePiece = new Piece((char) rand.nextInt(7));
        }
        else {
            this._activePiece = this._waitingPiece;
        }
        this._waitingPiece = new Piece((char) rand.nextInt(7));
    }
    
    public boolean      isConflict() {
        
        int             i;
        int             j = 0;
        char[][]        pieceGrid = this._activePiece.getTab();
        int             pieceLine = this._activePiece.getLine();
        int             pieceColumn = this._activePiece.getColumn();

        while (j < 4) {
            i = 4;
            while (--i >= 0 && pieceGrid[i][j] == 0);
            if (i >= 0) {
                if ((pieceColumn + j) < 0 || (pieceColumn + j) > 11) {
                    return (true);
                }
                if (_grid[pieceLine + i][pieceColumn + j] != 0) {
                    return (true);
                }
            }
            ++j;
        }
        return (false);
    }
    
    public boolean      pieceDown() {
        
        this._activePiece.moveDown();
        if (isConflict() == false) {
            return (true);
        }
        else {
            this._activePiece.moveUp();
            this.mergePiece();
            if (this.checkDefeat() == true) {
                Global.gameLost();
                return (false);
            }
            this.checkLines();
            this.addPiece(false);
            return (false);
        }
    }
    
    private void        mergePiece() {
        
        int             i = -1;
        int             j;
        char[][]        pieceGrid = this._activePiece.getTab();
        int             pieceLine = this._activePiece.getLine();
        int             pieceColumn = this._activePiece.getColumn();
        
        while (++i <= 3) {
            j = -1;
            while (++j <= 3) {
                if (pieceGrid[i][j] != 0) {
                    _grid[pieceLine + i][pieceColumn + j] = (char)(this._activePiece.getPieceId() + 1);
                }
            }
        }
    }
    
    public Piece        getActivePiece() {
        
        return (this._activePiece);
    }
    
    public Piece        getWaitingPiece() {
        return (this._waitingPiece);
    }

    private void        checkLines() {
        
        int             i = 22;
        int             j;
        int             count;
        char            linesDeleted = 0;
        
        while (--i >= 0) {
            j = -1;
            count = 0;
            while (++j <= 11) {
                if (this._grid[i][j] != 0) {
                    ++count;
                }
            }
            if (count == 12) {
                ++linesDeleted;
                this.moveGridDown(i);
                ++i;
            }
        }
        if (linesDeleted != 0) {
            Global.addPoints(linesDeleted);
        }
    }
    
    private void        moveGridDown(int line) {
        
        int             j;
        
        while (line >= 4) {
            j = -1;
            while (++j <= 11) {
                this._grid[line][j] = this._grid[line - 1][j];
            }
            --line;
        }
    }
    
    private boolean     checkDefeat() {
        
        int             i;
        int             j = 0;
        char[][]        pieceGrid = this._activePiece.getTab();
        int             pieceLine = this._activePiece.getLine();
        int             pieceColumn = this._activePiece.getColumn();

        while (j < 4) {
            i = 4;
            while (--i >= 0 && pieceGrid[i][j] == 0);
            if (i >= 0) {
                if ((pieceLine + i) <= 3) {
                    return (true);
                }
            }
            ++j;
        }
        return (false);    
    }
    
    private void        displayGrid() { // FONCTION A SUPPRIMER !!!!!!!!!!
        
        int             i = 0;
        int             j;
        
        System.out.print("\n\nTABLE :\n");
        while (i <= 22) {
            j = 0;
            while (j <= 11) {
                System.out.print((int) this._grid[i][j]);
                ++j;
            }
            System.out.print("\n");
            ++i;
        }
        System.out.print("\n\n");    
    }
 }
