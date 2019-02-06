/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 31/01/2019
 */
package org.springframework.samples.petclinic.otros;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * @author David Pérez S.
 */
public class ComprobadorCodigoPostal {

    private final String USER_AGENT = "Mozilla/5.0";
    private String ciudad;
    private String estado;
    private boolean existeCP;
    
    public ComprobadorCodigoPostal() {
        this.ciudad = "";
        this.estado = "";
        this.existeCP = false;
    }
    
    public boolean comprobarExisteCodigoPostal(String codigoPostal) throws Exception {
        String textoJSON = sendGet(codigoPostal);   // Obtenemos la respuesta JSON
        JSONObject obj = new JSONObject(textoJSON); // Convetimos el texto JSON recibido a un objeto JSON
        this.ciudad = obj.getString("municipio");  // Obtenemos la ciudad o municipio
        this.estado = obj.getString("estado");  // Obtenemos el estado
        
        this.existeCP = !this.ciudad.equals("");
        System.out.println("El CP existe: " + this.existeCP);
        return existeCP;
    }
    
    public String getCiudad() {
        return this.ciudad;
    }
    
    public String getEstado() {
        return this.estado;
    }
    
    public boolean getExisteCP() {
        return this.existeCP;
    }
    
    // HTTP GET request
    private String sendGet(String codigoPostal) throws Exception {

        System.out.println("Prueba 1 - Solicitud de Código Postal en México");
        String url = "https://api-codigos-postales.herokuapp.com/v2/codigo_postal/" + codigoPostal;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // Optional default is GET
        con.setRequestMethod("GET");
        // Add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando petición GET a: " + url);
        System.out.println("Código de respuesta: " + responseCode);

        String response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new String();
            while ((inputLine = in.readLine()) != null) {
                response = response.concat(inputLine);
            }
        }
        // Imprimimos el resultado
        System.out.println(response);
        
        return response;
    }
}
