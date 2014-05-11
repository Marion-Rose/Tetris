
package                     game;

import                      java.awt.Color;



public class                Piece {
    
    private char            _pieceId;
    private char[][]        _tab;
    private int             _line;
    private int             _column;
    private int             _angle;

    static final char[][][] _pieces = {
                                   {{0,0,0,0},
                                    {0,1,1,0},
                                    {0,1,1,0},
                                    {0,0,0,0}},
                                   
                                   {{0,0,2,0},
                                    {0,0,2,0},
                                    {0,0,2,0},
                                    {0,0,2,0}},
                                   
                                   {{0,0,3,0},
                                    {0,0,3,3},
                                    {0,0,0,3},
                                    {0,0,0,0}},
                                   
                                   {{0,0,0,4},
                                    {0,0,4,4},
                                    {0,0,4,0},
                                    {0,0,0,0}},
                                   
                                   {{0,0,5,0},
                                    {0,0,5,0},
                                    {0,0,5,5},
                                    {0,0,0,0}},
                                   
                                   {{0,0,6,0},
                                    {0,0,6,0},
                                    {0,6,6,0},
                                    {0,0,0,0}},
                                   
                                   {{0,0,0,0},
                                    {0,7,7,7},
                                    {0,0,7,0},
                                    {0,0,0,0}}};
    
    public                  Piece(char pieceId) {
        
        this._pieceId = pieceId;
        this._tab = this._pieces[pieceId];
        this._line = 0;
        this._column = 4;
        this._angle = 0;
    }
    
    public void             moveDown() {
        
        ++this._line;
    }
    
    public void             moveUp() {
        
        --this._line;
    }    
    public int              getLine() {
        
        return (this._line);
    }
    
    public int              getColumn() {
        
        return (this._column);
    }
    
    public char             getPieceId() {
        
        return (this._pieceId);
    }
    
    public char[][]         getTab() {
        
        return (this._tab);
    }
    
    public void             moveRight()
    {
        Grid                mainGrid = Global.getGrid();
        
        ++this._column;
        if (mainGrid.isConflict() == true) {
            --this._column;
        }
        Global.refresh();
    }
    public void             moveLeft()
    {
        Grid                mainGrid = Global.getGrid();
        
        --this._column;
        if (mainGrid.isConflict() == true) {
            ++this._column;
        }
        Global.refresh();
    }
    
    public void             moveBottom()
    {
        Grid                mainGrid = Global.getGrid();
        
        mainGrid.pieceDown();
        Global.refresh();
    }
    
    public void             rotate(int theta)
    {
        char[][]            newTab =   {{0,0,0,0},
                                        {0,0,0,0},
                                        {0,0,0,0},
                                        {0,0,0,0}};
        int                 y;
        int                 x;
        int                 ybis;
        int                 xbis;
        int                 yter;
        int                 xter;
        
        if (this._pieceId == 0) {
            return;
        }

        if (this._pieceId == 1 && this._angle == 0) {
            theta = 90;
        }
        else if (this._pieceId == 1) {
            theta = -90;
        }

        this._angle += theta;
        x = -1;
        while (++x <= 3) {
            y = -1;
            while (++y <= 3) {
                if (this._tab[x][y] != 0) {
                    xbis = x - 1;
                    ybis = y - 2;

                    if (theta == 90) {
                        xter = ybis;
                        yter = -xbis;
                    }
                    else {
                        xter = -ybis;
                        yter = xbis;
                    }
                    
                    xter += 1;
                    yter += 2;
                    try {
                        newTab[xter][yter] = this._tab[x][y];
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
            }
        }
        this._tab = newTab;
        Global.refresh();
    }
    
    public String           getPieceImage(int id) {
   
        switch (id) {
            case 1:
               return("BLUE_SQUARE.png");
            case 2:
               return("RED_SQUARE.png");
            case 3:
               return("GREEN_SQUARE.png");
            case 4:
               return("YELLOW_SQUARE.png");
            case 5:
               return("PURPLE_SQUARE.png");
            case 6:
               return("BROWN_SQUARE.png");
            case 7:
               return("PINK_SQUARE.png");
            default :
               return("BLACK_SQUARE.png");
        }
    }

    public void             displayTab()
    {
        int                 i;
        int                 j;
        
        i = -1;
        while (++i <= 3) {
            j = -1;
            while (++j <= 3) {
                System.out.print((int) this._tab[i][j]);
            }
            System.out.print("\n");
        }
    }
}
