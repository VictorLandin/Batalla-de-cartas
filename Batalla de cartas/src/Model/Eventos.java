/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author a22victorlr
 */
public class Eventos {
    private String tipo;
    private int x;
    private final int y = 100;
    private JLabel lblCarta;

    public String getTipo() {
        return tipo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void generarTipo(){
        int tipoInt;
        tipoInt = (int) (Math.random() * 100+1);
        if (tipoInt < 21){
            tipo = "EventoCofre";
        } else if (tipoInt < 81){
            tipo = "EventoBatalla";
        } else if (tipoInt < 101){
            tipo = "EventoTienda";
        }
        System.out.println(tipo);
    }

    public void generarJefe() {
        int tipoInt;
        tipoInt = (int) (Math.random() * 3+1);
        switch (tipoInt) {
            case 1 -> tipo = "BossCalabaza";
            case 2 -> tipo = "BossArbol";
            case 3 -> tipo = "BossArdilla";
        }
            System.out.println(tipo);
    }

    public JLabel crearLabel() {
        this.lblCarta = new JLabel("");
        this.lblCarta.setOpaque(true);
        this.lblCarta.setSize(100,120);
        this.lblCarta.setLocation(x, y);
        this.lblCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return lblCarta;
    }

    void actualizar() {
        this.lblCarta = new JLabel("");
        this.lblCarta.setOpaque(true);
        this.lblCarta.setSize(100,120);
        this.lblCarta.setLocation(x, y);
        this.lblCarta.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
    }
    
    
}
