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
public final class Cartas {

    private int ID_carta, ataque, defensa, repeticiones, Efecto;
    private int precio;
    private String imagen, objetivo;
    private JLabel carta;
    private ImageIcon imRedimensionada;

    public Cartas(int idCarta, int ataque, int defensa, int repeticiones, String imagen, int Efecto) {
        
        this.ID_carta = idCarta;
        this.ataque = ataque;
        this.defensa = defensa;
        this.repeticiones = repeticiones;
        this.imagen = imagen;
        this.Efecto = Efecto;
        this.crearLabel();
        precio = this.calcularPrecio();
        
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

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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

    public int getPrecio() {
        return precio;
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

    public int calcularPrecio() {
        if (Efecto == 0){
            return ((2+ataque + defensa) * repeticiones) + 10;
        } else {
            return ((2+ataque + defensa) * repeticiones) + 15;
        }
    }

}
