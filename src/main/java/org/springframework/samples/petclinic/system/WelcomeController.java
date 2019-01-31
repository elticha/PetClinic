package org.springframework.samples.petclinic.system;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
    
    @Autowired
    private LogRepository lr;

    @GetMapping("/")
    public String welcome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if(authentication.isAuthenticated())
        {
            this.lr.save(new Log("Se ha conectado el usuario " + authentication.getName() + " a las " + dtf.format(now)));
        }
        return "welcome";
    }
}