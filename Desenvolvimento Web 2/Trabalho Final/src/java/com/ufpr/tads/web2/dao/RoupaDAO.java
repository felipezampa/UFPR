package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Roupa;
import com.ufpr.tads.web2.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RoupaDAO {

    private static final String BUSCAR = "SELECT id_roupa, nome_roupa, preco_roupa, prazo_roupa FROM roupa WHERE id_roupa=?";
    private static final String BUSCAR_TODOS = "SELECT id_roupa, nome_roupa, preco_roupa, prazo_roupa FROM roupa";
    private static final String INSERIR = "INSERT INTO roupa(nome_roupa, preco_roupa, prazo_roupa) VALUES(?, ?, ?)";
    private static final String ALTERAR = "UPDATE roupa SET nome_roupa=?, preco_roupa=?, prazo_roupa=? WHERE id_roupa=?";
    private static final String EXCLUIR = "DELETE FROM roupa WHERE id_roupa= ?";

    private Connection connection = null;

    public RoupaDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar UsuarioDAO.");
        }
        this.connection = con;
    }

    public Roupa buscar(int id) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR)) {
            Roupa roupa = new Roupa();
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                roupa.setId(rs.getInt("id_roupa"));
                roupa.setNome(rs.getString("nome_roupa"));
                roupa.setPreco(rs.getDouble("preco_roupa"));
                roupa.setPrazo(rs.getTime("prazo_roupa"));
            }

            return roupa;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar roupa " + id + " | " + e);
        }
    }

    public List<Roupa> buscarTodos() throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(BUSCAR_TODOS)) {
            List<Roupa> roupas = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Roupa roupa = new Roupa();
                roupa.setId(rs.getInt("id_roupa"));
                roupa.setNome(rs.getString("nome_roupa"));
                roupa.setPreco(rs.getDouble("preco_roupa"));
                roupa.setPrazo(rs.getTime("prazo_roupa"));
                roupas.add(roupa);
            }

            return roupas;
        } catch (SQLException e) {
            throw new DAOException("Erro no DAO ao buscar todos usuarios " + e);
        }
    }

    public void inserir(Roupa roupa) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(INSERIR)) {
            st.setString(1, roupa.getNome());
            st.setDouble(2, roupa.getPreco());
            st.setTime(3, roupa.getPrazo());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo roupa: " + roupa.toString(), e);
        }
    }

    public void alterar(Roupa roupa) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(ALTERAR)) {
            st.setInt(4, roupa.getId());

            st.setString(1, roupa.getNome());
            st.setDouble(2, roupa.getPreco());
            st.setTime(3, roupa.getPrazo());

            st.executeUpdate();
        } catch (SQLException  e) {
            throw new DAOException("Erro alterando roupa: " + roupa.toString(), e);
        }
    }

    public void excluir(int id) throws DAOException {
        try ( PreparedStatement st = connection.prepareStatement(EXCLUIR)) {
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro excluindo roupa " + id + " | " + e);
        }
    }
    
    public String getPrazoPedido(){
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	return dateFormat.format(date);
    }
}
