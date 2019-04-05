package com.fb.logparser.model;

import org.springframework.data.repository.CrudRepository;

public interface LogRepository  extends CrudRepository<LogUnit, Long> {
    LogUnit findById(int id);
}
