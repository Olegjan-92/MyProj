package ru.myProj.domain;

import javax.persistence.*;

/**
 * Created by olegp_000 on 15.03.2017.
 */
@Table(name = "skills")
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(
            name = "employee_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "employee_id"))
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        if (name != null ? !name.equals(skill.name) : skill.name != null) return false;
        if (description != null ? !description.equals(skill.description) : skill.description != null) return false;
        return employee != null ? employee.equals(skill.employee) : skill.employee == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }
}
