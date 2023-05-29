/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import UI.*;
import BDacceso.*;
import SonidoEImagen.Imagen;
import SonidoEImagen.StringToIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author a22victorlr
 */
public class Juego {

    private int dinero = 20;
    private int vidaActual;
    private int vidaMax;
    private int puntuacion = 0;
    private int dificultad = 0;
    private Eventos[] eventList = new Eventos[10];
    private Mapa mapa = new Mapa();
    private Jugador jugador;
    private BDacceso bdAcceso;
    private UI interfaz;

    public Juego(UI interfaz) {
        this.interfaz = interfaz;
        bdAcceso = new BDacceso(); // instancia la clase de acceso a la base de datos
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public boolean comprarEnTienda(int precio) {
        if (dinero >= precio) {
            dinero -= precio;
            return true;
        }
        return false;
    }

    public Jugador crearJugador() {
        int idJugador = 0; // id del jugador, en este caso se asume que es 0

        ArrayList<Cartas> cartasJugador = bdAcceso.obtenerCartasJugador(idJugador); // obtiene las cartas del jugador de la tabla n:m
        int vidaJugador = bdAcceso.obtenerVidaJugador(idJugador); // obtiene la vida del jugador de la tabla de jugadores
        int energiaJugador = bdAcceso.obtenerEnergiaJugador(idJugador); // obtiene la energía del jugador de la tabla de jugadores
        int regeneracionEnergiaJugador = bdAcceso.obtenerRegeneracionEnergiaJugador(idJugador); // obtiene la regeneración de energía del jugador de la tabla de jugadores

        // crea el objeto de la clase Jugador con las propiedades obtenidas
        Jugador player = new Jugador(vidaJugador, cartasJugador, energiaJugador, regeneracionEnergiaJugador);
        this.jugador = player;
        return player;
    }

    public void empezar(JPanel mapaPanel) {
        puntuacion = 0;
        dificultad = 0;
        generarEventos();
        mostrarEventos();
        vidaActual = jugador.getVidaBase();
        vidaMax = vidaActual;
        actualizar();
    }

    private void generarEventos() {
        for (int i = 0; i < eventList.length - 1; i++) {
            Eventos evento = new Eventos();
            evento.generarTipo();
            eventList[i] = evento;
        }
        Eventos boss = new Eventos();
        boss.generarJefe();
        eventList[eventList.length - 1] = boss;
    }

    public void mostrarEventos() {
        Eventos mostrarEvento;
        do {
            mostrarEvento = mapa.mostrarEventos(eventList);
            if (mostrarEvento != null) {
                JLabel cartaLabel = mostrarEvento.crearLabel(this);
                interfaz.pintarEn(cartaLabel);
            }
        } while (mostrarEvento != null);
    }

    public void iniciar(String tipo, String posicion) {
        dificultad++;
        this.actualizar();
        switch (tipo) {
            case "EventoCofre" ->
                this.cofre();
            case "EventoTienda" ->
                this.tienda();
            default ->
                this.tipoBatalla(tipo);
        }
        mostrarEventoEspecifico(posicion);
    }

    public void actualizar() {
        interfaz.refrescar(dinero, vidaActual, dificultad);
        interfaz.repaint();
    }

    private void mostrarEventoEspecifico(String posicion) {
        Eventos evento = mapa.mostrarEventoEspecifico(eventList, posicion);
        if (evento != null) {
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
        if ("EventoBatalla".equals(tipo)) {
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
        int id = 10;
        Enemigos Enemigo;
        Enemigo = bdAcceso.generarJefe(id, dificultad);

        interfaz.batalla(Enemigo);
    }

    public void addCarta(Cartas carta) {
        jugador.meterCartaEnMazo(carta);
        modificarPuntuacion(carta.getPrecio());
    }

    public void addVida() {
        if (vidaMax > vidaActual) {
            vidaActual++;
        }
    }

    public void addDinero(int cantidad) {
        dinero += cantidad;
    }

    public void quitarVida(int cantidad) {

        vidaActual -= cantidad;
    }

    public void modificarPuntuacion(int cantidad) {
        puntuacion += cantidad;
    }

    public void perder() {
        modificarPuntuacion(dificultad - 5);
        modificarPuntuacion(dinero);
        interfaz.registrar(puntuacion);
    }

    public void ganar() {
        modificarPuntuacion(dificultad - 5);
        modificarPuntuacion(100);
        modificarPuntuacion(dinero);
        interfaz.registrar(puntuacion);
    }

    public void registrar(String nombre) {
        bdAcceso.addPuntuacion(puntuacion, nombre);
    }

    public void generarPuntuaciones(JPanel puntosPanel) {
        puntosPanel.removeAll();
        for (int i = 0; i < 10; i++) {
            puntuaciones sPuntuacion = bdAcceso.sacarPuntuacion(i);
            if (sPuntuacion != null) {
                String nombreString = sPuntuacion.getNombre();
                int tamañoXnombre = (nombreString.length()) * 16;
                ImageIcon nombreIcon;
                nombreIcon = StringToIcons.getIconRepresentation(nombreString, "0");

                JLabel nombreLbl = new JLabel();
                nombreLbl.setSize(new Dimension(tamañoXnombre * 2, 32));

                nombreLbl.setIcon(null);
                ImageIcon nombreIconRedime = Imagen.redimensionarimagen(nombreIcon, tamañoXnombre * 2, 32);
                nombreLbl.setIcon(nombreIconRedime);

                String puntos = sPuntuacion.getPuntuacion() + " P";
                int tamañoXpuntos = (puntos.length()) * 16;
                ImageIcon puntosIcon;
                puntosIcon = StringToIcons.getIconRepresentation(puntos, "0");

                JLabel puntosLbl = new JLabel();
                puntosLbl.setSize(new Dimension(tamañoXpuntos * 2, 32));

                puntosLbl.setIcon(null);
                ImageIcon puntosIconRedime = Imagen.redimensionarimagen(puntosIcon, tamañoXpuntos * 2, 32);
                puntosLbl.setIcon(puntosIconRedime);

                puntosLbl.setVisible(true);
                puntosLbl.setOpaque(true);
                puntosLbl.setBackground(new Color(0, 0, 0, 0));

                nombreLbl.setVisible(true);
                nombreLbl.setOpaque(true);
                nombreLbl.setBackground(new Color(0, 0, 0, 0));

                puntosPanel.add(nombreLbl);
                nombreLbl.setLocation(10, (i * 32) + 10);
                puntosPanel.setComponentZOrder(nombreLbl, 0);
                puntosPanel.add(puntosLbl);
                puntosLbl.setLocation(74, (i * 32) + 42);
                puntosPanel.setComponentZOrder(puntosLbl, 0);

                JButton atrasButton = new JButton();
                String atrasDirec = SonidoEImagen.Imagen.getImageLink("Atras");
                atrasButton.setBounds(304, 500, 192, 56);
                atrasButton.setBackground(new Color(0, 0, 0, 0));

                ImageIcon atras = new ImageIcon(atrasDirec);
                ImageIcon atrasRedimen = Imagen.redimensionarimagen(atras, atrasButton.getWidth(), atrasButton.getHeight());
                atrasButton.setIcon(atrasRedimen);

                puntosPanel.add(atrasButton);
                puntosPanel.setComponentZOrder(atrasButton, 0);
                atrasButton.setOpaque(true);

                atrasButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Acciones a realizar cuando se hace clic en el boton
                        interfaz.volverMenu(puntosPanel);
                    }
                });
                puntosPanel.repaint();
                actualizar();
            }
        }
    }
}
