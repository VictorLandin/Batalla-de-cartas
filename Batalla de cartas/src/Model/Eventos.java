/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import SonidoEImagen.Imagen;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Eventos {
    private String tipo;
    private String posicion;
    private int x;
    private final int y = 100;
    private JLabel lblEvento;
    private Juego juego;

    public String getTipo() {
        return tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getY() {
        return y;
    }

    public void generarTipo(){
        int tipoInt;
        tipoInt = (int) (Math.random() * 100+1);
        if (tipoInt < 31){
            tipo = "EventoCofre";
        } else if (tipoInt < 81){
            tipo = "EventoBatalla";
        } else if (tipoInt < 101){
            tipo = "EventoTienda";
        }
    }

    public void generarJefe() {
        tipo = "Boss";
    }

    public JLabel crearLabel(Juego juego) {
        this.juego = juego;
        this.lblEvento = new JLabel("");
        this.lblEvento.setOpaque(true);
        this.lblEvento.setSize(150,192);
        this.lblEvento.setLocation(x, y);
        this.lblEvento.setBackground(new Color(0,0,0,0));
        String lblDirec = SonidoEImagen.Imagen.getImageLink(tipo);
        ImageIcon menu = new ImageIcon(lblDirec);
        ImageIcon menuRedimen = Imagen.redimensionarimagen(menu, lblEvento.getWidth(), lblEvento.getHeight());
        this.lblEvento.setIcon(menuRedimen);
             // Agregar el "click listener" al JLabel
        this.lblEvento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Acciones a realizar cuando se hace clic en el JLabel
                lblEvento.getParent().remove(lblEvento);
                juego.actualizar();
                juego.iniciar(tipo, posicion);
            }
        });
        return lblEvento;
    }}

    