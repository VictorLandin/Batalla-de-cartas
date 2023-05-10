/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import SonidoEImagen.Imagen;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Cartas {

    private int ID_carta, ataque, defensa, coste, repeticiones;
    private String Efecto, imagen, objetivo;
    JLabel carta;

    public Cartas(int idCarta, int ataque, int defensa, int repeticiones, String objetivoString, String imagen) {
        carta = new JLabel();
        this.ID_carta = idCarta;
        this.ataque = ataque;
        this.defensa = defensa;
        this.objetivo = objetivoString;
        this.coste = 0;
        this.Efecto = null;
        this.imagen = imagen;
        carta.setSize(100, 180);
        ImageIcon im = new ImageIcon(imagen);
        ImageIcon imRedimensionada = Imagen.redimensionarimagen(im, carta.getWidth(), carta.getHeight());
        carta.setIcon(imRedimensionada);
        carta.setOpaque(true);
        carta.setVisible(true);
        
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public JLabel getCarta() {
        return carta;
    }
    
    public void refrescar(){
        this.carta.setOpaque(true);
        this.carta.setVisible(true);
        this.carta.setSize(100,180);
        this.carta.setLocation(carta.getX(), carta.getY());
    }

    /**
     *
     * @return
     */
    public int getID_carta() {
        return ID_carta;
    }

    /**
     *
     * @return
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     *
     * @return
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     *
     * @return
     */
    public int getCoste() {
        return coste;
    }

    /**
     *
     * @return
     */
    public String getEfecto() {
        return Efecto;
    }

    /**
     *
     * @return
     */
    public String getImagen() {
        return imagen;
    }

}
