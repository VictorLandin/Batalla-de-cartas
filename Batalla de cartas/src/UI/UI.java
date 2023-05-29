/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Model.*;
import SonidoEImagen.Imagen;
import SonidoEImagen.StringToIcons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author a22victorlr
 */
public class UI extends javax.swing.JFrame {

    private Juego partida;
    private UI interfaz;
    private JLabel vidaEnemigoLbl;
    private Jugador player;
    private int eventosCompletados = 0;
    private int escudo = 0;
    private int defensa = 0;
    private int escudoEnemigo = 0;
    private boolean acabar = false;

    private Enemigos Enemigo;
    private ArrayList<Cartas> mazo;
    private ArrayList<Cartas> descarte;
    private Accion next;
    private Iterator<Accion> itAcciones;
    private ArrayList<Accion> acciones;
    private JLabel EnemigoLbl;
    private JLabel escudoEnemigoLbl;
    private String nombre;
    private JLabel nombreLbl;

    /**
     * Creates new form UI
     */
    public UI() {

        interfaz = this;
        initComponents();
        partida = new Juego(this);
        colocarImagenes();
        mapaPanel.setVisible(false);
        batallaPanel.setVisible(false);
        StatsPanel.setVisible(false);
        eventoPanel.setVisible(false);
        Iniciar.setVisible(true);
        Iniciar.setBackground(new Color(0, 0, 0, 0));
        Iniciar.repaint();
    }

    public void pintarEn(JLabel label) {
        mapaPanel.add(label);
        mapaPanel.setComponentZOrder(label, 0);
        label.setOpaque(true);

        mapaPanel.repaint();
    }

    public void eventoCofre(Cartas carta) {
        JLabel cartaLbl = carta.getCarta();
        mapaPanel.setVisible(false);
        eventoPanel.setVisible(true);

        eventoPanel.add(cartaLbl);
        eventoPanel.setComponentZOrder(cartaLbl, 0);
        cartaLbl.setLocation(325, 200);
        cartaLbl.setOpaque(true);

        cartaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el JLabel
                partida.addCarta(carta);
                eventoPanel.remove(cartaLbl);
                eventoPanel.repaint();
                interfaz.irAMapa(eventoPanel);
                cartaLbl.removeMouseListener(this);
            }
        });

        ArrayList<JLabel> lblsTesoro = new ArrayList<JLabel>();
        lblsTesoro.add(cartaLbl);
        addBotonVolver(lblsTesoro);
        eventoPanel.repaint();
    }

    public void eventoTienda(Cartas[] carta) {
        mapaPanel.setVisible(false);
        eventoPanel.setVisible(true);

        ArrayList<JLabel> lblsTienda = new ArrayList<JLabel>();

        for (int i = 0; i < carta.length; i++) {
            lblsTienda.addAll(this.cargarCartaTienda(carta[i], i));
        }

        addBotonVolver(lblsTienda);

    }

    public void registrar(int puntuacion) {
        puntosPanel.setVisible(true);
        puntosPanel.removeAll();
        StatsPanel.setVisible(false);
        batallaPanel.setVisible(false);
        nombreLbl = new JLabel();
        puntosPanel.add(nombreLbl);
        nombreLbl.setBounds(35, 10, 730, 64);

        JLabel puntosLbl = new JLabel();
        puntosPanel.add(puntosLbl);
        puntosLbl.setBounds(35, 128, 730, 64);
        String puntosString = String.valueOf(puntuacion) + " P";
        int tamañoX = (puntosString.length()) * 64;
        ImageIcon puntosIcon;
        puntosIcon = StringToIcons.getIconRepresentation(puntosString, "0");

        puntosLbl.setSize(tamañoX, 64);

        puntosLbl.setIcon(null);
        ImageIcon puntosIconRedime = Imagen.redimensionarimagen(puntosIcon, tamañoX, 64);
        puntosLbl.setIcon(puntosIconRedime);

        generarBotones();
    }

    public void generarBotones() {
        int labelWidth = 64; // Ancho de cada JLabel
        int labelHeight = 64; // Alto de cada JLabel
        int columns = 1; // Número de columnas llevadas

        int x = 35; // Posición x inicial
        int y = 300; // Posición y inicial

        // Generar y agregar las etiquetas para cada letra del alfabeto
        for (char c = 'A'; c <= 'Z'; c++) {
            JLabel label = new JLabel();
            label.setText(Character.toString(c));
            label.setBounds(x, y, labelWidth, labelHeight); // Establecer posición y tamaño del JLabel

            String lblDirec = SonidoEImagen.Imagen.getImageLink("ASCI/" + c);
            ImageIcon miImagen = new ImageIcon(lblDirec);
            ImageIcon miImagenRedimensionada = Imagen.redimensionarimagen(miImagen, labelWidth, labelHeight);
            label.setIcon(miImagenRedimensionada);

            puntosPanel.add(label);
            label.setOpaque(true);
            label.setBackground(new Color(0, 0, 0, 0));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Acciones a realizar cuando se hace clic en el JLabel
                    if (nombre == null) {
                        nombre = label.getText();
                    } else {
                        nombre = nombre + label.getText();
                    }
                    repaintNombre();
                    puntosPanel.repaint();
                }

            });
            puntosPanel.repaint();

            x += labelWidth + 10; // Avanzar a la siguiente columna

            // Avanzar a la siguiente fila si se llega al final de la columna
            if (columns == 10) {
                x = 35; // Reiniciar la posición x
                columns = 0;
                y += labelHeight + 10; // Avanzar a la siguiente fila
            }
            columns++;
        }
        JLabel labelBackspace = new JLabel();
        labelBackspace.setBounds(x + 74, y, 124, labelHeight); // Establecer posición y tamaño del JLabel

        String BackspaceDirec = SonidoEImagen.Imagen.getImageLink("ASCI/back");
        ImageIcon BackspaceImagen = new ImageIcon(BackspaceDirec);
        ImageIcon BackspaceImagenRedimensionada = Imagen.redimensionarimagen(BackspaceImagen, 124, labelHeight);
        labelBackspace.setIcon(BackspaceImagenRedimensionada);

        puntosPanel.add(labelBackspace);
        labelBackspace.setOpaque(true);
        labelBackspace.setBackground(new Color(0, 0, 0, 0));
        labelBackspace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el JLabel
                if (nombre != null) {
                    if (nombre.length() != 0) {
                        nombre = nombre.substring(0, nombre.length() - 1);
                    }

                }
                repaintNombre();
                puntosPanel.repaint();
            }

        });

        JLabel confirmar = new JLabel();
        confirmar.setBounds(x + 208, y, 124, labelHeight); // Establecer posición y tamaño del JLabel

        String confirmarDirec = SonidoEImagen.Imagen.getImageLink("ASCI/confirmar");
        ImageIcon confirmarImagen = new ImageIcon(confirmarDirec);
        ImageIcon confirmarImagenRedimensionada = Imagen.redimensionarimagen(confirmarImagen, 124, labelHeight);
        confirmar.setIcon(confirmarImagenRedimensionada);

        puntosPanel.add(confirmar);
        confirmar.setOpaque(true);
        confirmar.setBackground(new Color(0, 0, 0, 0));
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nombre != null) {
                    if (nombre.length() != 0) {
                        partida.registrar(nombre);
                        repaintNombre();
                        puntosPanel.repaint();
                        partida = new Juego(interfaz);
                        puntosPanel.setVisible(false);
                        menuPanel.setVisible(true);
                    }
                }

            }

        });
        puntosPanel.repaint();
    }

    private void repaintNombre() {
        int tamañoX = (nombre.length()) * 64;
        ImageIcon nombreIcon;
        nombreIcon = StringToIcons.getIconRepresentation(nombre, "0");

        nombreLbl.setSize(tamañoX, 64);

        nombreLbl.setIcon(null);
        ImageIcon nombreIconRedime = Imagen.redimensionarimagen(nombreIcon, tamañoX, 64);
        nombreLbl.setIcon(nombreIconRedime);
    }

    public void batalla(Enemigos Enemy) {
        this.Enemigo = Enemy;
        mapaPanel.setVisible(false);
        batallaPanel.setVisible(true);

        EnemigoLbl = Enemigo.getEnemigo();
        vidaEnemigoLbl = new JLabel();

        batallaPanel.add(EnemigoLbl);
        batallaPanel.setComponentZOrder(EnemigoLbl, 0);

        EnemigoLbl.setLocation(350, 200);
        EnemigoLbl.setOpaque(true);

        vidaEnemigoLbl.setBackground(new Color(0, 0, 0, 0));
        vidaEnemigoLbl.setLocation(350, 300);
        vidaEnemigoLbl.setOpaque(true);

        vidaEnemigoLbl.setIcon(null);
        String vidaEnemigo = String.valueOf(Enemigo.getVida());
        int tamañoX = (vidaEnemigo.length() + 1) * 16;
        ImageIcon vidaIcon;
        vidaIcon = StringToIcons.getIconRepresentation(vidaEnemigo, "v");

        vidaEnemigoLbl.setSize(tamañoX * 2, 32);

        ImageIcon vidaIconRedime = Imagen.redimensionarimagen(vidaIcon, tamañoX * 2, 32);
        vidaEnemigoLbl.setIcon(vidaIconRedime);

        batallaPanel.add(vidaEnemigoLbl);
        batallaPanel.setComponentZOrder(vidaEnemigoLbl, 0);

        batallaPanel.repaint();
        empezarBatalla();
    }

    private void empezarBatalla() {
        escudoEnemigoLbl = new JLabel();
        batallaPanel.add(escudoEnemigoLbl);
        batallaPanel.setComponentZOrder(escudoEnemigoLbl, 0);
        acciones = Enemigo.getAcciones();
        mazo = player.getMazo();
        descarte = new ArrayList<>();
        Collections.shuffle(mazo);
        itAcciones = acciones.iterator();
        next = itAcciones.next();
        escudo = 0;
        defensa = 0;

        turnoJugador();
    }

    private void turnoOponente() {
        int ataque = next.getAtaque();
        escudoEnemigo = 0;
        escudoEnemigo = next.getDefensa();
        if (ataque < escudo) {
            escudo -= ataque;
        } else {
            ataque -= escudo;
            escudo = 0;
            partida.quitarVida(ataque);
            partida.actualizar();
            partida.modificarPuntuacion(ataque);
        }
        if (escudoEnemigo != 0) {
            mostrarEscudoEnemigo();

            escudoEnemigoLbl.setVisible(true);
        } else {
            escudoEnemigoLbl.setVisible(false);
        }
        if (!itAcciones.hasNext()) {
            itAcciones = acciones.iterator();
        }

        next = itAcciones.next();
        mostrarEscudo();
        if (batallaAcabada()) {
            partida.perder();
        } else {
            turnoJugador();
        }
    }

    private ArrayList<JLabel> mostrarSigAccion() {
        ArrayList<JLabel> accionesLbl = new ArrayList<>();
        int ataque = next.getAtaque();
        int escudoEn = next.getDefensa();
        if (ataque != 0) {
            JLabel ataqueLbl = sigAccionIcon("a", ataque);
            ataqueLbl.setLocation(350, 160);
            batallaPanel.add(ataqueLbl);
            accionesLbl.add(ataqueLbl);
            batallaPanel.setComponentZOrder(ataqueLbl, 0);
        }
        if (escudoEn != 0) {
            JLabel escudoLbl = sigAccionIcon("e", escudoEn);
            escudoLbl.setLocation(350, 120);
            batallaPanel.add(escudoLbl);
            accionesLbl.add(escudoLbl);
            batallaPanel.setComponentZOrder(escudoLbl, 0);
        }
        batallaPanel.repaint();
        return accionesLbl;
    }

    private JLabel sigAccionIcon(String tipo, int cantidad) {
        String precioString = String.valueOf(cantidad);
        int tamañoX = (precioString.length() + 1) * 16;

        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(precioString, tipo);
        JLabel accionLbl = new JLabel();
        accionLbl.setSize(new Dimension(tamañoX * 2, 32));

        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX * 2, 32);
        accionLbl.setIcon(precioIconRedime);

        return accionLbl;
    }

    private void turnoJugador() {
        ArrayList<JLabel> mostrarSigAccion = mostrarSigAccion();
        escudo = 0;
        mostrarEscudo();
        acabar = false;
        ArrayList<JLabel> mano = new ArrayList<JLabel>();
        for (int i = 0; i < 3; i++) {
            if (mazo.isEmpty()) {
                mazo.addAll(descarte);
                descarte.removeAll(descarte);
                Collections.shuffle(mazo);
            }
            Cartas carta = mazo.get(0);
            mano.add(carta.getCarta());
            mano.addAll(mostrarSigAccion);
            descarte.add(carta);
            mazo.remove(0);
            crearCartaMano(carta, i);
            addBotonAcabar(mano);

        }
    }

    private void addBotonAcabar(ArrayList<JLabel> cartaDisp) {
        Iterator<JLabel> it = cartaDisp.iterator();
        JButton acabarButton = new JButton();
        String acabarDirec = SonidoEImagen.Imagen.getImageLink("AcabarTurno");
        acabarButton.setBounds(600, 500, 192, 56);
        acabarButton.setBackground(new Color(0, 0, 0, 0));

        ImageIcon acabar = new ImageIcon(acabarDirec);
        ImageIcon acabarRedimen = Imagen.redimensionarimagen(acabar, Iniciar.getWidth(), Iniciar.getHeight());
        acabarButton.setIcon(acabarRedimen);

        batallaPanel.add(acabarButton);
        batallaPanel.setComponentZOrder(acabarButton, 0);
        acabarButton.setOpaque(true);

        acabarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el boton
                while (it.hasNext()) {
                    JLabel cartaDisp1 = it.next();
                    batallaPanel.remove(cartaDisp1);
                }
                partida.actualizar();
                batallaPanel.remove(acabarButton);
                if (!batallaAcabada()) {
                    turnoOponente();
                } else {
                    acabarBatalla();
                }
                batallaPanel.repaint();
                acabarButton.removeMouseListener(this);
            }

        });
    }

    private boolean batallaAcabada() {
        return Enemigo.getVida() <= 0 || partida.getVidaActual() <= 0;
    }

    private void acabarBatalla() {
        if (descarte != null) {
            mazo.addAll(descarte);
            descarte.removeAll(descarte);
        }
        batallaPanel.remove(vidaEnemigoLbl);
        batallaPanel.remove(EnemigoLbl);
        escudoEnemigoLbl.setVisible(false);
        EscudoLbl.setVisible(false);
        batallaPanel.repaint();
        partida.addDinero(7);
        partida.modificarPuntuacion(15);
        irAMapa(batallaPanel);
        partida.actualizar();
    }

    private void crearCartaMano(Cartas carta, int lugar) {
        JLabel cartaLbl = carta.getCarta();
        batallaPanel.add(cartaLbl);
        batallaPanel.setComponentZOrder(cartaLbl, 0);
        cartaLbl.setLocation((170 * lugar) + 100, 400);
        cartaLbl.setOpaque(true);
        cartaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el JLabel
                int ataque = carta.getAtaque();
                int efecto = carta.getID_carta();
                int rep = carta.getRepeticiones();
                for (int i = 0; i < rep; i++) {
                    hacerAtaque(ataque);
                    if (carta.getDefensa() != 0) {
                        escudo += carta.getDefensa() + defensa;
                    }
                    mostrarEscudo();
                    if (efecto == 4) {
                        defensa += 1;
                    } else if (efecto == 5) {
                        partida.addVida();
                    }
                }
                StatsPanel.repaint();
                batallaPanel.remove(cartaLbl);
                batallaPanel.repaint();
                partida.actualizar();
                cartaLbl.removeMouseListener(this);
            }
        });
        batallaPanel.repaint();
        StatsPanel.repaint();
    }

    private void hacerAtaque(int ataque) {
        if (ataque < escudoEnemigo) {
            escudoEnemigo -= ataque;
        } else {
            ataque -= escudoEnemigo;
            escudoEnemigo = 0;
            Enemigo.restarVida(ataque);
            actVidaEnemigo();
            mostrarEscudoEnemigo();
            partida.modificarPuntuacion(ataque);
        }
    }

    private void mostrarEscudo() {
        if (escudo != 0) {
            EscudoLbl.setVisible(true);
            String escudoString = String.valueOf(escudo);
            int tamañoX = (escudoString.length() + 1) * 16;
            ImageIcon escudoIcon;
            escudoIcon = StringToIcons.getIconRepresentation(escudoString, "e");

            EscudoLbl.setSize(new Dimension(tamañoX * 2, 32));

            EscudoLbl.setIcon(null);
            ImageIcon escudoIconRedime = Imagen.redimensionarimagen(escudoIcon, tamañoX * 2, 32);
            EscudoLbl.setIcon(escudoIconRedime);
        } else {
            EscudoLbl.setVisible(false);
        }
        StatsPanel.repaint();
    }

    private void mostrarEscudoEnemigo() {

        String escudoString = String.valueOf(escudoEnemigo);
        int tamañoX = (escudoString.length() + 1) * 16;
        ImageIcon escudoIcon;
        escudoIcon = StringToIcons.getIconRepresentation(escudoString, "e");

        escudoEnemigoLbl.setSize(new Dimension(tamañoX * 2, 32));
        escudoEnemigoLbl.setLocation(350, 340);

        escudoEnemigoLbl.setIcon(null);
        ImageIcon escudoIconRedime = Imagen.redimensionarimagen(escudoIcon, tamañoX * 2, 32);
        escudoEnemigoLbl.setIcon(escudoIconRedime);

    }

    private void actVidaEnemigo() {
        vidaEnemigoLbl.setIcon(null);
        String vidaEnemigo = String.valueOf(Enemigo.getVida());
        int tamañoX = (vidaEnemigo.length() + 1) * 16;
        ImageIcon vidaIcon;
        vidaIcon = StringToIcons.getIconRepresentation(vidaEnemigo, "v");

        vidaEnemigoLbl.setSize(tamañoX * 2, 32);

        ImageIcon vidaIconRedime = Imagen.redimensionarimagen(vidaIcon, tamañoX * 2, 32);
        vidaEnemigoLbl.setIcon(vidaIconRedime);

        partida.actualizar();
        batallaPanel.repaint();
        StatsPanel.repaint();
    }

    public void mostrarVida(int vidaActual) {
        String vidaString = String.valueOf(vidaActual) + "/" + String.valueOf(partida.getVidaMax());
        int tamañoX = (vidaString.length() + 1) * 16;
        ImageIcon vidaIcon;
        vidaIcon = StringToIcons.getIconRepresentation(vidaString, "v");

        VidaLbl.setSize(tamañoX * 2, 32);

        VidaLbl.setIcon(null);
        ImageIcon vidaIconRedime = Imagen.redimensionarimagen(vidaIcon, tamañoX * 2, 32);
        VidaLbl.setIcon(vidaIconRedime);

    }

    public void mostrarDinero(int dinero) {
        String dineroString = String.valueOf(dinero);
        int tamañoX = (dineroString.length() + 1) * 16;
        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(dineroString, "d");

        dineroLbl.setSize(new Dimension(tamañoX * 2, 32));

        dineroLbl.setIcon(null);
        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX * 2, 32);
        dineroLbl.setIcon(precioIconRedime);

    }

    private void addBotonVolver(ArrayList<JLabel> cartaDisp) {
        Iterator<JLabel> it = cartaDisp.iterator();
        JButton atrasButton = new JButton();
        String atrasDirec = SonidoEImagen.Imagen.getImageLink("Atras");
        atrasButton.setBounds(304, 500, 192, 56);
        atrasButton.setBackground(new Color(0, 0, 0, 0));

        ImageIcon atras = new ImageIcon(atrasDirec);
        ImageIcon atrasRedimen = Imagen.redimensionarimagen(atras, Iniciar.getWidth(), Iniciar.getHeight());
        atrasButton.setIcon(atrasRedimen);

        eventoPanel.add(atrasButton);
        eventoPanel.setComponentZOrder(atrasButton, 0);
        atrasButton.setOpaque(true);

        atrasButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el boton
                while (it.hasNext()) {
                    JLabel cartaDisp1 = it.next();
                    eventoPanel.remove(cartaDisp1);
                }
                partida.actualizar();
                eventoPanel.remove(atrasButton);
                eventoPanel.repaint();
                interfaz.irAMapa(eventoPanel);
                atrasButton.removeMouseListener(this);
            }
        });
    }

    private ArrayList<JLabel> cargarCartaTienda(Cartas carta, int i) {
        JLabel cartaLbl = carta.getCarta();
        int precio = carta.calcularPrecio();
        eventoPanel.add(cartaLbl);
        eventoPanel.setComponentZOrder(cartaLbl, 0);
        cartaLbl.setLocation(75 + (i * 250), 200);
        JLabel Precio = colocarPrecio(precio, cartaLbl.getX());
        cartaLbl.setOpaque(true);
        cartaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el JLabel
                if (partida.comprarEnTienda(precio)) {
                    partida.addCarta(carta);
                    eventoPanel.remove(Precio);
                    eventoPanel.remove(cartaLbl);
                    partida.actualizar();
                    eventoPanel.repaint();
                    cartaLbl.removeMouseListener(this);
                }
            }
        });
        ArrayList<JLabel> lblsTienda = new ArrayList<JLabel>();
        lblsTienda.add(Precio);
        lblsTienda.add(cartaLbl);
        return lblsTienda;

    }

    private JLabel colocarPrecio(int precio, int posicion) {
        String precioString = String.valueOf(precio);
        int tamañoX = (precioString.length() + 1) * 16;
        int posicionX = (posicion + 75) - (tamañoX);
        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(precioString, "d");
        JLabel dineroLbl = new JLabel();
        dineroLbl.setSize(new Dimension(tamañoX * 2, 32));
        dineroLbl.setLocation(posicionX, 400);

        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX * 2, 32);
        dineroLbl.setIcon(precioIconRedime);

        eventoPanel.add(dineroLbl);
        eventoPanel.setComponentZOrder(dineroLbl, 0);

        eventoPanel.repaint();
        return dineroLbl;

    }

    private void irAMapa(JPanel panelAnt) {
        eventosCompletados++;
        if (eventosCompletados == 10) {
            panelAnt.setVisible(false);
            partida.ganar();
        } else {
            panelAnt.setVisible(false);
            mapaPanel.setVisible(true);
        }
    }

    public void volverMenu(JPanel puntosPanel) {
        puntosPanel.setVisible(false);
        menuPanel.setVisible(true);
    }

    private void colocarImagenes() {
        //Strings de dirección
        String menuDirec = SonidoEImagen.Imagen.getImageLink("Menu");
        String evtDirec = SonidoEImagen.Imagen.getImageLink("Evento");
        String batallaDirec = SonidoEImagen.Imagen.getImageLink("Batalla");
        String mapaDirec = SonidoEImagen.Imagen.getImageLink("Mapa");
        String inicDirec = SonidoEImagen.Imagen.getImageLink("Inicio");
        String scoresDirec = SonidoEImagen.Imagen.getImageLink("Scores");

        ImageIcon menu = new ImageIcon(menuDirec);
        ImageIcon menuRedimen = Imagen.redimensionarimagen(menu, menuLabel.getWidth(), menuLabel.getHeight());
        this.menuLabel.setIcon(menuRedimen);

        ImageIcon evento = new ImageIcon(evtDirec);
        ImageIcon eventoRedimen = Imagen.redimensionarimagen(evento, eventoLabel.getWidth(), eventoLabel.getHeight());
        this.eventoLabel.setIcon(eventoRedimen);

        ImageIcon batalla = new ImageIcon(batallaDirec);
        ImageIcon batallaRedimen = Imagen.redimensionarimagen(batalla, batallaLabel.getWidth(), batallaLabel.getHeight());
        this.batallaLabel.setIcon(batallaRedimen);

        ImageIcon mapa = new ImageIcon(mapaDirec);
        ImageIcon mapaRedimen = Imagen.redimensionarimagen(mapa, mapaLabel.getWidth(), mapaLabel.getHeight());
        this.mapaLabel.setIcon(mapaRedimen);

        ImageIcon inic = new ImageIcon(inicDirec);
        ImageIcon inicRedimen = Imagen.redimensionarimagen(inic, Iniciar.getWidth(), Iniciar.getHeight());
        this.Iniciar.setIcon(inicRedimen);

        ImageIcon scores = new ImageIcon(scoresDirec);
        ImageIcon scoresRedimen = Imagen.redimensionarimagen(scores, puntuacionButton.getWidth(), puntuacionButton.getHeight());
        this.puntuacionButton.setIcon(scoresRedimen);
        
        menuLabel.setComponentZOrder(puntuacionButton, 0);
        menuLabel.setComponentZOrder(Iniciar, 0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StatsPanel = new javax.swing.JPanel();
        dineroLbl = new javax.swing.JLabel();
        difLbl = new javax.swing.JLabel();
        VidaLbl = new javax.swing.JLabel();
        EscudoLbl = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        puntuacionButton = new javax.swing.JButton();
        Iniciar = new javax.swing.JButton();
        menuLabel = new javax.swing.JLabel();
        mapaPanel = new javax.swing.JPanel();
        mapaLabel = new javax.swing.JLabel();
        eventoPanel = new javax.swing.JPanel();
        eventoLabel = new javax.swing.JLabel();
        batallaPanel = new javax.swing.JPanel();
        batallaLabel = new javax.swing.JLabel();
        puntosPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(816, 639));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StatsPanel.setBackground(new java.awt.Color(0, 0, 0));
        StatsPanel.setMinimumSize(new java.awt.Dimension(800, 100));
        StatsPanel.setPreferredSize(new java.awt.Dimension(800, 50));

        dineroLbl.setMaximumSize(new java.awt.Dimension(128, 32));
        dineroLbl.setMinimumSize(new java.awt.Dimension(64, 32));

        VidaLbl.setMinimumSize(new java.awt.Dimension(192, 32));

        EscudoLbl.setMinimumSize(new java.awt.Dimension(192, 32));

        javax.swing.GroupLayout StatsPanelLayout = new javax.swing.GroupLayout(StatsPanel);
        StatsPanel.setLayout(StatsPanelLayout);
        StatsPanelLayout.setHorizontalGroup(
            StatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(dineroLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(difLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(VidaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(StatsPanelLayout.createSequentialGroup()
                .addGap(602, 602, 602)
                .addComponent(EscudoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        StatsPanelLayout.setVerticalGroup(
            StatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(StatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dineroLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(difLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VidaLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(EscudoLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(StatsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 80));

        menuPanel.setBackground(new java.awt.Color(60, 3, 65));
        menuPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        puntuacionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntuacionButtonActionPerformed(evt);
            }
        });

        Iniciar.setOpaque(true);
        Iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(puntuacionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(puntuacionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(Iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        menuLabel.setLocation(0, 0);

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mapaPanel.setBackground(new java.awt.Color(60, 63, 5));
        mapaPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mapaPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        mapaLabel.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout mapaPanelLayout = new javax.swing.GroupLayout(mapaPanel);
        mapaPanel.setLayout(mapaPanelLayout);
        mapaPanelLayout.setHorizontalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mapaPanelLayout.setVerticalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(mapaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        eventoPanel.setBackground(new java.awt.Color(0, 3, 5));

        javax.swing.GroupLayout eventoPanelLayout = new javax.swing.GroupLayout(eventoPanel);
        eventoPanel.setLayout(eventoPanelLayout);
        eventoPanelLayout.setHorizontalGroup(
            eventoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        eventoPanelLayout.setVerticalGroup(
            eventoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(eventoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        batallaPanel.setBackground(new java.awt.Color(0, 63, 65));
        batallaPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout batallaPanelLayout = new javax.swing.GroupLayout(batallaPanel);
        batallaPanel.setLayout(batallaPanelLayout);
        batallaPanelLayout.setHorizontalGroup(
            batallaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(batallaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        batallaPanelLayout.setVerticalGroup(
            batallaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(batallaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(batallaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        puntosPanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout puntosPanelLayout = new javax.swing.GroupLayout(puntosPanel);
        puntosPanel.setLayout(puntosPanelLayout);
        puntosPanelLayout.setHorizontalGroup(
            puntosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        puntosPanelLayout.setVerticalGroup(
            puntosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(puntosPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed
        // TODO add your handling code here:

        player = partida.crearJugador();
        StatsPanel.setVisible(true);
        VidaLbl.setVisible(true);
        dineroLbl.setVisible(true);
        menuPanel.setVisible(false);
        mapaPanel.setVisible(true);
        partida.empezar(mapaPanel);
        eventosCompletados = 0;

    }//GEN-LAST:event_IniciarActionPerformed

    private void puntuacionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntuacionButtonActionPerformed
        // TODO add your handling code here:
        menuPanel.setVisible(false);
        puntosPanel.setVisible(true);
        partida.generarPuntuaciones(puntosPanel);
    }//GEN-LAST:event_puntuacionButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    public Juego getPartida() {
        return partida;
    }

    public JLabel getBatallaLabel() {
        return batallaLabel;
    }

    public JPanel getBatallaPanel() {
        return batallaPanel;
    }

    public JLabel getEventoLabel() {
        return eventoLabel;
    }

    public JPanel getEventoPanel() {
        return eventoPanel;
    }

    public JLabel getMapaLabel() {
        return mapaLabel;
    }

    public JPanel getMapaPanel() {
        return mapaPanel;
    }

    public JLabel getMenuLabel() {
        return menuLabel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EscudoLbl;
    private javax.swing.JButton Iniciar;
    private javax.swing.JPanel StatsPanel;
    private javax.swing.JLabel VidaLbl;
    private javax.swing.JLabel batallaLabel;
    private javax.swing.JPanel batallaPanel;
    private javax.swing.JLabel difLbl;
    private javax.swing.JLabel dineroLbl;
    private javax.swing.JLabel eventoLabel;
    private javax.swing.JPanel eventoPanel;
    private javax.swing.JLabel mapaLabel;
    private javax.swing.JPanel mapaPanel;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel puntosPanel;
    private javax.swing.JButton puntuacionButton;
    // End of variables declaration//GEN-END:variables

    public void refrescar(int dinero, int vidaActual, int dificultad) {

        String dineroString = String.valueOf(dinero);
        int tamañoXDinero = (dineroString.length() + 1) * 16;
        ImageIcon DineroIcon;
        DineroIcon = StringToIcons.getIconRepresentation(dineroString, "d");
        dineroLbl.setIcon(null);

        dineroLbl.setSize(new Dimension(tamañoXDinero * 2, 32));

        ImageIcon DineroIconRedime = Imagen.redimensionarimagen(DineroIcon, tamañoXDinero * 2, 32);
        dineroLbl.setIcon(DineroIconRedime);

        String vidaString = String.valueOf(vidaActual) + "/" + String.valueOf(partida.getVidaMax());
        int tamañoXVida = (vidaString.length() + 1) * 16;
        ImageIcon VidaIcon;
        VidaIcon = StringToIcons.getIconRepresentation(vidaString, "v");
        VidaLbl.setIcon(null);

        VidaLbl.setSize(new Dimension(tamañoXVida * 2, 32));

        ImageIcon VidaIconRedime = Imagen.redimensionarimagen(VidaIcon, tamañoXVida * 2, 32);
        VidaLbl.setIcon(VidaIconRedime);

        String difString = ("Nivel " + String.valueOf(dificultad));
        int tamañoXdif = (difString.length()) * 16;
        ImageIcon DifIcon;
        DifIcon = StringToIcons.getIconRepresentation(difString, "0");
        difLbl.setIcon(null);

        difLbl.setSize(new Dimension(tamañoXVida * 2, 32));

        ImageIcon DifIconRedime = Imagen.redimensionarimagen(DifIcon, tamañoXdif * 2, 32);
        difLbl.setIcon(DifIconRedime);

        dineroLbl.repaint();
        VidaLbl.repaint();
        difLbl.repaint();
        StatsPanel.repaint();
    }

}
