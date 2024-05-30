package mx.homek.logic.Validadores;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.PasswordField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ValidadorDeReglas {
    public String hashearContraseña(String contraseña){
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte[] hash = algoritmo.digest(contraseña.getBytes());


            StringBuilder cadenaHexadecimal = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    cadenaHexadecimal.append('0');
                }
                cadenaHexadecimal.append(hex);
            }
            return cadenaHexadecimal.toString();
        } catch (NoSuchAlgorithmException excepcion) {
            excepcion.printStackTrace();
        }
        return "";
    }
    public void agregarLimiteATextField (TextField textField, int limite){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String viejoValor, String nuevoValor) {
                if(textField.getText().length() > limite){
                    String cadenaAuxiliar = textField.getText().substring(0,limite);
                    textField.setText(cadenaAuxiliar);
                }
                else if(textField.getText().length() > 1 && textField.getText().substring(textField.getText().length() - 2,textField.getText().length() ).equals("  ")){
                    String cadenaAuxiliar = textField.getText().substring(0,textField.getText().length() - 1);
                    textField.setText(cadenaAuxiliar);
                }
                else if(textField.getText().length() == 1 && textField.getText().substring(0,textField.getText().length()).equals(" ")) {
                    String cadenaAuxiliar = textField.getText().substring(0, 0);
                    textField.setText(cadenaAuxiliar);
                }
            }
        });
    }
    public void limitarCampoNumerico(TextField textField){

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (textField.getText().length() > 0) {
                    char caracter = textField.getText().charAt(textField.getText().length()-1);
                    if (!Character.isDigit(caracter)) {
                        String cadenaAuxiliar = textField.getText().substring(0, textField.getText().length() - 1);
                        textField.setText(cadenaAuxiliar);
                    }
                }
            }
        });
    }
    public String validarUltimoCarcater(String cadena){
        if(cadena.length() > 0) {
            if (cadena.substring(cadena.length() - 1, cadena.length()).equals(" ")) {
                return cadena.substring(0, cadena.length() - 1);
            } else {
                return cadena;
            }
        }
        else{
            return "";
        }
    }
    public void limitarCampoTexto(TextField textField){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (textField.getText().length() > 0) {
                    char caracter = textField.getText().charAt(textField.getText().length() - 1);
                    if (Character.isDigit(caracter)) {
                        String cadenaAuxiliar = textField.getText().substring(0, textField.getText().length() - 1);
                        textField.setText(cadenaAuxiliar);
                    }
                }
            }
        });
    }
    public void agregarLimiteAPasswordField (PasswordField passwordField, int limite){
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(passwordField.getText().length() > limite) {
                    String cadenaAuxiliar = passwordField.getText().substring(0, limite);
                    passwordField.setText(cadenaAuxiliar);
                }
            }
        });
    }
    public boolean validadorDeCaracteresEspeciales (ArrayList<String> cadenas){
        for (String cadena:cadenas) {
            if (cadena.indexOf("'") >= 0 || cadena.indexOf(";") >= 0 || cadena.equals("or") ) {
                return true;
            }
        }
        return false;
    }
    public boolean validadorDeCamposVacios (ArrayList <String> cadenas){
        for(String cadena:cadenas) {
            if (cadena.equals("")) {
                return true;
            }
        }
        return false;
    }
    public boolean validadorComboBoxVacios(ArrayList <String> campos){
        for (String cadena : campos){
            if(cadena == null){
                return true;
            }
        }
        return false;
    }

    public boolean validadorSelectorDeFechasVacios(ArrayList <LocalDate> campos){
        for (LocalDate fecha : campos){
            if(fecha == null){
                return true;
            }
        }
        return false;
    }

    public void proponerNombreUsuario(TextField nombre, TextField apellidoPaterno, TextField apellidoMaterno, TextField nombreUsuario){
        apellidoMaterno.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String nombreUsuarioPropuesto = new String();
                if(nombre.getText().length() > 0){
                    nombreUsuarioPropuesto = nombre.getText().substring(0,1);
                }
                nombreUsuarioPropuesto += apellidoPaterno.getText();
                if(apellidoMaterno.getText().length()>1){
                    nombreUsuarioPropuesto += apellidoMaterno.getText().substring(0,1);
                }
                int n = (nombre.getText().length() + apellidoPaterno.getText().length() + apellidoMaterno.getText().length()) %10;
                nombreUsuarioPropuesto += Integer.toString(n);
                nombreUsuario.setText(nombreUsuarioPropuesto);
            }
        });
    }

    public boolean validadorCampoConCaracteresEspeciales(String cadena) {
        return cadena.contains("'") && cadena.contains(";") && cadena.contains("or");
    }

    public boolean validadorCampoVacio(String cadena) {
        return cadena.isEmpty();
    }
}

