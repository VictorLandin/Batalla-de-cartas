/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SonidoEImagen;

import Model.ExceptionLogger;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author a22victorlr
 */
public class Sonido {
    private final String carpetaSonidos = "sonido/";
    private final String extensionSonidos = ".wav";
    
    private Clip backgroundMusic;
    private Clip battleMusic;

    /**
     * constructor 
     * (no utilizado)
     */
    public Sonido() {
    }

    /**
     *
     * @param soundName
     * trae el nombre del sonido y lo hace sonar
     */
    public void playSound(String soundName) {
        try {
            File soundFile = new File(carpetaSonidos + soundName + extensionSonidos);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            ExceptionLogger.logException(e);
        }
    }
    
    /**
     * hace que funciona la musica de fondo
     */
    public void playMusic() {
        try {
            // Cargar la música de fondo
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(AudioSystem.getAudioInputStream(new File("background_music.wav")));

            // Cargar la música de batalla
            battleMusic = AudioSystem.getClip();
            battleMusic.open(AudioSystem.getAudioInputStream(new File("battle_music.wav")));

            // Reproducir la música de fondo en un hilo separado
            Thread backgroundMusicThread = new Thread(() -> {
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            });
            backgroundMusicThread.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            ExceptionLogger.logException(e);
        }
    }

    /**
     *
     */
    public void startBattleMusic() {
        // Detener la música de fondo
        backgroundMusic.stop();
        
        // Reproducir la música de batalla en un hilo separado
        Thread battleMusicThread = new Thread(() -> {
            battleMusic.loop(Clip.LOOP_CONTINUOUSLY);
        });
        battleMusicThread.start();
    }

    /**
     *
     */
    public void stopBattleMusic() {
        battleMusic.stop();
        battleMusic.flush();
        
        Thread backgroundMusicThread = new Thread(() -> {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        });
        backgroundMusicThread.start();
    }
}
