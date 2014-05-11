package             game;

import              java.awt.Color;
import              java.awt.Font;
import              java.awt.Graphics;
import              java.awt.Image;
import              java.awt.Toolkit;
import              javax.swing.JPanel;


public class        GameUI extends JPanel {

    public void     paintComponent(Graphics g) {

        Grid        mainGrid = Global.getGrid();
        Piece       activePiece = mainGrid.getActivePiece();
        int         pieceLine = activePiece.getLine();
        int         pieceColumn = activePiece.getColumn();
        char[][]    pieceGrid = activePiece.getTab();
        Piece       waitingPiece = mainGrid.getWaitingPiece();
        Font        font;

        super.paintComponent(g);
        for(int j = 0; j <= 23; j++) {
            g.drawLine(65, 60+36*j, 497, 60+36*j);
        } 
        for(int i = 0; i <= 12; i++) {
            g.drawLine(65+36*i, 60, 65+36*i, 707); 
        }  

        for (int i = 3; i <= 21; i = i+1) {
            for (int j = 0; j <= 11; j = j+1) {
               if (mainGrid._grid[i][j] != 0)
               {
                    Image img1 = Toolkit.getDefaultToolkit().getImage("Assets/Images/" + activePiece.getPieceImage(mainGrid._grid[i][j]));
                    g.drawImage(img1, 65+36*j, 60+36*(i - 4), 36, 36, this);
               }
            }
        }

        for (int i = 0; i <= 3; i = i+1) {
            for (int j = 0; j <= 3; j = j+1) {
                if (pieceGrid[i][j] != 0 && (60+36*(i + pieceLine) - (36*3) > 60)) {
                    Image img1 = Toolkit.getDefaultToolkit().getImage("Assets/Images/" + activePiece.getPieceImage(pieceGrid[i][j]));
                    g.drawImage(img1, 65+36*(j + pieceColumn), 60+36*(i + pieceLine) - (36*4), 36, 36, this);
                }
            }
        }
        
        if (Global.isGamePaused() == false) {
            pieceGrid = waitingPiece.getTab();

            for (int i = 0; i <= 3; i = i+1) {
                for (int j = 0; j <= 3; j = j+1) {
                    if (pieceGrid[i][j] != 0) {
                        Image img1 = Toolkit.getDefaultToolkit().getImage("Assets/Images/" + activePiece.getPieceImage(pieceGrid[i][j]));
                        g.drawImage(img1, 620+36*(j), 70+36*(i), 36, 36, this);
                    }
                }
            }
        }

        g.setColor(Color.darkGray);
        g.drawRect(620, 70, 144, 144);
        g.drawLine(65, 60, 65, 708);
        g.drawLine(65, 708, 501, 708);
        g.fillRoundRect(600, 300, 200, 40, 50, 50);
        g.fillRoundRect(600, 500, 200, 40, 50, 50);

        if (Global.isGameLost() == true) {
            g.fillRoundRect(64, 300, 436, 80, 50, 50);
            g.setColor(Color.red);
            font = new Font("Courier", Font.BOLD, 40);
            g.setFont(font);
            this.drawStringCentered(g, "VOUS AVEZ PERDU", 282, 355);

            g.setColor(Color.darkGray);
            g.fillRoundRect(64, 400, 436, 65, 50, 50);
            font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.black);
            this.drawStringCentered(g, "Appuyez sur Entrer pour rejouer", 282, 425);
            this.drawStringCentered(g, "Ou sur Echap pour quitter.", 282, 455);
        }
        else if (Global.isGamePaused() == true) {
            font = new Font("Courier", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.darkGray);
            g.fillRect(65, 60, 497 - 65, 707 - 58);
            g.setColor(Color.black);
            this.drawStringCentered(g, "PAUSE", 282, 355);
            font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            this.drawStringCentered(g, "Appuyez sur Espace pour reprendre", 282, 425);
            this.drawStringCentered(g, "Ou sur Echap pour quitter.", 282, 455);
        }
        
        g.setColor(Color.black);
        font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("Score", 672, 327);
        g.drawString("Niveau", 672, 527);
        g.setColor(Color.gray);

        font = new Font("Courier", Font.BOLD, 40);
        g.setFont(font);

        this.drawStringCentered(g, Integer.toString(Global.getScore()), 700, 400);
        this.drawStringCentered(g, Integer.toString(Global.getLevel()), 700, 600);
    }
 
    private void    drawStringCentered(Graphics g, String string, int x, int y) {
        
        double      stringLen = g.getFontMetrics().getStringBounds(string, g).getWidth();
        
        g.drawString(string, (int) (x - (stringLen / 2.0)), y);   
    }
 }