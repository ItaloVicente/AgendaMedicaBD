Comando para criar a tabela MySQL:

CREATE TABLE consulta ( id_consulta INT NOT NULL AUTO_INCREMENT, id_medico INT, id_paciente INT, data_consulta DATE, horário TIME, descrição TEXT, nota DOUBLE, status TEXT, PRIMARY KEY (id_consulta), FOREIGN KEY (id_medico) REFERENCES medico(id_medico), FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ); 

CREATE TABLE medico ( id_medico INT NOT NULL AUTO_INCREMENT, nome TEXT, especialidade TEXT, senha TEXT, crm VARCHAR(6) UNIQUE, PRIMARY KEY (id_medico) ); 

CREATE TABLE paciente ( id_paciente INT NOT NULL AUTO_INCREMENT, nome TEXT, sexo TEXT, senha TEXT, data_de_nascimento DATE, cpf VARCHAR(11) UNIQUE, PRIMARY KEY (id_paciente) );

link de como criar a tabela e o banco no NetBeans: https://www.youtube.com/watch?v=EZPYKkPkN4A&list=PLWd_VnthxxLcuMX7LluyGXGYNfWNAFvz2&index=4

Baixe o MySQL por esse link: https://dev.mysql.com/downloads/connector/j/

Pesquise por esse arquivo:

Platform independent .zip
