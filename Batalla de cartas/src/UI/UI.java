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
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author a22victorlr
 */
public class UI extends javax.swing.JFrame {

    private Juego partida;
    UI interfaz;
    JLabel vidaLbl;
    JLabel dineroActLbl;
    
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
        partida.generarDatosPrimera(eventoPanel);

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
        partida.generarDatosPrimera(eventoPanel);
        
        ArrayList<JLabel> lblsTienda = new ArrayList<JLabel>();

        for (int i = 0; i < carta.length; i++) {
            lblsTienda.addAll(this.cargarCartaTienda(carta[i], i));
        }
        
        addBotonVolver(lblsTienda);

    }

    public void mostrarVida(int vidaActual, JPanel panel) {
        String vidaString = String.valueOf(vidaActual) + "/" + String.valueOf(partida.getVidaMax());
        int tamañoX = (vidaString.length()+1) * 16;
        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(vidaString, "v");
        vidaLbl = new JLabel();
        
        vidaLbl.setSize(new Dimension(tamañoX*2, 32));
        vidaLbl.setLocation(304, 550);
        
        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX*2, 32);
        vidaLbl.setIcon(precioIconRedime);
        
        panel.add(vidaLbl);
        panel.setComponentZOrder(vidaLbl, 0);
        
    }

    public void mostrarDinero(int dinero, JPanel panel) {
        String dineroString = String.valueOf(dinero);
        int tamañoX = (dineroString.length()+1) * 16;
        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(dineroString, "d");
        dineroActLbl = new JLabel();
        
        dineroActLbl.setSize(new Dimension(tamañoX*2, 32));
        dineroActLbl.setLocation(5, 5);
        
        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX*2, 32);
        dineroActLbl.setIcon(precioIconRedime);
        
        panel.add(dineroActLbl);
        panel.setComponentZOrder(dineroActLbl, 0);
        
    }

    private void addBotonVolver(ArrayList<JLabel> cartaDisp) {
        Iterator<JLabel> it = cartaDisp.iterator();
        JButton atrasButton = new JButton();
        String atrasDirec = SonidoEImagen.Imagen.getImageLink("Atras");
        atrasButton.setBounds(304, 500, 192, 56);
        atrasButton.setBackground(new Color(0,0,0,0));
        
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
                eventoPanel.remove(atrasButton);
                eventoPanel.repaint();
                interfaz.irAMapa(eventoPanel);
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
                if(partida.comprarEnTienda(precio)){
                partida.addCarta(carta);
                eventoPanel.remove(Precio);
                eventoPanel.remove(cartaLbl);
                eventoPanel.repaint();
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
        int tamañoX = (precioString.length()+1) * 16;
        int posicionX = (posicion + 75) - (tamañoX);
        ImageIcon precioIcon;
        precioIcon = StringToIcons.getIconRepresentation(precioString, "d");
        JLabel dineroLbl = new JLabel();
        dineroLbl.setSize(new Dimension(tamañoX*2, 32));
        dineroLbl.setLocation(posicionX, 400);
        
        ImageIcon precioIconRedime = Imagen.redimensionarimagen(precioIcon, tamañoX*2, 32);
        dineroLbl.setIcon(precioIconRedime);
        
        eventoPanel.add(dineroLbl);
        eventoPanel.setComponentZOrder(dineroLbl, 0);
        
        eventoPanel.repaint();
        return dineroLbl;
                    
    }

    private void irAMapa(JPanel panelAnt) {
        panelAnt.setVisible(false);
        mapaPanel.setVisible(true);
    }

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
        eventoPanel.setVisible(false);
        Iniciar.setVisible(true);
        Iniciar.setBackground(new Color(0, 0, 0, 0));
        Iniciar.repaint();
    }

    private void colocarImagenes() {
        //Strings de dirección
        String menuDirec = SonidoEImagen.Imagen.getImageLink("Menu");
        String evtDirec = SonidoEImagen.Imagen.getImageLink("Evento");
        String batallaDirec = SonidoEImagen.Imagen.getImageLink("Batalla");
        String mapaDirec = SonidoEImagen.Imagen.getImageLink("Mapa");
        String inicDirec = SonidoEImagen.Imagen.getImageLink("Inicio");

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

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        Iniciar = new javax.swing.JButton();
        menuLabel = new javax.swing.JLabel();
        mapaPanel = new javax.swing.JPanel();
        mapaLabel = new javax.swing.JLabel();
        eventoPanel = new javax.swing.JPanel();
        eventoLabel = new javax.swing.JLabel();
        batallaPanel = new javax.swing.JPanel();
        batallaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(816, 639));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuPanel.setBackground(new java.awt.Color(60, 3, 65));
        menuPanel.setPreferredSize(new java.awt.Dimension(800, 600));

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
                .addComponent(Iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(Iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mapaPanel.setBackground(new java.awt.Color(60, 63, 5));
        mapaPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mapaPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        mapaLabel.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout mapaPanelLayout = new javax.swing.GroupLayout(mapaPanel);
        mapaPanel.setLayout(mapaPanelLayout);
        mapaPanelLayout.setHorizontalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapaPanelLayout.createSequentialGroup()
                .addComponent(mapaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mapaPanelLayout.setVerticalGroup(
            mapaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarActionPerformed
        // TODO add your handling code here:
        menuPanel.setVisible(false);
        mapaPanel.setVisible(true);
        partida.empezar(mapaPanel);

    }//GEN-LAST:event_IniciarActionPerformed

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
    private javax.swing.JButton Iniciar;
    private javax.swing.JLabel batallaLabel;
    private javax.swing.JPanel batallaPanel;
    private javax.swing.JLabel eventoLabel;
    private javax.swing.JPanel eventoPanel;
    private javax.swing.JLabel mapaLabel;
    private javax.swing.JPanel mapaPanel;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables

    public void refrescar(int dinero, int vidaAct) {
        String dineroString = String.valueOf(dinero);
        int tamañoXDinero = (dineroString.length()+1) * 16;
        ImageIcon DineroIcon;
        DineroIcon = StringToIcons.getIconRepresentation(dineroString, "d");
        this.remove(dineroActLbl);
        dineroActLbl = new JLabel();
        
        dineroActLbl.setSize(new Dimension(tamañoXDinero*2, 32));
        
        ImageIcon DineroIconRedime = Imagen.redimensionarimagen(DineroIcon, tamañoXDinero*2, 32);
        dineroActLbl.setIcon(DineroIconRedime);
        
        String vidaString = String.valueOf(vidaAct) + "/" + String.valueOf(partida.getVidaMax());
        int tamañoXVida = (vidaString.length()+1) * 16;
        ImageIcon VidaIcon;
        VidaIcon = StringToIcons.getIconRepresentation(vidaString, "v");
        this.remove(vidaLbl);
        vidaLbl = new JLabel();
        
        vidaLbl.setSize(new Dimension(tamañoXVida*2, 32));
        vidaLbl.setLocation(304, 550);
        
        ImageIcon VidaIconRedime = Imagen.redimensionarimagen(VidaIcon, tamañoXVida*2, 32);
        vidaLbl.setIcon(VidaIconRedime);
        
        this.repaint();
    }

    public void batalla(Enemigos Enemigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
