package modelo;

import jakarta.persistence.*;



@Entity
@Table(name = "employees")
public class Employees {
    @Id
    private int emp_no;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String role;
    private double salary;


    @ManyToOne
    @JoinColumn(name = "deptno")
    private Departments department;


    public Employees() {}


    public Employees(int emp_no, String first_name, String last_name, String birth_date, String role, double salary, Departments department) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.role = role;
        this.salary = salary;
        this.department = department;
    }

    public int getEmp_no() {
        return emp_no;
    }


    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
}
