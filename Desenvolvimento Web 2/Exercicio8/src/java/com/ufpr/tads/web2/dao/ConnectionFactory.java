package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/web2";
    private static final String LOGIN = "postgres";
    private static final String SENHA = "postgres";
    private Connection con = null;

    public Connection getConnection() throws SQLException {
        if (con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver do banco não encontrado: "
                        + DRIVER, e);
            } catch (SQLException e) {
                throw new SQLException("Erro conectando ao BD: " + URL + "/"
                        + LOGIN + "/" + SENHA, e);
            }
        }
        return con;

    }

    @Override
    public void close() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                System.out.println("Erro fechando a conexão. IGNORADO");
            }
        }
    }
}
