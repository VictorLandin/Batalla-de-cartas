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
            contadorPartida++;
        if (contadorPartida < 10) {
        if (slotIzq == null) {
            slotIzq = eventList[contadorPartida];
            slotIzq.setX(200);
        } else if (slotCen == null) {
            slotCen = eventList[contadorPartida];
            slotCen.setX(400);
        } else if (slotDer == null) {
            slotDer = eventList[contadorPartida];
            slotDer.setX(600);
        }
        
        return eventList[contadorPartida];
        }
        return null;
    }

    public void setContadorPartida(int contadorPartida) {
        this.contadorPartida = contadorPartida;
    }
    
}
