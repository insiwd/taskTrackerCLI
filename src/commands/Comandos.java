package commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Comandos {

    public void add(String descricao) throws IOException {
        File file = new File("tasks.json");

        // verifica se existe um JSON
        if (file.isFile() && file.exists()) {
            System.out.println(file + " Exists");
        } else {
            System.out.println(file + " Do not exist! \nCreating one!");

            file.createNewFile();
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("[]");
                fileWriter.flush();
            }
        }
    }
}
