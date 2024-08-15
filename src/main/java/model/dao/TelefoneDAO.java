/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;


import com.mycompany.agendamedica.Telefone;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author super
 */
public class TelefoneDAO {
    public void create(Telefone t){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO telefone (id_paciente,telefone)VALUES(?,?)");
            stmt.setInt(1,t.getId_paciente());
            stmt.setString(2, t.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao salvar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    public ArrayList<Telefone> read(int id_paciente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Telefone> telefones = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM telefone WHERE id_paciente = ?");
            stmt.setInt(1, id_paciente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Telefone telefone = new Telefone(rs.getString("telefone"), rs.getInt("id_paciente"));
                telefone.setId_telefone(rs.getInt("id_telefone"));
                telefones.add(telefone);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return telefones;
    }
}
