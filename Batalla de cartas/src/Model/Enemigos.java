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
public class Enemigos extends Personajes{
    
    private String nombre;

    /**
     *
     * @param vida
     * @param mazo
     * @param energia
     * @param regeneracionEnergia
     */
    public Enemigos(int vida, ArrayList<Cartas> mazo, int energia, int regeneracionEnergia) {
        super(vida, mazo, energia, regeneracionEnergia);
    }
    
    // Métodos adicionales

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }
}

