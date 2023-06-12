package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {
    @Id
    private int deptno;
    private String deptname;
    private String location;

    @OneToMany(mappedBy = "department")
    private List<Employees> employees;


    public Departments() {}


    public Departments(int deptno, String deptname, String location, List<Employees> employees) {
        this.deptno = deptno;
        this.deptname = deptname;
        this.location = location;
        this.employees = employees;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }
}
