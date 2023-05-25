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
    private int vidaActual;
    private int vidaMax;
    private int puntuacion;
    private int dificultad = 0;
    private Eventos[] eventList = new Eventos[10];
    private Mapa mapa = new Mapa();
    private Jugador jugador;
    private BDacceso bdAcceso;
    private UI interfaz;

    public Juego(UI interfaz) {
        crearJugador();
        this.interfaz = interfaz;
        
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public boolean comprarEnTienda(int precio) {
        if (dinero >= precio) {
            dinero -= precio;
            return true;
        }
        return false;
    }

    public void crearJugador() {
        int idJugador = 0; // id del jugador, en este caso se asume que es 0
        bdAcceso = new BDacceso(); // instancia la clase de acceso a la base de datos
        ArrayList<Cartas> cartasJugador = bdAcceso.obtenerCartasJugador(idJugador); // obtiene las cartas del jugador de la tabla n:m
        int vidaJugador = bdAcceso.obtenerVidaJugador(idJugador); // obtiene la vida del jugador de la tabla de jugadores
        int energiaJugador = bdAcceso.obtenerEnergiaJugador(idJugador); // obtiene la energía del jugador de la tabla de jugadores
        int regeneracionEnergiaJugador = bdAcceso.obtenerRegeneracionEnergiaJugador(idJugador); // obtiene la regeneración de energía del jugador de la tabla de jugadores

        // crea el objeto de la clase Jugador con las propiedades obtenidas
        Jugador player = new Jugador(vidaJugador, cartasJugador, energiaJugador, regeneracionEnergiaJugador);
        this.jugador = player;
    }


    public void empezar(JPanel mapaPanel) {
        generarEventos();
        mostrarEventos();
        vidaActual = jugador.getVidaBase();
        vidaMax = vidaActual;
        dinero=20;
        generarDatosPrimera(mapaPanel);
    }

    private void generarEventos() {
        for (int i = 0; i < eventList.length-1; i++) {
            Eventos evento = new Eventos();
            evento.generarTipo();
            eventList[i] = evento;
        }
        Eventos boss = new Eventos();
        boss.generarJefe();
        eventList[eventList.length-1] = boss;
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
        dificultad++;
        switch (tipo){
            case "EventoCofre" -> this.cofre();
            case "EventoTienda" -> this.tienda();
            default -> this.tipoBatalla(tipo);
        }
        mostrarEventoEspecifico(posicion);
    }

    public void actualizar() {
        interfaz.refrescar(dinero,vidaActual);
        interfaz.repaint();
    }

    private void mostrarEventoEspecifico(String posicion) {
        Eventos evento = mapa.mostrarEventoEspecifico(eventList, posicion);
        if (evento != null){
            JLabel cartaLabel = evento.crearLabel(this);
            interfaz.pintarEn(cartaLabel);
        }
    }

    private void cofre() {
        Cartas carta;
        carta = bdAcceso.generarCartaAleatoria(dificultad);
        
        interfaz.eventoCofre(carta);
    }

    private void tienda() {
        Cartas[] carta = new Cartas[3];
        carta[0] = bdAcceso.generarCartaAleatoria(dificultad);
        carta[1] = bdAcceso.generarCartaAleatoria(dificultad);
        carta[2] = bdAcceso.generarCartaAleatoria(dificultad);
        
        
        interfaz.eventoTienda(carta);
    }

    private void tipoBatalla(String tipo) {
        if ("eventoBatalla".equals(tipo)){
            this.empezarBatalla();
        } else {
            this.empezarBoss(tipo);
        }
    }

    private void empezarBatalla() {
        Enemigos Enemigo;
        Enemigo = bdAcceso.generarEnemigoAleatoria(dificultad);
        
        interfaz.batalla(Enemigo);
    }

    private void empezarBoss(String tipo) {
        
    }

    public void addCarta(Cartas carta) {
        jugador.meterCartaEnMazo(carta);
    }

    public void generarDatosPrimera(JPanel Panel) {
        interfaz.mostrarVida(vidaActual, Panel);
        interfaz.mostrarDinero(dinero, Panel);
        actualizar();
    }
}
