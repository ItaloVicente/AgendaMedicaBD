package com.mycompany.agendamedica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author super
 */
public class AgendaMedica {
    //ano/mes/dia
    public static void main(String[] args) {
        //focar nos ids
//        Medico m = new Medico("VICENTE", "Cardiologia", "12345", "07");
//        m.setId(13);        
//        MedicoDAO daom = new MedicoDAO();
//        daom.create(m,"12345");
//        Paciente p = new Paciente("CRIS","02/02/04","M", "12345", "08");
//        p.setId(5);
//        PacienteDAO daop = new PacienteDAO();
//        daop.create(p);
//        Consulta c = new Consulta(15, 10, "24/05/01", "10:00","espera");
//        c.setDescricao("2");
//        ConsultaDAO dao = new ConsultaDAO();
//        dao.create(c);
        //dao.delete(c);
        /*
        for(Consulta consulta : consultas){
            System.out.println(consulta.getStatus());
        }
*/
        new Cadastro().setVisible(true);
    }
}
