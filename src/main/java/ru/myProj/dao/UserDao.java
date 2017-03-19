package ru.myProj.dao;

import ru.myProj.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer>{
}
