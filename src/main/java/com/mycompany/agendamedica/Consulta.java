/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agendamedica;

/**
 *
 * @author super
 */
public class Consulta {
    private String nomeMedico;
    private String nomePaciente;
    private String data;
    private String horario;
    private String descricao;
    
    //data no formato dd/mm/aaaa

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Consulta(String nomeMedico, String nomePaciente, String data, String horario) {
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.data = data;
        this.horario=horario;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public String toString(){
        return "Medico: " + this.nomeMedico + " " + "Paciente: " + this.nomePaciente + " " + "Data: " + this.data + " Horario: " + this.horario;
    }
    
}
