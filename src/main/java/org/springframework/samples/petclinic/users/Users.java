/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2018 
 */

package org.springframework.samples.petclinic.users;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author David Pérez S.
 */
@XmlRootElement
public class Users {
    private List<User> users;

    @XmlElement
    public List<User> getUserList() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }
}
