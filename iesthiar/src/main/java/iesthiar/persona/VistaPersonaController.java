package iesthiar.persona;

import java.net.URL;
import java.util.ResourceBundle;

import iesthiar.LibretaDirecciones;
import iesthiar.util.UtilidadDeFechas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        //Borramos los textos de los datos de una persona
        mostrarDetallesPersona(null);

        personaTabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallesPersona(newValue));
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
        personaTabla.getItems().remove(indiceSeleccionado);
    }

}
