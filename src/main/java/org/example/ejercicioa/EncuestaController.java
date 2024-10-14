package org.example.ejercicioa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controlador para la encuesta de la aplicación.
 * Maneja la interacción del usuario y la validación de datos ingresados.
 */
public class EncuestaController {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private RadioButton btHombre;

    @FXML
    private RadioButton btMujer;

    @FXML
    private RadioButton btOtro;

    @FXML
    private CheckBox checkDeporte;

    @FXML
    private ComboBox<String> comboEdad;

    @FXML
    private VBox contenedorBase;

    @FXML
    private HBox contenedorBotones;

    @FXML
    private HBox contenedorCine;

    @FXML
    private HBox contenedorCompras;

    @FXML
    private HBox contenedorDeporte;

    @FXML
    private HBox contenedorHermanosEdad;

    @FXML
    private VBox contenedorLista;

    @FXML
    private HBox contenedorProfesion;

    @FXML
    private HBox contenedorSexo;

    @FXML
    private HBox contenedorTele;

    @FXML
    private ToggleGroup grupoSexo;

    @FXML
    private Label labelTele;

    @FXML
    private Label lblAficion;

    @FXML
    private Label lblCine;

    @FXML
    private Label lblCompras;

    @FXML
    private Label lblCual;

    @FXML
    private Label lblEdad;

    @FXML
    private Label lblEncuesta;

    @FXML
    private Label lblHermanos;

    @FXML
    private Label lblProfesion;

    @FXML
    private Label lblSexo;

    @FXML
    private ListView<String> lstDeportes;

    @FXML
    private Slider sliderCine;

    @FXML
    private Slider sliderCompras;

    @FXML
    private Slider sliderTele;

    @FXML
    private TextField txtHermanos;

    @FXML
    private TextField txtProfesion;

    /**
     * Maneja el evento de clic en el botón Aceptar.
     * Valida los datos y muestra una alerta con los resultados de la encuesta.
     *
     * @param event el evento de acción que desencadena el método
     */
    @FXML
    void aceptar(ActionEvent event) {
        boolean error=false;
        ArrayList<String> errores=new ArrayList<>();
        if(txtProfesion.getText().equals("")){
            errores.add("Hay que rellenar el campo Profesión.");
            error=true;
        }
        if(txtHermanos.getText().equals("")){
            errores.add("Hay que rellenar el campo Hermanos.");
            error=true;
        }else{
            try{Integer.parseInt(txtHermanos.getText());} catch (NumberFormatException e) {
                errores.add("El campo Hermanos debe ser numérico.");
                error=true;
            }
        }
        if(checkDeporte.isSelected()){
            if(lstDeportes.getSelectionModel().getSelectedItem()==null){
                errores.add("Debes seleccionar los deportes que practicas.");
                error=true;
            }
        }
        if(error){
            mostrarAlertError(btAceptar.getScene().getWindow(),errores);
        }else{
            ArrayList<String> lst= new ArrayList<>();
            lst.add("Profesión: "+txtProfesion.getText());
            lst.add("Nº de Hermanos: "+txtHermanos.getText());
            lst.add("Edad: "+comboEdad.getSelectionModel().getSelectedItem());
            lst.add("Sexo: "+ ((RadioButton)grupoSexo.getSelectedToggle()).getText());
            if(checkDeporte.isSelected()){
                lst.add("Deportes que practicas: ");
                for(String str:lstDeportes.getSelectionModel().getSelectedItems()){
                    lst.add("\t"+str);
                }
            }else{
                lst.add("No practicas ningún deporte.");
            }
            DecimalFormat df = new DecimalFormat("#.00"); //He puesto esto para que no quede feo con tantos decimales
            lst.add("Grado de afición a las compras: "+df.format(sliderCompras.getValue()));
            lst.add("Grado de afición a ver la televisión: "+df.format(sliderTele.getValue()));
            lst.add("Grado de afición a ir al cine: "+df.format(sliderCine.getValue()));
            mostrarVentanaDatos(lst);
        }
    }
    /**
     * Muestra una ventana de alerta con los datos metidos por el usuario.
     *
     * @param lst lista de strings que contienen los datos a mostrar
     */
    private void mostrarVentanaDatos(ArrayList<String> lst) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("TUS DATOS");
        String contenido="";
        for(String str:lst){
            contenido+=str+"\n";
        }
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
    /**
     * Maneja el evento de clic en el botón Cancelar.
     * Cierra la ventana actual.
     *
     * @param event el evento de acción que desencadena el método
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
    /**
     * Maneja el evento de selección del CheckBox de deportes.
     * Habilita o deshabilita la lista de deportes según el estado del CheckBox.
     *
     * @param event el evento de acción que desencadena el método
     */
    @FXML
    void seleccionDeportes(ActionEvent event) {
        if(checkDeporte.isSelected()){
            lstDeportes.setDisable(false);
        }else{
            lstDeportes.setDisable(true);
        }
    }
    /**
     * Inicializa el controlador.
     * Configura los elementos de la interfaz y las listas desplegables.
     */
    public void initialize() {
        comboEdad.getItems().addAll(
                "Menores de 18",
                "Entre 18 y 30",
                "Entre 31 y 50",
                "Entre 51 y 70",
                "Mayores de 70"
        );
        comboEdad.getSelectionModel().select(0);//Pongo esto para que por defecto esté este

        Tooltip tooltipCheck = new Tooltip("Selecciona para ver la lista de deportes");
        checkDeporte.setTooltip(tooltipCheck);

        lstDeportes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Tooltip tooltipDeporte = new Tooltip("Elije los deportes que practiques");
        lstDeportes.setTooltip(tooltipDeporte);
        lstDeportes.getItems().addAll(
                "Tenis",
                "Fútbol",
                "Baloncesto",
                "Natación",
                "Ciclismo",
                "Otro"
        );
        Tooltip tooltipCompras = new Tooltip("Indica del 1 al 10 cúanto te gusta ir de compras");
        sliderCompras.setTooltip(tooltipCompras);
        Tooltip tooltipTele = new Tooltip("Indica del 1 al 10 cúanto te gusta ver la tele");
        sliderTele.setTooltip(tooltipTele);
        Tooltip tooltipCine = new Tooltip("Indica del 1 al 10 cúanto te gusta ir al cine");
        sliderCine.setTooltip(tooltipCine);
    }
    /**
     * Muestra una alerta de error en caso de que los datos ingresados no sean válidos.
     *
     * @param win  la ventana que inicia la alerta
     * @param lst  lista de mensajes de error a mostrar
     */
    private void mostrarAlertError(Window win,ArrayList<String> lst) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(win);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        String error="";
        for(String str:lst) {
         error+=str+"\n";
        }
        alert.setContentText(error);
        alert.showAndWait();
    }
}
