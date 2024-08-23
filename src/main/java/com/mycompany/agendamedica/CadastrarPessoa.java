/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.agendamedica;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.dao.MedicoDAO;
import model.dao.PacienteDAO;
import model.dao.TelefoneDAO;

/**
 *
 * @author super
 */
public class CadastrarPessoa extends javax.swing.JFrame {

    /**
     * Creates new form CadastrarPessoa
     */
    public CadastrarPessoa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxPaciente = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelCRM = new javax.swing.JLabel();
        cbxMedico = new javax.swing.JCheckBox();
        lblNome = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblConfirmarSenha = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblEspecialidade = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        cpfTxt = new javax.swing.JTextField();
        nomeTxt = new javax.swing.JTextField();
        senhaTxt = new javax.swing.JTextField();
        confirmarTxt = new javax.swing.JTextField();
        especialidadeTxt = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        crmTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CADASTRO");
        setMinimumSize(new java.awt.Dimension(816, 581));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(250, 460, 80, 23);

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(430, 460, 72, 23);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("           JANELA DE CADASTRO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(290, 0, 303, 40);

        buttonGroup2.add(cbxPaciente);
        cbxPaciente.setSelected(true);
        cbxPaciente.setText("Paciente");
        cbxPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPacienteActionPerformed(evt);
            }
        });
        getContentPane().add(cbxPaciente);
        cbxPaciente.setBounds(380, 60, 85, 20);

        jLabel2.setText("Qual sua situacao?");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 60, 157, 16);

        jLabelCPF.setText("CPF:");
        getContentPane().add(jLabelCPF);
        jLabelCPF.setBounds(170, 370, 50, 16);

        jLabelCRM.setText("CRM:");
        getContentPane().add(jLabelCRM);
        jLabelCRM.setBounds(170, 330, 50, 16);

        buttonGroup2.add(cbxMedico);
        cbxMedico.setText("Medico");
        cbxMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMedicoActionPerformed(evt);
            }
        });
        getContentPane().add(cbxMedico);
        cbxMedico.setBounds(530, 60, 85, 20);

        lblNome.setText("Nome: ");
        getContentPane().add(lblNome);
        lblNome.setBounds(170, 130, 115, 16);

        lblData.setText("Data de nascimento:");
        getContentPane().add(lblData);
        lblData.setBounds(170, 280, 150, 16);

        lblSenha.setText("Senha:");
        getContentPane().add(lblSenha);
        lblSenha.setBounds(170, 180, 113, 16);

        lblConfirmarSenha.setText("Confirmar senha:");
        getContentPane().add(lblConfirmarSenha);
        lblConfirmarSenha.setBounds(170, 230, 120, 16);

        lblSexo.setText("Sexo:");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(170, 330, 89, 16);

        lblEspecialidade.setText("Especialidade:");
        getContentPane().add(lblEspecialidade);
        lblEspecialidade.setBounds(170, 280, 130, 16);

        jDateChooser1.setDateFormatString("dd/MM/yy");
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(340, 270, 160, 22);

        cpfTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfTxtActionPerformed(evt);
            }
        });
        getContentPane().add(cpfTxt);
        cpfTxt.setBounds(340, 360, 370, 30);
        getContentPane().add(nomeTxt);
        nomeTxt.setBounds(343, 122, 370, 30);
        getContentPane().add(senhaTxt);
        senhaTxt.setBounds(343, 172, 370, 30);
        getContentPane().add(confirmarTxt);
        confirmarTxt.setBounds(343, 222, 370, 30);

        especialidadeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                especialidadeTxtActionPerformed(evt);
            }
        });
        getContentPane().add(especialidadeTxt);
        especialidadeTxt.setBounds(343, 270, 370, 30);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Masculino");
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(330, 330, 77, 21);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Feminino");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(450, 330, 72, 21);
        getContentPane().add(crmTxt);
        crmTxt.setBounds(340, 320, 370, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/blackkk.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-4, -12, 870, 760);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Cadastro frame = new Cadastro();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbxPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPacienteActionPerformed
        

            lblData.setVisible(true);
            jDateChooser1.setVisible(true);
            lblSexo.setVisible(true);
            jRadioButton1.setVisible(true);
            jRadioButton2.setVisible(true);
            cpfTxt.setVisible(true);
            jLabelCPF.setVisible(true);
            lblEspecialidade.setVisible(false);
            especialidadeTxt.setVisible(false);
            crmTxt.setVisible(false);
            jLabelCRM.setVisible(false);
    }//GEN-LAST:event_cbxPacienteActionPerformed

    private void cbxMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMedicoActionPerformed
        

            lblEspecialidade.setVisible(true);
            especialidadeTxt.setVisible(true);
            crmTxt.setVisible(true);
            jLabelCRM.setVisible(true);
            lblData.setVisible(false);
            jDateChooser1.setVisible(false);
            lblSexo.setVisible(false);
            jRadioButton1.setVisible(false);
            jRadioButton2.setVisible(false);
            cpfTxt.setVisible(false);
            jLabelCPF.setVisible(false);
        
    }//GEN-LAST:event_cbxMedicoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        lblEspecialidade.setVisible(false);
        especialidadeTxt.setVisible(false);
        jLabelCRM.setVisible(false);
        crmTxt.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        PacienteDAO daop = new PacienteDAO();
        MedicoDAO daom = new MedicoDAO();
        TelefoneDAO daot = new TelefoneDAO();
        //Caso cadastro paciente
        if(cbxPaciente.isSelected()){
            String nome = nomeTxt.getText().toUpperCase();
            String senha = senhaTxt.getText();
            String confirmar = confirmarTxt.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
            String formattedDate = dateFormat.format(jDateChooser1.getDate());
            String sexo;
            if(jRadioButton1.isSelected())
                sexo = "M";
            else
                sexo = "F";
            String cpf = cpfTxt.getText();
            boolean verificador = false;
            if(daop.checkLogin(cpf, senha) == true){
                verificador=true;
                JOptionPane.showMessageDialog(null, "Usuário ja cadastrado");
            }
            if(cpf.length()!=11){
                verificador=true;
                JOptionPane.showMessageDialog(null,"CPF invalido!");
            }
            if(verificador==false&&senha.equals(confirmar)==true){
                try {
                    Paciente paciente = new Paciente(nome,formattedDate,sexo,senha,cpf);
                    daop.create(paciente);
                    Paciente pacienteCriado = daop.returnkLogin(cpf, senha);
                    while (true){
                        String telefone = JOptionPane.showInputDialog("Cadastre um telefone");
                        if(telefone!=null){
                            String[] vetortelefone = telefone.split("(?!^)");

                        if (vetortelefone.length == 11) {
                        Telefone t = new Telefone(telefone, pacienteCriado.getId());
                        daot.create(t);
                        break;
                        } else {
                        JOptionPane.showMessageDialog(null, "Olá, telefone inválido formato: 859NNNNNNNN");
                        }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Olá, telefone inválido formato: 859NNNNNNNN");
                        }

                    }
                    while (true) {
                        String telefone = JOptionPane.showInputDialog("Se quiser, cadastre outro telefone (Aperte Ok caso nao queira registrar mais telefones)");

                        if (telefone == null || telefone.isEmpty()) {
                            // Se o telefone for null (usuário clicou em "Cancelar") ou vazio (usuário clicou em "Ok" sem digitar nada)
                            break;
                        }

                        String[] vetortelefone = telefone.split("(?!^)");

                        if (vetortelefone.length == 11) {
                            Telefone t = new Telefone(telefone, pacienteCriado.getId());
                            daot.create(t);
                        } else {
                            JOptionPane.showMessageDialog(null, "Olá, telefone inválido formato: 859NNNNNNNN");
                        }
                    }
                } catch (HeadlessException ex) {
                    Logger.getLogger(CadastrarPessoa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(formattedDate.length()!=8){
                JOptionPane.showMessageDialog(null, "Olá, datas inseridas inválidas");
            }
            if(senha.equals(confirmar)==false){
                JOptionPane.showMessageDialog(null, "Olá, o Cadastro não deu certo! senhas nao batem.","ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Caso cadastro medico
        else{
            String nome = nomeTxt.getText().toUpperCase();
            String senha = senhaTxt.getText();
            String confirmar = confirmarTxt.getText();
            String especialidade = especialidadeTxt.getText().toUpperCase();
            String crm = crmTxt.getText();
            boolean verificador2=false;
            if(daom.checkLogin(crm, senha) == true){
                verificador2=true;
                JOptionPane.showMessageDialog(null," Usuário já cadastrado!");
                
            }
            
            if(crm.length()!=6){
                verificador2=true;
                JOptionPane.showMessageDialog(null,"CRM invalido!");
            }
            
            if(verificador2==false&&senha.equals(confirmar)==true){
                Medico medico = new Medico(nome,especialidade,senha,crm);
                String senhaAdm = JOptionPane.showInputDialog("Para castrar um medico precisa-se da senha do admnistrador ");
                daom.create(medico, senhaAdm);
            }
            if(senha.equals(confirmar)==false){
                JOptionPane.showMessageDialog(null, "Olá, o Cadastro não deu certo! senhas nao batem.","ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cpfTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfTxtActionPerformed
        
    }//GEN-LAST:event_cpfTxtActionPerformed

    private void especialidadeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_especialidadeTxtActionPerformed
        
    }//GEN-LAST:event_especialidadeTxtActionPerformed

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
            java.util.logging.Logger.getLogger(CadastrarPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastrarPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastrarPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastrarPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarPessoa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbxMedico;
    private javax.swing.JCheckBox cbxPaciente;
    private javax.swing.JTextField confirmarTxt;
    private javax.swing.JTextField cpfTxt;
    private javax.swing.JTextField crmTxt;
    private javax.swing.JTextField especialidadeTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCRM;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel lblConfirmarSenha;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblEspecialidade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JTextField nomeTxt;
    private javax.swing.JTextField senhaTxt;
    // End of variables declaration//GEN-END:variables
}
