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
public class Medico {
    private String nome;
    private String especialidade;
    private String senha;
    private ArrayList<Double> notas = new ArrayList();
    private ArrayList<Consulta> consultas = new ArrayList();
    private ArrayList<Consulta> listaEspera = new ArrayList();
    public Medico(String nome, String especialidade, String senha) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.senha = senha;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

    public ArrayList<Consulta> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(ArrayList<Consulta> listaEspera) {
        this.listaEspera = listaEspera;
    }
    
    public void adicionarNota(double nota){
        notas.add(nota);
    }
    public double calcularMedia(){
        double media = 0;
        double som = 0;
        for (double i: this.notas){
            som=som+i;
        }
        media = som/this.notas.size();
        return media;
    }
    public void adicionarConsulta (Consulta consulta){
        
            this.consultas.add(consulta);
    }
    public void adicionarListaEspera (Consulta consulta){
        this.listaEspera.add(consulta);
    }
    //Periodo no formato dd/mm/aaaa
    public ArrayList<Consulta> gerarRelatorio(String periodoInicial,String periodoFinal) throws ParseException,ExeptionNoMedico{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial = sdf.parse(periodoInicial);
        Date dataFinal = sdf.parse(periodoFinal);
        ArrayList<Consulta> consultasPeriodo = new ArrayList<>();
        if(this.especialidade==null){
            throw new ExeptionNoMedico();
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
    public void verificarPessoa() throws ExeptionNoMedico{
        if(this.especialidade==null){
            throw new ExeptionNoMedico();
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
    @Override
    public String toString() {
        return "Medico{" + "nome=" + nome + ", especialidade=" + especialidade + ", notas=" + notas + '}';
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
