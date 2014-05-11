package                     game;

public class                Global {
    
    private static Sounds   _sounds;
    private static Window   _window;
    private static Grid     _grid;
    private static int      _points = 0;
    private static int      _linesCompleted = 0;
    private static int      _currentLevel = 1;
    private static int      _fallingIterationDelay = 500;
    private static boolean  _gameIsPaused = false;
    private static boolean  _gameIsLost = false;
    
    public static void      main(String[] args) {
        
        _window = new Window();
        _window.drawMenu();
        _sounds = new Sounds();
    }
 
    
    public static void      startGame() {

        _sounds.playMainTheme();
        _window.repaint();
        
        while (_gameIsLost == false) {
            try {
                Thread.sleep(_fallingIterationDelay);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            if (_gameIsPaused == false) {
                _grid.pieceDown();
            }
            _window.repaint();
        }
    }
    
    public static void      restartGame() {
        
        _points = 0;
        _linesCompleted = 0;
        _currentLevel = 1;
        _fallingIterationDelay = 500;
        _gameIsPaused = false;
        _gameIsLost = false;
        _window.restartGame();
    }
    
    public static void      refresh() {
        
        _window.repaint();
    }
    
    public static void      setGrid() {
        
        _grid = new Grid();
    }
    
    public static Grid      getGrid() {
        
        return (_grid);
    }
    
    public static void      addPoints(char linesDeleted) {
        
        if (linesDeleted > 0 && linesDeleted <= 4) {
            _linesCompleted += linesDeleted;
            _points += Math.pow(2, linesDeleted) * 1000 * _currentLevel;
        }
        updateLevel();
    }
    
    private static void     updateLevel() {
        
        if (_linesCompleted <= 0) {
            _currentLevel = 1;
        }
        else if ((_linesCompleted >= 1) && (_linesCompleted <= 90)) {
            _currentLevel = 1 + ((_linesCompleted - 1) / 10);
        }
        else {
            _currentLevel = 10;
        }
        updateFallingDelay();
    }
    
    private static void     updateFallingDelay() {
        
        _fallingIterationDelay = ((11 - _currentLevel) * 50);
    }
    
    public static int       getScore() {
        
        return (_points);
    }
    
    public static int       getLevel() {
        
        return (_currentLevel);
    }
    
    public static int       getLinesCompleted() {
        
        return (_linesCompleted);
    }
    public static void      pauseGame() {
        
        if (_gameIsPaused == true) {
            _gameIsPaused = false;
            _sounds.playMainTheme();
        }
        else {
            _gameIsPaused = true;
            _sounds.pauseMainTheme();
        }
        Global.refresh();
    }
    
    public static boolean   isGamePaused() {
        
        return (_gameIsPaused);
    }
    
    public static boolean   isGameLost() {
        
        return (_gameIsLost);
    }
    
    public static void      gameLost() {
        
        _sounds.pauseMainTheme();
        _gameIsLost = true;
    }
}
