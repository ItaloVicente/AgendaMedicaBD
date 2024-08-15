/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import com.mycompany.agendamedica.Consulta;
import com.mycompany.agendamedica.Paciente;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author super
 */
public class ConsultaDAO {
    
    public void create(Consulta c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO consulta (id_medico, id_paciente, data_consulta, horario, descricao, nota, status)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1,c.getIdMedico());
            stmt.setInt(2, c.getIdPaciente());
            stmt.setString(3, c.getData());
            stmt.setString(4, c.getHorario());
            stmt.setString(5, c.getDescricao());
            stmt.setDouble(6, c.getNota());
            stmt.setString(7, c.getStatus());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao salvar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    
    public void update(Consulta c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE consulta SET id_medico = ?, id_paciente = ?, data_consulta = ?, horario = ?, descricao = ?, nota = ?, status = ? WHERE id_consulta = ?");
            stmt.setInt(1,c.getIdMedico());
            stmt.setInt(2, c.getIdPaciente());
            stmt.setString(3, c.getData());
            stmt.setString(4, c.getHorario());
            stmt.setString(5, c.getDescricao());
            stmt.setDouble(6, c.getNota());
            stmt.setString(7, c.getStatus());
            stmt.setInt(8,c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao atualizar: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    
        public void delete(Consulta c){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM consulta WHERE id_consulta = ?");
            stmt.setInt(1,c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao excluir: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    
    public List<Consulta> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return consultas;
    }
    
    public ArrayList<Consulta> getIdMedico(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_medico = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que a classe Consulta tenha um construtor que aceita todos os campos da tabela
                Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    public ArrayList<Consulta> getIdPaciente(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_paciente = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que a classe Consulta tenha um construtor que aceita todos os campos da tabela
                Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    
    public Consulta getConsultaId(int id){
        Consulta consulta = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_consulta = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que a classe Consulta tenha um construtor que aceita todos os campos da tabela
                consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consulta;
    }
    public ArrayList<Consulta> getTodasConsultaDataHora(String data, String hora){
        ArrayList<Consulta> consultas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE data_consulta = ? AND horario = ?")) {
                stmt.setString(1, data);
                stmt.setString(2, hora);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    if(rs.getString("status").equals("marcada")||rs.getString("status").equals("espera")){
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consultas.add(consulta);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return consultas;
    }
    
    public ArrayList<Consulta> getConsultaListaEspera(String data, String hora){
        ArrayList<Consulta> consultas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE data_consulta = ? AND horario = ?")) {
                stmt.setString(1, data);
                stmt.setString(2, hora);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    if(rs.getString("status").equals("espera")){
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consultas.add(consulta);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return consultas;
    }
    public ArrayList<Consulta> getConsultaListaEsperaMedico(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_medico = ?")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    if(rs.getString("status").equals("espera")){
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consultas.add(consulta);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return consultas;
    }
    public ArrayList<Consulta> getConsultaListaEsperaPaciente(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_paciente = ?")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    if(rs.getString("status").equals("espera")){
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consultas.add(consulta);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return consultas;
    }
    public ArrayList<Consulta> getConsultasParaAvaliar(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_paciente = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que a classe Consulta tenha um construtor que aceita todos os campos da tabela
                if(rs.getString("status").equals("realizada")){
                Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }
    
    public ArrayList<Consulta> getConsultasAvaliadas(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_medico = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Supondo que a classe Consulta tenha um construtor que aceita todos os campos da tabela
                if(rs.getString("status").equals("avaliada")){
                Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                consulta.setId(rs.getInt("id_consulta"));
                consulta.setDescricao(rs.getString("descricao"));
                consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }
}
