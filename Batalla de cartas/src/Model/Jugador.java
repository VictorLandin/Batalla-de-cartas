/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author a22victorlr
 */
public class Jugador extends Personajes {
    
    private int energia;
    private int regeneracionEnergia;
    private int vida;
    /**
     *
     * @param vida
     * @param mazo
     * @param energia
     * @param regeneracionEnergia
     */
    public Jugador(int vida, ArrayList<Cartas> mazo, int energia, int regeneracionEnergia) {
        super(vida, mazo, energia, regeneracionEnergia);
        this.energia = energia;
        this.mazo = mazo;
        this.regeneracionEnergia = regeneracionEnergia;
        this.vida = vida;
    }
    
    /**
     *
     * @param carta
     */
    public void meterCartaEnMazo(Cartas carta) {
        mazo.add(carta);
    }
    
    /**
     *
     * @param carta
     */
    public void quitarCartaDeMazo(Cartas carta) {
        mazo.remove(carta);
    }

    ArrayList<Cartas> getMazo() {
        return mazo;
    }
}
