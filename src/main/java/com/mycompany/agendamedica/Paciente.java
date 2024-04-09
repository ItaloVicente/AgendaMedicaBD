/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agendamedica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author super
 */
public class Paciente {
    private String nome;
    private String idade;
    private String sexo;
    private String senha;
    private ArrayList<Consulta> consultas = new ArrayList<>();
    private ArrayList<Consulta> listaEspera = new ArrayList<>();
    private ArrayList<Consulta> consultasRealizadas = new ArrayList<>();
    private ArrayList<Consulta> avaliarConsultas = new ArrayList<>();

    public ArrayList<Consulta> getAvaliarConsultas() {
        return avaliarConsultas;
    }

    public void setAvaliarConsultas(ArrayList<Consulta> avaliarConsultas) {
        this.avaliarConsultas = avaliarConsultas;
    }
    

    public ArrayList<Consulta> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(ArrayList<Consulta> listaEspera) {
        this.listaEspera = listaEspera;
    }

    public ArrayList<Consulta> getConsultasRealizadas() {
        return consultasRealizadas;
    }

    public void setConsultasRealizadas(ArrayList<Consulta> consultasRealizadas) {
        this.consultasRealizadas = consultasRealizadas;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

    public Paciente(String nome, String idade, String sexo, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public ArrayList<Consulta> gerarRelatorio(String periodoInicial,String periodoFinal) throws ParseException,ExceptionNoPaciente{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial = sdf.parse(periodoInicial);
        Date dataFinal = sdf.parse(periodoFinal);
        ArrayList<Consulta> consultasPeriodo = new ArrayList<>();
        if(this.idade==null){
            throw new ExceptionNoPaciente();
        }
        for(Consulta i : this.consultas){
            Date dataConsulta = sdf.parse(i.getData());
            if(dataConsulta.after(dataInicial)&&dataConsulta.before(dataFinal)){
                consultasPeriodo.add(i);
            }
        }
        if(consultasPeriodo.size()>0){
            System.out.println("Voce possui os seguintes agendamentos/consultas no periodo de: " + periodoInicial + " a " + periodoFinal);
            for(Consulta i : consultasPeriodo){
                System.out.println(i.toString());
            }
        }
        else{
            System.out.println("Voce nao possui agendamentos/consultas no periodo informado");
        }
        return consultasPeriodo;
    }
    public void adicionarConsulta(Consulta consulta){
        this.consultas.add(consulta);
    }
    public void addListaEspera(Consulta consulta){
        this.listaEspera.add(consulta);
    }
    public void cancelarAgendamento(Consulta consulta){
        int index =0;
        for(int i = 0;i<this.consultas.size();i++){
            if(consultas.get(i).getNomeMedico().equals(consulta.getNomeMedico())&&consultas.get(i).getHorario().equals(consulta.getHorario())&&consultas.get(i).getData().equals(consulta.getData())&&consultas.get(i).getNomePaciente().equals(consulta.getNomePaciente())){
                Consulta consultaLista = consultas.get(i);
                if(listaEspera.size()>0){
                    for(int x =0; x<listaEspera.size();x++){
                        Consulta consultaListaEspera = listaEspera.get(x);
                        if(consultaListaEspera.getData().equals(consultaLista.getData())&&consultaListaEspera.getHorario().equals(consultaLista.getHorario())){
                            consultas.add(consultaListaEspera);
                            listaEspera.remove(x);
                        }
                    }
                }
                this.consultas.remove(index);
            }
            index=index+1;
        }
    }
    public void verificarListas (Consulta consulta){
        for(int i =0;i<this.listaEspera.size();i++){
            Consulta consultaListaEspera = listaEspera.get(i);
                        if(consultaListaEspera.getData().equals(consulta.getData())&&consultaListaEspera.getHorario().equals(consulta.getHorario())){
                            consultas.add(consultaListaEspera);
                            listaEspera.remove(i);
                        }
        }
    }
    public void realizarConsulta(Consulta consulta){
        int index =0;
        for(int x=0;x<consultas.size();x++){
            Consulta i = consultas.get(x);
            if(i.getNomeMedico().equals(consulta.getNomeMedico())&&i.getData().equals(consulta.getData())&&i.getNomePaciente().equals(consulta.getNomePaciente())&&i.getHorario().equals(consulta.getHorario())){
                this.consultas.remove(index);
            }
            index=index+1;
        }
    }
    public void avaliarConsulta(Consulta consulta){
        int index =0;
        for(int x=0;x<avaliarConsultas.size();x++){
            Consulta i = avaliarConsultas.get(x);
            if(i.getNomeMedico().equals(consulta.getNomeMedico())&&i.getData().equals(consulta.getData())&&i.getNomePaciente().equals(consulta.getNomePaciente())){
                this.avaliarConsultas.remove(index);
                consultasRealizadas.add(i);
            }
            index=index+1;
        }
    }
    public void verificarPessoa() throws ExceptionNoPaciente{
        if(this.idade==null){
            throw new ExceptionNoPaciente();
        }
    }
    @Override
    public String toString() {
        return "Paciente{" + "nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + '}';
    }
    public void addConsultaRealizada(Consulta consulta){
        this.consultasRealizadas.add(consulta);
    }
    public void addAvaliarConsulta(Consulta consulta){
        this.avaliarConsultas.add(consulta);
    }
    public void removerAvailarConsulta(Consulta consulta){
        int index =0;
        for(int x=0;x<avaliarConsultas.size();x++){
            Consulta i = avaliarConsultas.get(x);
            if(i.getData().equals(consulta.getData())&&i.getHorario().equals(consulta.getHorario())){
                this.avaliarConsultas.remove(index);
            }
            index=index+1;
        }
    }
    public void removerListaEspera(Consulta consulta){
        int index =0;
        for(int x=0;x<listaEspera.size();x++){
            Consulta i = listaEspera.get(x);
            if(i.getData().equals(consulta.getData())&&i.getHorario().equals(consulta.getHorario())){
                this.listaEspera.remove(index);
            }
            index=index+1;
        }
    }
    public void verificarListaEspera(Consulta consulta){
        int index =0;
        for(int x=0;x<listaEspera.size();x++){
            Consulta i = listaEspera.get(x);
            if(i.getData().equals(consulta.getData())&&i.getHorario().equals(consulta.getHorario())){
                this.listaEspera.remove(index);
                this.consultas.add(i);
            }
            index=index+1;
        }
    }
}
