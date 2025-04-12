package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Comandos {

    public void add(String descricao) throws IOException {
        File arquivo = new File("tasks.json");

        // verifica se existe o tasks.json
        if (arquivo.isFile() && arquivo.exists()) {
            System.out.println(arquivo + " Existe!");
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
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro: " + e);
        }
    }
}
