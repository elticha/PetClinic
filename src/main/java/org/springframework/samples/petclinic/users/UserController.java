/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2019
 */
package org.springframework.samples.petclinic.users;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.otros.ComprobadorCodigoPostal;
import org.springframework.samples.petclinic.system.Log;
import org.springframework.samples.petclinic.system.LogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author David Pérez S.
 */
@Controller
public class UserController {

    private static final String VIEW_USERS = "users/createOrUpdateUserForm";
    private final UserRepository users;
    
    @Autowired
    private LogRepository lr;
    
    @Autowired
    private UserService userService;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/user/new")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return VIEW_USERS;
    }
    
    @GetMapping("/logs")
    public String getLogs(Map<String,Object> model){
        System.out.println("Obteniendo logs....");
        Collection<Log> logs = this.lr.getAll();
        model.put("logs",logs);
        return "logs/reports";
    }

    @PostMapping("/user/new")
    public String processCreationForm(@Valid User user, BindingResult bindingResult) throws Exception {
        User userExists = userService.findUserByUsername(user.getUsername());
        ComprobadorCodigoPostal cp = new ComprobadorCodigoPostal();
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "Ya existe un usuario con este nombre");
        }
        if (!cp.comprobarExisteCodigoPostal(user.getCodigopostal())) {
            bindingResult.rejectValue("codigopostal", "error.user", "Este código postal no existe");
        }
        if(!cp.getCiudad().contains(user.getCiudad())) {
            bindingResult.rejectValue("ciudad", "error.user", "Esta ciudad no coincide con el código postal introducido");
        }
        if(!cp.getEstado().contains(user.getEstado())) {
            bindingResult.rejectValue("estado", "error.user", "Este estado no coincide con el código postal introducido");
        }        
        if (bindingResult.hasErrors()) {
            return VIEW_USERS;
        } else {
            user.setEnabled(1);
            this.userService.saveUser(user);
            return "redirect:/user/find";
        }
    }

    @GetMapping("/user/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("user", new User());
        return "users/findUsers";
    }

    @GetMapping("/user")
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /users to return all records
        if (user.getUsername() == null) {
            user.setUsername(""); // empty string signifies broadest possible search
        }

        // find users by username
        Collection<User> results = this.users.findAllByUsername(user.getUsername());
        if (results.isEmpty()) {
            // no users found
            result.rejectValue("username", "notFound", "not found");
            return "users/findUsers";
        } else if (results.size() == 1) {
            // 1 user found
            user = results.iterator().next();
            return "redirect:/user/" + user.getId();
        } else {
            // multiple users found
            model.put("selections", results);
            return "users/usersList";
        }
    }

    @GetMapping("/user2")
    public String processFindForm2(User user, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (user.getUsername() == null) {
            user.setUsername(""); // empty string signifies broadest possible search
        }

        // find users by username
        Collection<User> results = this.users.findAllByUsername(user.getUsername());
        if (results.isEmpty()) {
            // no users found
            result.rejectValue("username", "notFound", "not found");
            return "users/findUsers";
        } else if (results.size() == 1) {
            // 1 user found
            user = results.iterator().next();
            return "redirect:/user/" + user.getId();
        } else {
            // multiple users found
            model.put("selections", results);
            return "users/usersList2";
        }
    }

    @GetMapping("/user/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.users.findById3(userId);
        model.addAttribute(user);
        return VIEW_USERS;
    }

    @PostMapping("/user/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult bindingResult, @PathVariable("userId") int userId) throws Exception {
        ComprobadorCodigoPostal cp = new ComprobadorCodigoPostal();
       
        if (!cp.comprobarExisteCodigoPostal(user.getCodigopostal())) {
            bindingResult.rejectValue("codigopostal", "error.user", "Este código postal no existe");
        }
        if(!cp.getCiudad().contains(user.getCiudad())) {
            bindingResult.rejectValue("ciudad", "error.user", "Esta ciudad no coincide con el código postal introducido");
        }
        if(!cp.getEstado().contains(user.getEstado())) {
            bindingResult.rejectValue("estado", "error.user", "Este estado no coincide con el código postal introducido");
        }        
        if (bindingResult.hasErrors()) {
            return VIEW_USERS;
        } else {
            user.setId(userId);
            System.out.println("usuario enabled: "+user.getEnabled());
            user.setEnabled(user.getEnabled());
            this.userService.saveUser(user);
            return "redirect:/user/{userId}";
        }
    }

    @GetMapping("/user/{userId}")
    public ModelAndView showUser(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.users.findById3(userId));
        return mav;
    }

    @GetMapping("/user/{userId}/delete")
    public String deleteUser(User user, BindingResult result, @PathVariable("userId") int userId) {
        user = this.users.findById3(userId);
        this.users.delete(user);
        return "redirect:/user?username=";
    }

    // Reporte de usuarios **********
    
    @GetMapping("/users/report")
    public String showUserList(Map<String, Object> model) {
        Users usersitos = new Users();
        usersitos.getUserList().addAll(this.users.findById2(999999));
        model.put("users", usersitos);
        // model.put("users", this.users.allUser());
        return "users/Users";
    }
}
