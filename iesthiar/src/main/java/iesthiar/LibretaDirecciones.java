package iesthiar;

import java.io.IOException;

import iesthiar.persona.Persona;
import iesthiar.persona.VistaPersonaController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LibretaDirecciones extends Application {
    
    private Stage escenarioPrincipal;
    private BorderPane contenedorPrincipal;
    private ObservableList<Persona> datosPersona = FXCollections.observableArrayList();

    @Override
    public void start(Stage escenarioPrincipal) {
        //Necesario para cambiar las escenas.
        this.escenarioPrincipal=escenarioPrincipal;
        //Estableciendo el titulo
        this.escenarioPrincipal.setTitle("Libreta de direcciones");
        initContenedorPrincipal();
        mostrarVistaPeronas();
    }

    public void initContenedorPrincipal() {
        try {
            //Carga el contenido principal desde el fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LibretaDirecciones.class.getResource("vistaPrincipal.fxml"));
            contenedorPrincipal = (BorderPane) loader.load();

            //Muestra la escena del contenedor principal
            Scene scene = new Scene(contenedorPrincipal);
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }

    public void mostrarVistaPeronas() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LibretaDirecciones.class.getResource("persona/vistaPersona.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            contenedorPrincipal.setCenter(personOverview);
            VistaPersonaController vistaPersonaController = loader.getController();
            vistaPersonaController.setLibretaDirecciones(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public LibretaDirecciones() {
        datosPersona.add(new Persona("Andrés","Muñoz"));
        datosPersona.add(new Persona("Maria","Cubides"));
        datosPersona.add(new Persona("Pepe","Rodriguez"));
        datosPersona.add(new Persona("Juan","Nuñez"));
        datosPersona.add(new Persona("Lenny","Diaz"));
        datosPersona.add(new Persona("Homero", "Simpson"));
    }

    public ObservableList<Persona> getDatosPersona() {
        return datosPersona;
    }

    public static void main(String[] args) {
        launch(args);
    }

}