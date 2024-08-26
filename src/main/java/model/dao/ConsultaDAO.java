/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import com.mycompany.agendamedica.Consulta;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            stmt = con.prepareStatement("INSERT INTO consulta (id_medico, id_paciente, data_consulta, horario, descricao, nota, status, prioridade)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setInt(1,c.getIdMedico());
            stmt.setInt(2, c.getIdPaciente());
            stmt.setString(3, c.getData());
            stmt.setString(4, c.getHorario());
            stmt.setString(5, c.getDescricao());
            stmt.setDouble(6, c.getNota());
            stmt.setString(7, c.getStatus());
            stmt.setInt(8,c.getPrioridade());

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
            stmt = con.prepareStatement("UPDATE consulta SET id_medico = ?, id_paciente = ?, data_consulta = ?, horario = ?, descricao = ?, nota = ?, status = ?, prioridade = ? WHERE id_consulta = ?");
            stmt.setInt(1,c.getIdMedico());
            stmt.setInt(2, c.getIdPaciente());
            stmt.setString(3, c.getData());
            stmt.setString(4, c.getHorario());
            stmt.setString(5, c.getDescricao());
            stmt.setDouble(6, c.getNota());
            stmt.setString(7, c.getStatus());
            stmt.setInt(8,c.getPrioridade());
            stmt.setInt(9,c.getId());

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
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_medico = ? AND status=\"espera\"")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consulta.setPrioridade(rs.getInt("prioridade"));
                        consultas.add(consulta);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return consultas;
    }
    public ArrayList<Consulta> getConsultaListaEsperaPaciente(int id){
        ArrayList<Consulta> consultas = new ArrayList<>();
            try (Connection conn = ConnectionFactory.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id_paciente = ? AND status=\"espera\"")) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                        Consulta consulta = new Consulta(rs.getInt("id_medico"),rs.getInt("id_paciente"),rs.getString("data_consulta"), rs.getString("horario"), rs.getDouble("nota"), rs.getString("status"));
                        consulta.setId(rs.getInt("id_consulta"));
                        consulta.setDescricao(rs.getString("descricao"));
                        consulta.setPrioridade(rs.getInt("prioridade"));
                        consultas.add(consulta);
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
    public ArrayList<ArrayList<String>> returnConsultasJoinPaciente(int id_paciente, String pesquisa) throws SQLException{
        ArrayList<ArrayList<String>> ConsultasPaciente = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT M.nome,P.nome,C.data_consulta,C.horario,C.descricao FROM consulta as C JOIN paciente as P on P.id_paciente = C.id_paciente JOIN medico as M on M.id_medico=C.id_medico WHERE P.id_paciente=? and (C.status=\"avaliada\" or C.status=\"realizada\") and M.nome LIKE ?");

        stmt.setInt(1, id_paciente);
        stmt.setString(2, "%"+pesquisa+"%" );
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            ArrayList<String> colunas = new ArrayList<>();
            colunas.add(rs.getString("M.nome"));
            colunas.add(rs.getString("P.nome"));
            colunas.add(rs.getString("C.data_consulta"));
            colunas.add(rs.getString("C.horario"));
            colunas.add(rs.getString("C.descricao"));
            ConsultasPaciente.add(colunas);
        }
        return ConsultasPaciente;
    }
    public ArrayList<ArrayList<String>> returnConsultasJoinMedico(int id_medico, String pesquisa) throws SQLException{
        ArrayList<ArrayList<String>> ConsultasPaciente = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT M.nome,P.nome,C.data_consulta,C.horario,C.descricao FROM consulta as C JOIN paciente as P on P.id_paciente = C.id_paciente JOIN medico as M on M.id_medico=C.id_medico WHERE M.id_medico=? and (C.status=\"avaliada\" or C.status=\"realizada\") and P.nome LIKE ? ");

        stmt.setInt(1, id_medico);
        stmt.setString(2, "%"+pesquisa+"%" );
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            ArrayList<String> colunas = new ArrayList<>();
            colunas.add(rs.getString("M.nome"));
            colunas.add(rs.getString("P.nome"));
            colunas.add(rs.getString("C.data_consulta"));
            colunas.add(rs.getString("C.horario"));
            colunas.add(rs.getString("C.descricao"));
            ConsultasPaciente.add(colunas);
        }
        return ConsultasPaciente;
    }
}
