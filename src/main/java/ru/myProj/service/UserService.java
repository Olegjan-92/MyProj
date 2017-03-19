package ru.myProj.service;

import ru.myProj.domain.Skill;
import ru.myProj.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by olegp_000 on 15.03.2017.
 */
public interface UserService {
    Map<User, List<Skill>> getAllUsersWithSkills();
    Map<String, List<Skill>> getCityWithSkills();
}
