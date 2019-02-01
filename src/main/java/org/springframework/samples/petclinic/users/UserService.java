/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 30/01/2019
 */

package org.springframework.samples.petclinic.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.authority.Authority;
import org.springframework.samples.petclinic.authority.AuthorityRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author David Pérez S.
 */
@Service("userService")
public class UserService {
    
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setAuthority("USER");
        userRepository.save(user);
        System.out.println("\n\nNombre de 'user' de la autoridad: "+authority.getUsername()+"\n");
        authorityRepository.save(authority);
    }
}
