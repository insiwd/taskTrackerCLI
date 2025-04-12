package commands;

import java.io.File;

public class Comandos {

    public void add(String descricao) {
        File file = new File("tasks.json");
        
        // verifica se existe um JSON
        if (file.isFile()) {
            System.out.println(file + " Exists");
        }
        else {
            System.out.println(file + " Do not exist! \nCreating one!");

        }
    }

}
