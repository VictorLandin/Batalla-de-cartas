/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import SonidoEImagen.Imagen;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Enemigos extends Personajes {

    private String nombre;
    private ArrayList<Accion> acciones;
    private JLabel enemigo;
    private ImageIcon imRedimensionada;

    /**
     *
     * @param vida
     * @param mazo
     * @param energia
     * @param regeneracionEnergia
     */
    public Enemigos(int vida, ArrayList<Accion> mazo, int energia, int regeneracionEnergia, String nombre) {
        super(vida, energia, regeneracionEnergia);
        this.nombre = nombre;
        this.acciones = mazo;
        this.vida = vida;
        crearLabel();
    }

    public JLabel getEnemigo() {
        return enemigo;
    }

    // Métodos adicionales
    private void crearLabel() {
        enemigo = new JLabel();
        if ("boss".equals(nombre)) {
            enemigo.setSize(200, 200);
        } else {
            enemigo.setSize(100, 100);
        }

        String imagenDir = SonidoEImagen.Imagen.getImageLink(nombre);
        ImageIcon im = new ImageIcon(imagenDir);
        imRedimensionada = Imagen.redimensionarimagen(im, enemigo.getWidth(), enemigo.getHeight());
        enemigo.setIcon(imRedimensionada);

        enemigo.setOpaque(true);
        enemigo.setVisible(true);
        enemigo.setBackground(new Color(0, 0, 0, 0));
        enemigo.setText("  ");
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Accion> getAcciones() {
        return acciones;
    }

    public void restarVida(int ataque) {
        vida -= ataque;
    }

}
