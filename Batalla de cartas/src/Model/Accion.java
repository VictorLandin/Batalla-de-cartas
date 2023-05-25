/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author victor
 */
public class Accion {
    private int ID_accion, ataque, defensa, repeticiones, Efecto;
    private String objetivo;
    
    public Accion(int idAccion, int ataque, int defensa, int repeticiones) {
        
        this.ID_accion = idAccion;
        this.ataque = ataque;
        this.defensa = defensa;
        this.repeticiones = repeticiones;
        
    }

    public int getID_accion() {
        return ID_accion;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getEfecto() {
        return Efecto;
    }

    public String getObjetivo() {
        return objetivo;
    }
    
    
}
