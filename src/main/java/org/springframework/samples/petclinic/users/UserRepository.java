/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 29/01/2019
 */
package org.springframework.samples.petclinic.users;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Pérez S.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT user FROM User user WHERE user.id <=:id")
    @Transactional(readOnly = true)
    Collection<User> findById2(@Param("id") Integer id);
}
