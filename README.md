# Vídeo da Apresentação
https://www.youtube.com/watch?v=cLY_0DggBSY

# Configurando o MySQL

Comando para criar a tabela MySQL: 

CREATE TABLE medico ( id_medico INT NOT NULL AUTO_INCREMENT, nome TEXT, especialidade TEXT, senha TEXT, crm VARCHAR(6) UNIQUE, ativo BOOLEAN DEFAULT 1, PRIMARY KEY (id_medico) ); 

CREATE TABLE paciente ( id_paciente INT NOT NULL AUTO_INCREMENT, nome TEXT, sexo VARCHAR(1), senha TEXT, data_de_nascimento DATE, cpf VARCHAR(11) UNIQUE, ativo BOOLEAN DEFAULT 1, PRIMARY KEY (id_paciente) );

CREATE TABLE telefone (id_telefone INT NOT NULL AUTO_INCREMENT, telefone VARCHAR(11), id_paciente INT, PRIMARY KEY (id_telefone), FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente));

CREATE TABLE consulta ( id_consulta INT NOT NULL AUTO_INCREMENT, id_medico INT, id_paciente INT, data_consulta DATE, horario TIME, descricao TEXT, nota DOUBLE, status TEXT, prioridade INT, PRIMARY KEY (id_consulta), FOREIGN KEY (id_medico) REFERENCES medico(id_medico), FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) );

link de como criar a tabela e o banco no NetBeans: https://www.youtube.com/watch?v=EZPYKkPkN4A&list=PLWd_VnthxxLcuMX7LluyGXGYNfWNAFvz2&index=4

Baixe o MySQL por esse link: https://dev.mysql.com/downloads/connector/j/

Pesquise por esse arquivo:

Platform independent .zip
