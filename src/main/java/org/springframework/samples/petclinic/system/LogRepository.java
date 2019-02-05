package org.springframework.samples.petclinic.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Fernando
 */

public interface LogRepository extends JpaRepository<Log, Integer>{
    
}
