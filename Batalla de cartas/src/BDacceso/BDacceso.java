/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDacceso;

import Model.Accion;
import Model.Cartas;
import Model.Enemigos;
import Model.ExceptionLogger;
import Model.puntuaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author a22victorlr
 */
public class BDacceso {

    private Connection conexion;
    private String url = "jdbc:mysql://localhost/batalla";
    private String usuario = "root";
    private String contrasena = "root";

    public BDacceso() {
        // crea la conexión a la base de datos
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
                ExceptionLogger.logException(e);
        }
    }

    // método para obtener las cartas del jugador
    public ArrayList<Cartas> obtenerCartasJugador(int idJugador) {
        ArrayList<Cartas> cartasJugador = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM carta INNER JOIN mazo ON mazo.idcarta = carta.idCarta WHERE mazo.idPersonaje = ?");
            ps.setInt(1, idJugador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCarta = rs.getInt("idCarta");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                int repeticiones = rs.getInt("repeticiones");
                String imagen = rs.getString("imagen");
                int Efecto = 0;
            
            try {
                PreparedStatement pa = conexion.prepareStatement("SELECT * FROM cata_tiene_efecto WHERE idCarta = ?");
                pa.setInt(1, idCarta);
                ResultSet ra = pa.executeQuery();
               
                if (ra.next()) {
                    Efecto = rs.getInt("idEfecto");
                }
            } catch (SQLException ex) {
                ExceptionLogger.logException(ex);
            }
            
                Cartas carta = new Cartas(idCarta, ataque, defensa, repeticiones, imagen, Efecto);
                cartasJugador.add(carta);
            }
        } catch (SQLException e) {
                ExceptionLogger.logException(e);
        }
        return cartasJugador;
    }

    // método para obtener la vida del jugador
    public int obtenerVidaJugador(int idJugador) {
        int vida = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT vida FROM personaje WHERE idPersonaje = ?");
            ps.setInt(1, idJugador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vida = rs.getInt("vida");
            }
        } catch (SQLException e) {
                ExceptionLogger.logException(e);
        }
        return vida;
    }

    // método para obtener la energía del jugador
    public int obtenerEnergiaJugador(int idJugador) {
        int energia = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT energia FROM personaje WHERE idPersonaje = ?");
            ps.setInt(1, idJugador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                energia = rs.getInt("energia");
            }
        } catch (SQLException e) {
                ExceptionLogger.logException(e);
        }
        return energia;
    }

    // método para obtener la regeneración de energía del jugador
    public int obtenerRegeneracionEnergiaJugador(int idJugador) {
        int regeneracionEnergia = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT regeneracionEnergia FROM personaje WHERE idPersonaje = ?");
            ps.setInt(1, idJugador);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                regeneracionEnergia = rs.getInt("regeneracionEnergia");
            }
        } catch (SQLException e) {
            
                ExceptionLogger.logException(e);
        }
        return regeneracionEnergia;
    }

    public Cartas generarCartaAleatoria(int dificultad) {
        Cartas cartaCreada = null;
        int id = (int) (Math.random() * 5 + 1);

        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM carta WHERE idCarta = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idCarta = rs.getInt("idCarta");
            int ataque = rs.getInt("ataque");
            int defensa = rs.getInt("defensa");
            int repeticiones = rs.getInt("repeticiones");
            String imagen = rs.getString("imagen");
            int Efecto = 0;
            
            try {
                PreparedStatement pa = conexion.prepareStatement("SELECT * FROM cata_tiene_efecto WHERE idCarta = ?");
                pa.setInt(1, idCarta);
                ResultSet ra = pa.executeQuery();
               
                if (ra.next()) {
                    Efecto = rs.getInt("idEfecto");
                }
            } catch (SQLException ex) {
                
                ExceptionLogger.logException(ex);
            }
            
            cartaCreada = new Cartas(idCarta, ataque, defensa, repeticiones, imagen, Efecto);

        } catch (SQLException e) {
            
                ExceptionLogger.logException(e);
        }
        return cartaCreada;
    }

    public Enemigos generarEnemigoAleatoria(int dificultad) {
        Enemigos enemigo = null;
        ArrayList<Accion> accionesEnemigo = new ArrayList<>();
        int idEnemigo = 1;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM accion INNER JOIN comportamiento ON comportamiento.idaccion = accion.idCarta WHERE comportamiento.idPersonaje = ?");
            ps.setInt(1, idEnemigo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCarta = rs.getInt("idCarta");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                int repeticiones = rs.getInt("repeticiones");
                Accion accion = new Accion(idCarta, ataque, defensa, repeticiones);
                accionesEnemigo.add(accion);
            }
        } catch (SQLException e) {
                ExceptionLogger.logException(e);
        }

            int vida = 0;
            String nombre = null;
            try {
                PreparedStatement ps = conexion.prepareStatement("SELECT * FROM personaje WHERE idPersonaje = ?");
                ps.setInt(1, idEnemigo);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    vida = rs.getInt("vida");
                    nombre = rs.getString("imagen");
                }
            } catch (SQLException e) {
                ExceptionLogger.logException(e);
            }

            enemigo = new Enemigos(vida, accionesEnemigo, 0, 0, nombre);

        return enemigo;
    }

    public Enemigos generarJefe(int id, int dificultad) {
        Enemigos enemigo = null;
        ArrayList<Accion> accionesEnemigo = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM accion INNER JOIN comportamiento ON comportamiento.idaccion = accion.idCarta WHERE comportamiento.idPersonaje = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCarta = rs.getInt("idCarta");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                int repeticiones = rs.getInt("repeticiones");
                Accion accion = new Accion(idCarta, ataque, defensa, repeticiones);
                accionesEnemigo.add(accion);
            }
        } catch (SQLException e) {
            
                ExceptionLogger.logException(e);
        }

            int vida = 0;
            String nombre = null;
            try {
                PreparedStatement ps = conexion.prepareStatement("SELECT * FROM personaje WHERE idPersonaje = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    vida = rs.getInt("vida");
                    nombre = rs.getString("imagen");
                }
            } catch (SQLException e) {
                ExceptionLogger.logException(e);
            }

            enemigo = new Enemigos(vida, accionesEnemigo, 0, 0, nombre);

        return enemigo;
    
    }

    public void addPuntuacion(int puntuacion, String nombre) {
        try {
            PreparedStatement stmt = conexion.prepareStatement("INSERT INTO puntuacion(nombre, puntuacion) VALUES (?, ?)");
            stmt.setString(1, nombre);
            stmt.setString(2, Integer.toString(puntuacion));

            stmt.executeUpdate();
            } catch (SQLException ex) {
                
                ExceptionLogger.logException(ex);
            }
    }
    
    public puntuaciones sacarPuntuacion(int id) {
        String nombre;
        String puntuacion;
        try {
            PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM puntuaciones WHERE idJugadores = ?");
            stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                nombre = rs.getString("nombre");
                puntuacion = rs.getString("puntuacion");
                puntuaciones puntuacionJugador = new puntuaciones(nombre, puntuacion);
                return puntuacionJugador;
                }
            } catch (SQLException e) {
                ExceptionLogger.logException(e);
            }
        return null;
    }
}
