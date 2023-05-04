/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import BDacceso.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
        int filas = (int) Math.ceil(mazo.size() / 5.0); // calcula el número de filas necesarias
        panelMazo.setLayout(new GridLayout(filas, 5)); // establece el layout en forma de rejilla
        for (Cartas carta : mazo) {
            ImageIcon imagenCarta = new ImageIcon(carta.getImagen());
            JLabel labelCarta = new JLabel(imagenCarta);
            panelMazo.add(labelCarta); // añade el label al panel del mazo
        }
        panelMazo.revalidate(); // revalida el panel para que se muestren los cambios
        panelMazo.repaint(); // repinta el panel para que se muestren los cambios
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
        jugador = new Jugador(vidaJugador, cartasJugador, energiaJugador, regeneracionEnergiaJugador);

    }

    private void restarDinero(int precio) {
        
    }
}
