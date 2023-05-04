/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Cartas {
    private int ID_carta, ataque, defensa, coste, repeticiones;
    private String Efecto, imagen;

    public Cartas(int idCarta, int ataque, int defensa, int repeticiones, String objetivoString, String imagen) {
        JLabel carta = new JLabel();
        this.ID_carta = idCarta;
        this.ataque = ataque;
        this.defensa = defensa;
        
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
