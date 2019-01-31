package org.springframework.samples.petclinic.system;

import org.springframework.data.repository.Repository;

/**
 *
 * @author Fernando
 */

public interface LogRepository extends Repository<Log, Integer>{
    void save(Log log);
}
