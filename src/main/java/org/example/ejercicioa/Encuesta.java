package org.example.ejercicioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Clase principal de la aplicación Encuesta.
 * Extiende la clase Application de JavaFX para iniciar la interfaz gráfica.
 */
public class Encuesta extends Application {
    /**
     * Método que inicia la aplicación JavaFX.
     * Carga el archivo FXML y establece la escena en el escenario principal.
     *
     * @param stage el escenario principal donde se mostrará la escena
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Encuesta.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Encuesta!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
     /**
     * Método principal que lanza la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados en esta aplicación)
     */
    public static void main(String[] args) {
        launch();
    }
}