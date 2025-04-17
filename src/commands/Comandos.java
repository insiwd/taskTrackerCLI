package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import task.Task;

public class Comandos {

    public void add(String descricao) throws IOException {
        File arquivo = new File("tasks.json");

        Task task = new Task(descricao);
        String taskJson = String.format(
                "{\"id\": %d, \"descricao\": \"%s\"}",
                task.getId(),
                task.getDescription());

        if (!arquivo.exists()) {
            // se não existe, cria
            arquivo.createNewFile();
            try (FileWriter fileWriter = new FileWriter(arquivo)) {
                fileWriter.write("[]");
                fileWriter.flush();
            }
        } else {
            System.out.println("arquivo existe.");
        }

        // precisa de um try-with-resources para não ter warning
        try (Scanner sc = new Scanner(arquivo)) {
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
                // cria uma nova string
                FileWriter fileWriter = new FileWriter(arquivo, false);
                fileWriter.write("[" + taskJson + "]");
                fileWriter.close();
            } else {
                // quando já tem conteudo
                List<String> objetoStrings = separaObjetos(jsonBruto); // quebra os objetos existentes
                objetoStrings.add(taskJson);

                String novoJson = "[" + String.join(",", objetoStrings) + "]";

                // escreve no arquivo.
                // não precisa do close, porque o try já fecha
                try (FileWriter fileWriter = new FileWriter(arquivo, false)) {
                    fileWriter.write(novoJson);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro: " + e);
        }
    }

    public List<String> separaObjetos(String conteudoJson) {

        // precisamos remover os colchetes iniciais e finais
        if (conteudoJson.startsWith("[") && conteudoJson.endsWith("]")) {
            // pula o primeiro caractere
            String novoConteudoJson = conteudoJson.substring(1, conteudoJson.length() - 1);

            // cria uma lista vazia
            List<String> objetos = new ArrayList<>();

            // manter tracking do indice atual
            // stringbuilder acumula os valores
            StringBuilder objetoAtual = new StringBuilder();
            int nivel = 0;

            for (int i = 0; i < novoConteudoJson.length(); i++) {
                char c = novoConteudoJson.charAt(i);
                if (c == '{') {
                    nivel++;
                }
                if (nivel > 0) {
                    objetoAtual.append(c);
                }
                if (c == '}') {
                    nivel--;
                    if (nivel == 0) {
                        objetos.add(objetoAtual.toString());
                        objetoAtual.setLength(0);
                    }
                }

            }
            return objetos;
        }
        return new ArrayList<>();
    }

    public String removerEspacosForaDasStrings(String json) {
        // stringbuilder para ir incrementando
        StringBuilder resultado = new StringBuilder();
        boolean dentroDeString = false;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            switch (c) {
                case '"' -> {
                    // alterna o valor atual
                    dentroDeString = !dentroDeString;
                    resultado.append(c);
                }
                case ' ' -> {
                    if (dentroDeString == true) {
                        resultado.append(' ');
                    }
                }
                default -> resultado.append(c);
            }
        }
        return resultado.toString();
    }
}