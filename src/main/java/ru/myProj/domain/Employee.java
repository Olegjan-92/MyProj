package ru.myProj.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Table(name = "employes")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String full_name;
    private String short_name;
    private int age;
    private String city;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "employee_id")
    private List<User> users;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "employee_id")
    private List<Skill> skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (age != employee.age) return false;
        if (full_name != null ? !full_name.equals(employee.full_name) : employee.full_name != null) return false;
        if (short_name != null ? !short_name.equals(employee.short_name) : employee.short_name != null) return false;
        if (city != null ? !city.equals(employee.city) : employee.city != null) return false;
        if (users != null ? !users.equals(employee.users) : employee.users != null) return false;
        return skills != null ? skills.equals(employee.skills) : employee.skills == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (full_name != null ? full_name.hashCode() : 0);
        result = 31 * result + (short_name != null ? short_name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        return result;
    }
}
