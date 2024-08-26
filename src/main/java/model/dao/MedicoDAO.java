/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import com.mycompany.agendamedica.Consulta;
import com.mycompany.agendamedica.Medico;
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
public class MedicoDAO {
    public void create(Medico m, String senhaADM){
        
        if(ConnectionFactory.getSenhaADM().equals(senhaADM)){
        
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;

            try {
                stmt = con.prepareStatement("INSERT INTO medico (nome, especialidade, senha, crm)VALUES(?,?,?,?)");
                stmt.setString(1,m.getNome());
                stmt.setString(2, m.getEspecialidade());
                stmt.setString(3, m.getSenha());
                stmt.setString(4,m.getCRM());

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null,"Salvo com sucesso");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao salvar: Cheque seus dados, como CRM " + "Erro: " + ex);
                }finally{
                    ConnectionFactory.closeConnection(con, stmt);
                }
        }else{
            JOptionPane.showMessageDialog(null,"Senha admin incorreta");
        }
    }
    
    public void update(Medico m, String senhaADM, String CRM){
        if(ConnectionFactory.getSenhaADM().equals(senhaADM)){
        
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;

            try {
                stmt = con.prepareStatement("UPDATE medico SET nome = ? , especialidade = ? , senha = ?, crm = ? WHERE crm = ?");
                stmt.setString(1,m.getNome());
                stmt.setString(2, m.getEspecialidade());
                stmt.setString(3, m.getSenha());
                stmt.setString(4, CRM);
                stmt.setString(5,m.getCRM());

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar: " + ex);
                }finally{
                    ConnectionFactory.closeConnection(con, stmt);
                }
        }else{
            JOptionPane.showMessageDialog(null,"Senha admin incorreta");
        }
    }
    //funcao sobrecarga para inativar o medico
    public void update(Medico m, String senhaADM, int ativo){
        if(ConnectionFactory.getSenhaADM().equals(senhaADM)){
        
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            
            ConsultaDAO daoc = new ConsultaDAO();
            List<Consulta> consultas = daoc.getIdMedico(m.getId());
            for(Consulta consulta : consultas){
                if(consulta.getIdMedico() == m.getId()){
                    if(consulta.getStatus().equals("marcada")==true || consulta.getStatus().equals("espera")){
                        daoc.delete(consulta);
                    }
                }
            }

            try {
                stmt = con.prepareStatement("UPDATE medico SET nome = ? , especialidade = ? , senha = ?, crm = ?, ativo = ? WHERE id_medico = ?");
                stmt.setString(1,m.getNome());
                stmt.setString(2, m.getEspecialidade());
                stmt.setString(3, m.getSenha());
                stmt.setString(4, m.getCRM());
                stmt.setInt(5,ativo);
                stmt.setInt(6,m.getId());

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar: " + ex);
                }finally{
                    ConnectionFactory.closeConnection(con, stmt);
                }
        }else{
            JOptionPane.showMessageDialog(null,"Senha admin incorreta");
        }
    }
    //apenas para mostrar como deletar algo
    public void delete(Medico m){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ConsultaDAO daoc = new ConsultaDAO();
        List<Consulta> consultas = daoc.read();
        for(Consulta consulta : consultas){
            if(consulta.getIdMedico() == m.getId()){
                if(consulta.getStatus().equals("marcada")==true || consulta.getStatus().equals("espera")){
                    daoc.delete(consulta);
                }
                //null significa que a consulta vai ficar sem um medico registrado, posso exluir o medico sem a consulta
                try{
                    stmt = con.prepareStatement("UPDATE consulta SET id_medico = NULL");
                    stmt.executeUpdate();
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,"Erro ao atualizar a consulta sem medico: " + ex);
                }
            }
        }
        
        try {
            stmt = con.prepareStatement("DELETE FROM medico WHERE id_medico = ?");
            stmt.setInt(1,m.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Erro ao excluir: " + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
    }
    public void inativar(Medico m, String senhaADM){
        m.setAtivo(0);
        this.update(m, senhaADM, m.getAtivo());
    }
     public Medico getByCRM(String CRM){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE crm = ?");
            stmt.setString(1, CRM);
            rs = stmt.executeQuery();
            while(rs.next()){
                medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
           }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return medico;
    }   
    public ArrayList<Medico> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medico");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Medico medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                medicos.add(medico);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medicos;
    }
    public ArrayList<Medico> readAtivos(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medico");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if(rs.getBoolean("ativo")){
                Medico medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                medicos.add(medico);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medicos;
    }
    
    public ArrayList<Medico> readForNome(String nome){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Medico medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                medicos.add(medico);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medicos;
    }
    public ArrayList<Medico> read(String search){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE (nome LIKE ? OR especialidade LIKE ? )");
            stmt.setString(1, search+"%");
            stmt.setString(2, search+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if(rs.getBoolean("ativo")){
                    Medico medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                    medico.setId(rs.getInt("id_medico"));
                    medicos.add(medico);
                }
 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medicos;
    }
    public ArrayList<Medico> readForSenha(String senha){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Medico> medicos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE senha LIKE ?");
            stmt.setString(3, senha);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Medico medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                medicos.add(medico);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medicos;
    }
    
    public boolean checkLogin(String crm, String senha){
        boolean verificador = false;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE crm = ? and senha = ?");
            stmt.setString(1, crm);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                if(rs.getBoolean("ativo")){
                    verificador = true;
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return verificador;
    }
    
    public Medico returnLogin(String crm, String senha){
        Medico medico = null;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE crm = ? and senha = ?");
            stmt.setString(1, crm);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                
                medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return medico;
    }
    public Medico returnMedico(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Medico medico = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM medico WHERE id_medico=?");
            stmt.setString(1, String.valueOf(id));
            rs = stmt.executeQuery();
            
            if(rs.next()){
                
                medico = new Medico(rs.getString("nome"),rs.getString("especialidade"), rs.getString("senha"), rs.getString("crm"));
                medico.setId(rs.getInt("id_medico"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        
        return medico;
    }
}
