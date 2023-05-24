/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import UI.*;
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
    private Eventos[] eventList = new Eventos[10];
    private Mapa mapa = new Mapa();
    Jugador jugador;
    UI interfaz;

    public Juego(UI interfaz) {
        crearJugador();
        this.interfaz = interfaz;
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

    public void empezar(JPanel mapaPanel) {
        generarEventos();
        mostrarEventos();
    }

    private void generarEventos() {
        for (int i = 0; i < eventList.length-1; i++) {
            Eventos evento = new Eventos();
            evento.generarTipo();
            eventList[i] = evento;
        }
        Eventos boss = new Eventos();
        boss.generarJefe();
        eventList[9] = boss;
    }
    
    public void mostrarEventos(){
        Eventos mostrarEvento;
        do{
        mostrarEvento = mapa.mostrarEventos(eventList);
        if (mostrarEvento != null){
            JLabel cartaLabel = mostrarEvento.crearLabel(this);
            interfaz.pintarEn(cartaLabel);
        }
        }while (mostrarEvento != null);
    }
    
    public void iniciar(String tipo, String posicion) {
        mostrarEventos();
    }

    public void actualizar() {
        interfaz.repaint();
    }
}
