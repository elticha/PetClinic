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

/**
 * @author David Pérez S.
 */
public class CodigoPostal {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    private void sendGet() throws Exception {

        System.out.println("Prueba 1 - Solicitud de Código Postal en México");
        String url = "https://api-codigos-postales.herokuapp.com/v2/codigo_postal/29100";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando petición GET a: " + url);
        System.out.println("Código de respuesta: " + responseCode);

        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        // Imprimimos el resultado
        System.out.println(response.toString());
    }
}
