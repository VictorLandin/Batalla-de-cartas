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
       
        if (slotIzq == null) {
            contadorPartida++;
            slotIzq = eventList[contadorPartida];
            slotIzq.setX(75);       
            slotIzq.setPosicion("Izq");
            return eventList[contadorPartida];
        } else if (slotCen == null) {
            contadorPartida++;
            slotCen = eventList[contadorPartida];
            slotCen.setX(325);        
            slotCen.setPosicion("Cen");
            return eventList[contadorPartida];
        } else if (slotDer == null) {
            contadorPartida++;
            slotDer = eventList[contadorPartida];
            slotDer.setX(575);
            slotDer.setPosicion("Der");
            return eventList[contadorPartida];
        } 
        return null;
    }

        public Eventos mostrarEventoEspecifico(Eventos[] eventList, String posicion) {
        if (contadorPartida < eventList.length-1) {
        if (null != posicion) switch (posicion) {
                case "Izq" -> {
                    contadorPartida++;
                    slotIzq = eventList[contadorPartida];
                    slotIzq.setX(75);
                    slotIzq.setPosicion("Izq");
                    return eventList[contadorPartida];
            }
                case "Cen" -> {
                    contadorPartida++;
                    slotCen = eventList[contadorPartida];
                    slotCen.setX(325);
                    slotCen.setPosicion("Cen");
                    return eventList[contadorPartida];
            }
                case "Der" -> {
                    contadorPartida++;
                    slotDer = eventList[contadorPartida];
                    slotDer.setX(575);
                    slotDer.setPosicion("Der");
                    return eventList[contadorPartida];
            }
                default -> {
            }
            } 

        }
        return null;
    }
    
    
    public void setContadorPartida(int contadorPartida) {
        this.contadorPartida = contadorPartida;
    }
    
}
