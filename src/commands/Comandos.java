package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import task.Task;

public class Comandos {

    public void add(String descricao) throws IOException {
        File arquivo = new File("tasks.json");

        // verifica se existe o tasks.json
        if (arquivo.isFile() && arquivo.exists()) {
            System.out.println(arquivo + " Existe!");
            Task task = new Task(descricao);
            String taskJson = String.format(
                "{\"id\": %d, \"descricao\": %s}",
                task.getId(),
                task.getDescription()
            );

        } else {
            // se não existe, cria
            System.out.println(arquivo + " Não existe :( \nCriando..!");
            arquivo.createNewFile();
            try (FileWriter fileWriter = new FileWriter(arquivo)) {
                fileWriter.write("[]");
                fileWriter.flush();
            }
        }

        // precisa de um try-with-resources para não ter warning
        try (Scanner sc = new Scanner(arquivo)){
            // guardando o JSON emuma string
            StringBuilder conteudo = new StringBuilder();
            // lendo e guardando
            while (sc.hasNextLine()) {
                conteudo.append(sc.nextLine());
            }

            // guardando o JSON lido em outra string, para ver se está vazio ou tem conteúdo
            // removendo espaços
            // se está vazio, printa que está vazio!!
            String jsonBruto = conteudo.toString().replaceAll("\\s+", "");
            if (jsonBruto.equals("[]")) {
                System.out.println("Vazio!");
            } else {
                System.out.println("Tem conteúdo!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro: " + e);
        }
    }
}
