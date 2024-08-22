/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.agendamedica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.ConsultaDAO;
import model.dao.MedicoDAO;

/**
 *
 * @author super
 */
public class AvaliarConsulta extends javax.swing.JFrame {

    /**
     * Creates new form AvaliarConsulta
     */
    public AvaliarConsulta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtConsultas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        dataRelatorioTxt = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE AVALIAÇÃO DE CONSULTAS");
        setMinimumSize(new java.awt.Dimension(868, 630));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jtConsultas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        jtConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medico", "Data", "Hora"
            }
        ));
        jtConsultas.getTableHeader().setResizingAllowed(false);
        jtConsultas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtConsultas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 170, 738, 380);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("SISTEMA DE AVALIÇÃO DE CONSULTAS REALIZADAS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 10, 376, 22);

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(190, 560, 72, 23);

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(560, 560, 72, 23);

        jLabel2.setText("Digite a data da consulta que deseja avaliar: (formato dd-mm-aaaa)");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 60, 450, 16);
        getContentPane().add(dataRelatorioTxt);
        dataRelatorioTxt.setBounds(630, 60, 116, 22);

        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(370, 560, 90, 23);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/blackkk.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 940, 750);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PosLogin frame = new PosLogin();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = jtConsultas.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");
        }
        else{
        MedicoDAO daom = new MedicoDAO();
        DefaultTableModel dtmConsultas = (DefaultTableModel) jtConsultas.getModel();
        ConsultaDAO daoc = new ConsultaDAO();
        Paciente paciente = Cadastro.getPaciente();
        ArrayList<Consulta> consultasParaAvaliar = paciente.getAvaliarConsultas();
        for(Consulta consulta : consultasParaAvaliar){
            try {
                String nomeMedico = (String) dtmConsultas.getValueAt(row, 0);
                String data  = (String) dtmConsultas.getValueAt(row,1);
                Date dataObj;
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd");
                dataObj = formatoOriginal.parse(data);
                String dataFormatada = formatoDesejado.format(dataObj);
                String horario = (String) dtmConsultas.getValueAt(row, 2);
                if(daom.returnMedico(consulta.getIdMedico()).getNome().equals(nomeMedico)&&consulta.getHorario().equals(horario)&&dataFormatada.equals(consulta.getData())){
                    String notaS = JOptionPane.showInputDialog("Avaliação do atendimento (de 0.0 a 5.0)");
                    double nota = Double.parseDouble(notaS);
                    consulta.setNota(nota);
                    consulta.setStatus("avaliada");
                    daoc.update(consulta);
                }
            } catch (ParseException ex) {
                Logger.getLogger(AvaliarConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        paciente.resetAvaliarConsultas();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) jtConsultas.getModel();
        MedicoDAO daom = new MedicoDAO();
        int index5 = table.getRowCount();
        for(int x=0;x<index5;x++){
            table.removeRow(0);
        }
        if(dataRelatorioTxt.getText().split("(?!^)").length==10){
        try {
            Paciente paciente = Cadastro.getPaciente();
            ArrayList<Consulta> consultasParaAvaliar = paciente.getAvaliarConsultas();
            for(Consulta consulta : consultasParaAvaliar){
                Date dataObj;
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd");
                dataObj = formatoOriginal.parse(dataRelatorioTxt.getText());
                String dataFormatada = formatoDesejado.format(dataObj);
                if(consulta.getData().equals(dataFormatada)){
                DefaultTableModel dtmConsultas = (DefaultTableModel) jtConsultas.getModel();
                Date dataConsulta;
                SimpleDateFormat formatoConsultaOriginal = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoConsultaDesejado = new SimpleDateFormat("dd-MM-yyyy");
                dataConsulta = formatoConsultaOriginal.parse(consulta.getData());
                String dataConsultaFormatada = formatoConsultaDesejado.format(dataConsulta);
                Object[] dados = {daom.returnMedico(consulta.getIdMedico()).getNome(),dataConsultaFormatada,consulta.getHorario()};
                dtmConsultas.addRow(dados);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AvaliarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Olá, datas inseridas inválidas formato (dd-mm-yyyy). Nao esqueca de colocar o (-) ");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            Paciente paciente = Cadastro.getPaciente();
            MedicoDAO daom = new MedicoDAO();
            ArrayList<Consulta> consultasParaAvaliar = paciente.getAvaliarConsultas();
            for(Consulta consulta : consultasParaAvaliar){
                DefaultTableModel dtmConsultas = (DefaultTableModel) jtConsultas.getModel();
                Date dataObj;
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd-MM-yyyy");
                dataObj = formatoOriginal.parse(consulta.getData());
                String dataFormatada = formatoDesejado.format(dataObj);
                Object[] dados = {daom.returnMedico(consulta.getIdMedico()).getNome(),dataFormatada,consulta.getHorario()};
                dtmConsultas.addRow(dados);
            }
            paciente.resetConsultas();
        } catch (ParseException ex) {
            Logger.getLogger(AvaliarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(AvaliarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AvaliarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AvaliarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AvaliarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AvaliarConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dataRelatorioTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtConsultas;
    // End of variables declaration//GEN-END:variables
}
