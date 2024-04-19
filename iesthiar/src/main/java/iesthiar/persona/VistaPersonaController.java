package iesthiar.persona;

import java.net.URL;
import java.util.ResourceBundle;

import iesthiar.LibretaDirecciones;
import iesthiar.util.UtilidadDeFechas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VistaPersonaController implements Initializable, ChangeListener {

    @FXML
    private TableView<Persona> personaTabla;
    @FXML
    private TableColumn<Persona, String> nombreColumna;
    @FXML
    private TableColumn<Persona, String> apellidosColumna;

    @FXML
    private Label nombreEtiqueta;
    @FXML
    private Label apellidosEtiqueta;
    @FXML
    private Label direccionesEtiqueta;
    @FXML
    private Label codigoPostalEtiqueta;
    @FXML
    private Label ciudadEtiqueta;
    @FXML
    private Label fechaNacimientoEtiqueta;

    private LibretaDirecciones libretaDirecciones;

    public VistaPersonaController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        apellidosColumna.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        // Borramos los textos de los datos de una persona
        mostrarDetallesPersona(null);

        personaTabla.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> mostrarDetallesPersona(newValue));
    }

    public void setLibretaDirecciones(LibretaDirecciones libretaDirecciones) {
        this.libretaDirecciones = libretaDirecciones;
        personaTabla.setItems(libretaDirecciones.getDatosPersona());
    }

    public void mostrarDetallesPersona(Persona persona) {
        if (persona != null) {
            nombreEtiqueta.setText(persona.getNombre());
            apellidosEtiqueta.setText(persona.getApellidos());
            direccionesEtiqueta.setText(persona.getDireccion());
            codigoPostalEtiqueta.setText(Integer.toString(persona.getCodigoPostal()));
            ciudadEtiqueta.setText(persona.getCiudad());
            fechaNacimientoEtiqueta.setText(UtilidadDeFechas.formato(persona.getFechaNacimiento()));
        } else {
            nombreEtiqueta.setText("");
            apellidosEtiqueta.setText("");
            direccionesEtiqueta.setText("");
            codigoPostalEtiqueta.setText("");
            ciudadEtiqueta.setText("");
            fechaNacimientoEtiqueta.setText("");
        }
    }

    @FXML
    private void borrarPersona() {
        int indiceSeleccionado = personaTabla.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0) {
        personaTabla.getItems().remove(indiceSeleccionado);
        } else {
        // Muestro alerta
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("Atención");
        alerta.setHeaderText("Persona no seleccionada");
        alerta.setContentText("Por favor, selecciona una persona de la tabla");
        alerta.showAndWait();
        }
    }

    // Muestro el diálogo editar persona cuando el usuario hace clic en el botón de
    // Crear
    @FXML
    private void crearPersona() {
        Persona temporal = new Persona();
        boolean guardarClicked = libretaDirecciones.muestraEditarPersona(temporal);
        if (guardarClicked) {
            libretaDirecciones.getDatosPersona().add(temporal);
        }
    }

    // Muestro el diálogo editar persona cuando el usuario hace clic en el botón de
    // Editar
    @FXML
    private void editarPersona() {
        Persona seleccionada = personaTabla.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean guardarClicked = libretaDirecciones.muestraEditarPersona(seleccionada);
            if (guardarClicked) {
                mostrarDetallesPersona(seleccionada);
            }
        } else {
            // Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Persona no seleccionada");
            alerta.setContentText("Por favor, selecciona una persona");
            alerta.showAndWait();
        }
    }

}
