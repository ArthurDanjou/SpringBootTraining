package fr.arthurdanjou.springboottraining.config;

import com.google.gson.Gson;
import fr.arthurdanjou.springboottraining.SpringBoot;
import lombok.Getter;

import java.io.*;

public class ConfigurationManager {

    private SpringBoot main;
    private Gson gson;
    private File configFile;

    @Getter
    private Configuration configuration;

    public ConfigurationManager(SpringBoot main, Gson gson, String path) {
        this.main = main;
        this.gson = gson;
        this.configFile = new File(path);
    }

    public void loadConfiguration() {
        String json = loadFile(this.configFile);
        if (!json.equals("")) {
            configuration = this.gson.fromJson(json, Configuration.class);
            main.getLogger().info("Configuration chargée !");
        } else {
            configuration = new Configuration();
            save(this.configFile, this.gson.toJson(configuration));
            main.getLogger().info("Veuillez remplir le fichier de confuguration !");
            System.exit(1);
        }
    }

    public static String loadFile(File file) {
        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            try {
                String line;
                BufferedReader reader = new BufferedReader(new FileReader(file));

                while ((line = reader.readLine()) != null) sb.append(line);

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void save(File file, String content) {
        if (file.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
