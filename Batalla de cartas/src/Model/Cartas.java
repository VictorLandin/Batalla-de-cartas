/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import SonidoEImagen.Imagen;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Cartas {

    private int ID_carta, ataque, defensa, repeticiones, Efecto;
    private String imagen, objetivo;
    JLabel carta;
    ImageIcon imRedimensionada;

    public Cartas(int idCarta, int ataque, int defensa, int repeticiones, String objetivoString, String imagen) {
        
        this.ID_carta = idCarta;
        this.ataque = ataque;
        this.defensa = defensa;
        this.objetivo = objetivoString;
        this.Efecto = 0;
        this.imagen = imagen;
        this.crearLabel();
        
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
        Dimension dimension = new Dimension();
        dimension.height = 180;
        dimension.width = 100;
        carta.setMinimumSize(dimension);
        this.carta.setSize(100,180);
        this.carta.setLocation(carta.getX(), carta.getY());
        carta.setIcon(imRedimensionada);
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


    public int getEfecto() {
        return Efecto;
    }

    public void setEfecto(int Efecto) {
        this.Efecto = Efecto;
    }

    /**
     *
     * @return
     */
    public String getImagen() {
        return imagen;
    }

    private void crearLabel() {
        carta = new JLabel();
        carta.setSize(150,192);
        
        
        
        String imagenDir = SonidoEImagen.Imagen.getImageLink(imagen);
        ImageIcon im = new ImageIcon(imagenDir);
        imRedimensionada = Imagen.redimensionarimagen(im, carta.getWidth(), carta.getHeight());
        carta.setIcon(imRedimensionada);
        
        
        
        carta.setOpaque(true);
        carta.setVisible(true);
        carta.setBackground(new Color(0,0,0,0));
        carta.setText("  ");    }

}
