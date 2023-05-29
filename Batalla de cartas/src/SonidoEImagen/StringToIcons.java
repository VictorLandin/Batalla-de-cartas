/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SonidoEImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author victor
 */
public class StringToIcons {

    private static final String directorio = "src/imagen/ASCI/";

    public static ImageIcon getIconRepresentation(String input, String tipo) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int extra = 0;
        panel.setBackground(new Color (0,0,0,0));
        int start = 0;
        String firstIconName = "default.png"; // Icono predeterminado para cualquier otro tipo de dato
        JLabel first = new JLabel("");
        first.setSize(16, 16);
        if ("d".equals(tipo)) {
            firstIconName = "dinero.png";
            start = 1;
            ImageIcon firstIcon = new ImageIcon(directorio + firstIconName);
            first.setIcon(firstIcon);
            panel.add(first);
            extra = 1;
        } else if ("v".equals(tipo)) {
            firstIconName = "vida.png";
            start = 1;
            ImageIcon firstIcon = new ImageIcon(directorio + firstIconName);
            first.setIcon(firstIcon);
            panel.add(first);
            extra = 1;
        } else if ("e".equals(tipo)) {
            firstIconName = "escudo.png";
            start = 1;
            ImageIcon firstIcon = new ImageIcon(directorio + firstIconName);
            first.setIcon(firstIcon);
            panel.add(first);
            extra = 1;
        } else if ("a".equals(tipo)) {
            firstIconName = "ataque.png";
            start = 1;
            ImageIcon firstIcon = new ImageIcon(directorio + firstIconName);
            first.setIcon(firstIcon);
            panel.add(first);
            extra = 1;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String iconName = getIconFileName(c);
            ImageIcon icon = new ImageIcon(directorio + iconName);
            JLabel label = new JLabel("");
            label.setSize(16, 16);
            label.setLocation((i+extra)*16, 0);
            label.setIcon(icon);
            panel.add(label);
        }
        
        panel.repaint();

        int width = panel.getComponentCount() * 16; // Ajusta el ancho del panel según la cantidad de iconos
        panel.setPreferredSize(new Dimension(width, 16));
        panel.setSize(width, 16);
        BufferedImage image;   
        // Convierte el panel en un icono
        if (panel.getWidth() == 0){
            image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        } else {
            image = new BufferedImage(panel.getWidth(), 16, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        return new ImageIcon(image);
    }

    private static String getIconFileName(char c) {
        return switch (c) {
            case '=' -> "equals.png";
            case '-' -> "minus.png";
            case '+' -> "plus.png";
            case '*' -> "times.png";
            default -> c + ".png";
        };
    }
}
