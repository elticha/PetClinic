/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Mantenimiento de Sistemas
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 31/01/2019
 */

package org.springframework.samples.petclinic.authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David Pérez S.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    // XD
}
