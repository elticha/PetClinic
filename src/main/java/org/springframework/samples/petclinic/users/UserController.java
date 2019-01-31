/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2019
 */

package org.springframework.samples.petclinic.users;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author David Pérez S.
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/Registro");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("users/Registro");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "El usuario ha sido registrado exitosamente");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("users/Registro");

        }
        return modelAndView;
    }
}
