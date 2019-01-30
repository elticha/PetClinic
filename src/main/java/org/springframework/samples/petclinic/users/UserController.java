/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2018 
 */

package org.springframework.samples.petclinic.users;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author David Pérez S.
 */
@Controller
public class UserController {
    
    private final UserRepository users;
    
    public UserController(UserRepository users) {
        this.users = users;
    }
    
    @GetMapping("/users/report")
    public String showUserList(Map<String, Object> model) {
        Users users = new Users();
        users.getUserList().addAll(this.users.findById2(999999));
        model.put("users", users);
       // model.put("users", this.users.allUser());
        return "users/Users";
    }    
}
