/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author a22victorlr
 */
public class Mapa {

    private Eventos slotIzq, slotCen, slotDer;
    private int contadorPartida = -1;
    
    public Eventos mostrarEventos(Eventos[] eventList) {
        if (contadorPartida < 10) {
        if (slotIzq == null) {
            contadorPartida++;
            slotIzq = eventList[contadorPartida];
            slotIzq.setX(75);       
            return eventList[contadorPartida];
        } else if (slotCen == null) {
            contadorPartida++;
            slotCen = eventList[contadorPartida];
            slotCen.setX(325);        
            return eventList[contadorPartida];
        } else if (slotDer == null) {
            contadorPartida++;
            slotDer = eventList[contadorPartida];
            slotDer.setX(575);
            return eventList[contadorPartida];
        } 

        }
        return null;
    }

    public void setContadorPartida(int contadorPartida) {
        this.contadorPartida = contadorPartida;
    }
    
}
