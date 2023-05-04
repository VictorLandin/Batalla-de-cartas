/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDacceso;

import Model.Cartas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        }
    }

    // método para obtener las cartas del jugador
    public ArrayList<Cartas> obtenerCartasJugador(int idJugador) {
        ArrayList<Cartas> cartasJugador = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM carta INNER JOIN jugador_carta ON jugador_carta.carta_idcarta = carta.idCarta WHERE jugador_carta.jugador_idjugador = ?");
            ps.setInt(1, idJugador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCarta = rs.getInt("idCarta");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                int repeticiones = rs.getInt("repeticiones");
                String imagen = rs.getString("imagen");
                String objetivoString = rs.getString("objetivo");
                Cartas carta = new Cartas(idCarta, ataque, defensa, repeticiones, objetivoString, imagen);
                cartasJugador.add(carta);
            }
        } catch (SQLException e) {
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
        }
        return regeneracionEnergia;
    }

    }