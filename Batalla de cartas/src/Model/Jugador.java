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

    public Jugador(int vida, ArrayList<Cartas> mazo, int energia, int regeneracionEnergia) {
        super(vida, mazo, energia, regeneracionEnergia);
    }
    
    
    public void meterCartaEnMazo(Cartas carta) {
        mazo.add(carta);
    }
    
    public void quitarCartaDeMazo(Cartas carta) {
        mazo.remove(carta);
    }
}
