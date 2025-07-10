package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        // Obtener el entorno deseado (por defecto: dev)
        String env = System.getProperty("env", "dev");
        String configFile = String.format("config/%s.properties", env);

        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream(configFile)) {

            if (input == null) {
                throw new RuntimeException("Archivo de configuración no encontrado: " + configFile);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando archivo de configuración: " + configFile, e);
        }
    }

//    static { //Este bloque static se ejecuta una única vez cuando se accede por primera vez a la clase ConfigManager.
//        // Es ideal para inicializar recursos estáticos como propiedades de configuración.
//        String env = System.getProperty("env", "dev");
//        try (InputStream input = new FileInputStream("src/main/resources/config/" + env + ".properties")) {
//            properties.load(input);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load config file for environment: " + env, e);
//        }
//    }

    public static String getProperty(String key) {
//        return properties.getProperty(key);
        return System.getProperty(key, properties.getProperty(key));
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

}