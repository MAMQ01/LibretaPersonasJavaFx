package iesthiar.persona;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import iesthiar.util.UtilidadDeFechas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditorPersonaController implements Initializable {

    // TextField para los campos

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidosTextField;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField codigoPostalTextField;

    @FXML
    private TextField ciudadTextField;

    @FXML
    private TextField fechaNacimientoTextField;

    private Stage escenarioEdicion; // Escenario de edición
    private Persona persona; // Referencia a la clase persona
    private boolean guardarClicked = false;

    // Inicializa la clase controller y es llamado justo DESPUÉS de cargar el
    // archivo FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    // Establece el escenario de edición
    public void setEscenarioEdicion(Stage escenarioEdicion) {
        this.escenarioEdicion = escenarioEdicion;
    }

    // Establece la persona a editar
    public void setPersona(Persona persona) {
        this.persona = persona;
        nombreTextField.setText(persona.getNombre());
        apellidosTextField.setText(persona.getApellidos());
        direccionTextField.setText(persona.getDireccion());
        codigoPostalTextField.setText(Integer.toString(persona.getCodigoPostal()));
        ciudadTextField.setText(persona.getCiudad());
        fechaNacimientoTextField.setText(UtilidadDeFechas.formato(persona.getFechaNacimiento()));
        fechaNacimientoTextField.setPromptText("dd/mm/yyyy");
    }

    // Devuelve true si se ha pulsado Guardar, si no devuelve false
    public boolean isGuardarClicked() {
        return guardarClicked;
    }

    // LLamado cuando se pulsa Guardar
    @FXML
    private void guardar() {
        if (datosValidos()) {
            // Asigno datos a propiedades de persona
            persona.setNombre(nombreTextField.getText());
            persona.setApellidos(apellidosTextField.getText());
            persona.setDireccion(direccionTextField.getText());
            persona.setCodigoPostal(Integer.parseInt(codigoPostalTextField.getText()));
            persona.setCiudad(ciudadTextField.getText());
            persona.setFechaNacimiento(null);
            guardarClicked = true; // Cambio valor booleano
            escenarioEdicion.close(); // Cierro el escenario de edición
        }
    }

    // LLamado cuando se pulsa Cancelar
    @FXML
    private void cancelar() {
        escenarioEdicion.close();
    }

    // Validación de datos
    private boolean datosValidos() {
        // Inicializo string para mensajes
        String mensajeError = "";
        // Compruebo los campos
        if (nombreTextField.getText() == null || nombreTextField.getText().length() == 0) {
            mensajeError += "Nombre no válido.";
        }
        if (apellidosTextField.getText() == null || apellidosTextField.getText().length() == 0) {
            mensajeError += "Apellidos no válidos.";
        }
        if (direccionTextField.getText() == null || direccionTextField.getText().length() == 0) {
            mensajeError += "Dirección no válida.";
        }
        if (codigoPostalTextField.getText() == null ||
                codigoPostalTextField.getText().length() == 0) {
            mensajeError += "Código postal no válido.";
        } else {
            // Convierto el código postal a entero
            try {
                Integer.parseInt(codigoPostalTextField.getText());
            } catch (NumberFormatException e) {
                mensajeError += "Código postal no válido (debe ser un entero).\n";
            }
        }
        if (ciudadTextField.getText() == null || ciudadTextField.getText().length() == 0) {
            mensajeError += "Ciudad no válida.";
        }
        if (fechaNacimientoTextField.getText() == null ||
                fechaNacimientoTextField.getText().length() == 0) {
            mensajeError += "Fecha de nacimiento no válida.";
        } else {
            if (!UtilidadDeFechas.fechaValida(fechaNacimientoTextField.getText())) {
                mensajeError += "Fecha de nacimiento no válida (debe tener formato dd/mm/yyyy).";
            }
        }
        // Si no hay errores devuelvo true, si no, una alerta con los errores y false
        if (mensajeError.length() == 0) {
            return true;
        } else {
            // Muestro alerta y devuelvo false
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Datos no válidos");
            alerta.setContentText("Por favor, corrige los errores");
            alerta.showAndWait();
            return false;
        }
    }
}