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

    /**
     * constructor
     */
    public Sonido() {
    }

    /**
     *
     * @param soundName
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
}
