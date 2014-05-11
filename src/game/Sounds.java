package             game;

import              java.io.*;
import              javax.sound.sampled.*;

public class        Sounds {
    
    Clip            _mainTheme;

    
    public          Sounds() {
        
        initFiles();
    }
    
    private void    initFiles() {
        
        try {
            File file = new File("Assets/Sounds/MainTheme.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            this._mainTheme = (Clip) AudioSystem.getLine(info);
            this._mainTheme.open(stream);
        }
        catch (Exception e) {
        }

    }
    
    public void     playMainTheme() {
        
        this._mainTheme.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void     pauseMainTheme() {
        
        this._mainTheme.stop();
    }
}
