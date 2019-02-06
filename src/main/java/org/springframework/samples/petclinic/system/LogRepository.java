package org.springframework.samples.petclinic.system;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando
 */

public interface LogRepository extends Repository<Log, Integer>{
    
    @Query("SELECT informacion FROM Log log")
    @Transactional(readOnly = true)
    Collection<Log> getAll();
    
    void save(Log log);
}
