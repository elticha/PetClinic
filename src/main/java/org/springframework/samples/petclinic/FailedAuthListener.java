package org.springframework.samples.petclinic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.samples.petclinic.system.Log;
import org.springframework.samples.petclinic.system.LogRepository;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Fernando
 */
@Component
public class FailedAuthListener {
    
    @Autowired
    private LogRepository lr;
    
    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent e)
    {
        String username = (String) e.getAuthentication().getPrincipal();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try
        {
            System.out.println("Guardando...");
            this.lr.save(new Log("[ Error de credenciales ] El usuario " + username + " ha intentado acceder a las " + dtf.format(now)));
        }
        catch(Exception er)
        {
            er.printStackTrace();
        }
    }
}
