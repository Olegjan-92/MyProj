package ru.myProj.dao;

import ru.myProj.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
}
