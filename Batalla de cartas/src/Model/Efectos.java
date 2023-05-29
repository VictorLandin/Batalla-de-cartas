/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author a22victorlr
 */
public class Efectos {
    //esta clase registra que efectos has segun su ID, deversas cartas o acciones generaran efectos
    public void addEfecto(int id){
        String tipo = null;
        switch (id){
            case 1 -> tipo = "def";
            case 2 -> tipo = "heal";
            case 3 -> tipo = "atq";
            case 4 -> tipo = "regen";
        }
    }

    
    
}
