/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.agendamedica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class AgendaMedica {

    public static void main(String[] args) throws Exception {
        Paciente p1 = new Paciente("Italo", "18", "M", "12345");
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(p1);
        System.out.println("Hello World!");
        Medico m1 = new Medico("ui","ai","123");
        System.out.println(m1.getNome());
        Consulta c1 = new Consulta(m1.getNome(), "Itin", "02/02/2006","09:00");
        m1.adicionarConsulta(c1);
        m1.gerarRelatorio("02/05/2005", "15/08/2006");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Consulta> consultas = new ArrayList<>();
        consultas.add(c1);
        try (Writer writer = new FileWriter("consultas.json")) {
            String jsonNovo = gson.toJson(pacientes);
            System.out.println(jsonNovo);
            gson.toJson(consultas,writer);
            writer.flush();
            writer.close();
        }
    }
}
