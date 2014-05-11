package         game;

import          java.awt.event.KeyListener;
import          java.awt.event.KeyEvent;

public class    KeyboardListener implements KeyListener{
    
    public void keyPressed(KeyEvent event) {
        
        Piece   activePiece = Global.getGrid().getActivePiece();
        
        if (Global.isGameLost() == true) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    Global.restartGame();
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
            }
        }
        else if (Global.isGamePaused() == false) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    activePiece.moveRight();
                    break;
                case KeyEvent.VK_LEFT:
                    activePiece.moveLeft();
                    break;
                case KeyEvent.VK_UP:
                    activePiece.rotate(-90);
                    if (Global.getGrid().isConflict())
                        activePiece.rotate(90);
                    break;
                case KeyEvent.VK_DOWN:
                   activePiece.moveBottom();
                    break;
                case KeyEvent.VK_SPACE:
                    Global.pauseGame();
                    break;
            }
        }
        else {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    Global.pauseGame();
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
            }
        }
    }
    public void keyReleased(KeyEvent event) {
    }

    public void keyTyped(KeyEvent event) {
    }
}
