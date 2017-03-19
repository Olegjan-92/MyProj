package ru.myProj.service;

import ru.myProj.dao.EmployeeDao;
import ru.myProj.dao.UserDao;
import ru.myProj.domain.Employee;
import ru.myProj.domain.Skill;
import ru.myProj.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private EmployeeDao employeeDao;

    public UserServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Map<User, List<Skill>> getAllUsersWithSkills(){
        List<User> users = (List<User>) userDao.findAll();
        Map<User, List<Skill>> userAndSkills = new HashMap<>();
        for(User user : users) {
            userAndSkills.put(user, user.getEmployee().getSkills());
        }
        return userAndSkills;
    }

    @Override
    public Map<String, List<Skill>> getCityWithSkills() {
        List<Employee> employees = (List<Employee>) employeeDao.findAll();
        Map<String, List<Skill>> employeeAndSkils = new HashMap<>();
        for (Employee employee: employees){
            employeeAndSkils.put(employee.getCity(), employee.getSkills());
        }
        return employeeAndSkils;
    }
}
