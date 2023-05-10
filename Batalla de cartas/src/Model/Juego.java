/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import BDacceso.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author a22victorlr
 */
public class Juego {

    private String situacion;
    private int dinero;
    private int puntuacion;
    private int dificultad;
    Jugador jugador;

    public Juego() {
        crearJugador();
    }

    public void mostrarMazo(JPanel panelMazo) {
    ArrayList<Cartas> mazo = jugador.getMazo();
    
    int columnas = 5;
    int x = 10; // Posición inicial x
    int y = 10; // Posición inicial y
    
    for (int i = 0; i < mazo.size(); i++) {
        Cartas carta = mazo.get(i);
        JLabel labelCarta = carta.getCarta();
        
        // Establece la posición (x, y) del JLabel
        labelCarta.setLocation(x, y);
        labelCarta.setSize(100, 180);
        panelMazo.add(labelCarta); // Añade el label al panel del mazo
        
        carta.refrescar();
        panelMazo.setComponentZOrder(labelCarta, 0);
        // Calcula la posición (x, y) del siguiente JLabel
        x += labelCarta.getWidth()+ 10; // Añade 10 píxeles de separación horizontal
        if ((i + 1) % columnas == 0) {
            x = 10; // Reinicia la posición x al inicio de la fila
            y += labelCarta.getHeight() + 10; // Añade 10 píxeles de separación vertical
        }
    }
}


    public void iniciarBatalla(Enemigos enemigo) {
        // cambiar la situación del juego a "batalla"
        // iniciar la batalla contra el enemigo
    }

    public void comprarEnTienda(Tienda tienda, Jugador jugador, Cartas carta) {
        int precio = tienda.getPrecio(carta);
        if (dinero >= precio) {
            this.restarDinero(precio);
            jugador.agregarCartaAlMazo(carta);
            tienda.quitarCartaDelInventario(carta);
        }
    }

    public void crearJugador() {
        int idJugador = 0; // id del jugador, en este caso se asume que es 0
        BDacceso bdAcceso = new BDacceso(); // instancia la clase de acceso a la base de datos
        ArrayList<Cartas> cartasJugador = bdAcceso.obtenerCartasJugador(idJugador); // obtiene las cartas del jugador de la tabla n:m
        int vidaJugador = bdAcceso.obtenerVidaJugador(idJugador); // obtiene la vida del jugador de la tabla de jugadores
        int energiaJugador = bdAcceso.obtenerEnergiaJugador(idJugador); // obtiene la energía del jugador de la tabla de jugadores
        int regeneracionEnergiaJugador = bdAcceso.obtenerRegeneracionEnergiaJugador(idJugador); // obtiene la regeneración de energía del jugador de la tabla de jugadores

        // crea el objeto de la clase Jugador con las propiedades obtenidas
        Jugador player = new Jugador(vidaJugador, cartasJugador, energiaJugador, regeneracionEnergiaJugador);
        this.jugador = player;
    }

    private void restarDinero(int precio) {

    }
}
