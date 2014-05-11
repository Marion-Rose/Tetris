package game;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;


public class            Window extends JFrame {
 
    private CardLayout     _cardLayout = new CardLayout();
    private JPanel         _content = new JPanel();
    private GameUI         _game  = new GameUI(); // creation d'un objet panneau pour dessiner
    private JPanel         _menu;
    private JButton        _buttonPlay = new JButton("Jouer");

    private Thread         _t;

    public                 Window() {

       setTitle("Tétris"); // donne un titre à la fenêtre
       setSize(900, 800); // définit la taille de la fenêtre (largeur, hauteur) 
       setResizable(false); // taille non modifiable 
       setAlwaysOnTop(true); // fenêtre au premier plan 
       setLocationRelativeTo(null); // fenêtre centrée à l'écran 
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // rend la croix de fermeture 
       setVisible(true); // rend la fenêtre visible
       this._content.addKeyListener(new KeyboardListener());

       this.initMenu();
   }

    private void        initMenu() {
        
        JPanel          line1 = new JPanel();
        JPanel          line2 = new JPanel();
        JButton         playButton = new JButton("Jouer");
        JButton         exitButton = new JButton("Quitter");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Global.setGrid();
                initGame();
                drawGame();
                _t = new Thread(new runGame());
                _t.start();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        line1.setLayout(new BoxLayout(line1, BoxLayout.LINE_AXIS));
        line1.add(playButton, BorderLayout.CENTER);

        line2.setLayout(new BoxLayout(line2, BoxLayout.LINE_AXIS));
        line2.add(exitButton, BorderLayout.CENTER);    

        this._menu = new JPanel();
        this._menu.setLayout(new BoxLayout(this._menu, BoxLayout.PAGE_AXIS));
        this._menu.add(line1, BorderLayout.CENTER);
        this._menu.add(line2, BorderLayout.CENTER);

        this._content.setLayout(this._cardLayout);
        this._content.add(this._menu, "Menu");
    }
 
    public void         restartGame() {
     
        Global.setGrid();
        initGame();
        drawGame();
        this._t = new Thread(new runGame());
        this._t.start();    
    }
 
    public void         drawMenu() {

        getContentPane().add(this._content);
    }
 
    private void        initGame() {

        this._game = new GameUI();
        this._game.setBackground(Color.BLACK);
        this._content.setLayout(this._cardLayout);
        this._content.add(this._game, "Game");
    }
 
    private void        drawGame() {
        
        getContentPane().add(this._content);
        this._cardLayout.show(this._content, "Game");
        this._content.requestFocusInWindow();
    }
}

class                   runGame implements Runnable {
      
    public void         run() {
        
        Global.startGame();                   
    }               
}