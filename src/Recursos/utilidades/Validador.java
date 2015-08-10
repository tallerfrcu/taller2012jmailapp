/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos.utilidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextField;

/**
 * Clase que representa un validador de datos de entrada. Se utiliza para
 * validar los datos que ingresa el usuario al sistema.
 * @author Accornero, Fontana, García, Pascal
 */
public class Validador {
    /**
     * Constructor de la calse que instancia un validador
     */
    public Validador(){}
    /**
     * Método que valida que dos contraseñas ingresadas sean iguales
     * @param contrasena1 primer contraseña ingresada
     * @param contrasena2 segunda contraseña ingresada
     * @return true si las contraseñas son iguales, false en caso contrario
     */
    public boolean validarContraseñas(String contrasena1, String contrasena2){
        return contrasena1.contentEquals(contrasena2);
    }
    /**
     * Método que valida los campos obligatorios para cargar en algún 
     * formulario o pantalla
     * @param campos lista de campos que son obligatorios
     * @return lista de campos que están vacíos.
     */
    public List<JTextField> validarDatosObligatorios(List<JTextField> campos) {
        List<JTextField> camposInvalidos = new ArrayList<>();
        Iterator iterador = campos.iterator();
        boolean esValido = false;
        while(iterador.hasNext()){
            JTextField campoObligatorio = (JTextField)iterador.next();
            if(campoObligatorio.getText().isEmpty()){
                camposInvalidos.add(campoObligatorio);
            }
        }
        return camposInvalidos;
    }
}
