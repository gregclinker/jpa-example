package hello;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foreName;
    private String surName;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String foreName, String surName) {
        this.foreName = foreName;
        this.surName = surName;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", foreName='" + foreName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
