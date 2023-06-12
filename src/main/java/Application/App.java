package Application;

import java.util.List;
import java.util.Scanner;

import modelo.Departments;
import modelo.Employees;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class App {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("===========================================");
		System.out.println("MANTENIMENT EMPLEATS");
		System.out.println("===========================================");
		System.out.println("Escolliu quina acció voleu fer:");
		System.out.println("1. Consultar empleats");
		System.out.println("2. Inserir nou empleat");
		System.out.println("===========================================");
		int option = scanner.nextInt();
		scanner.nextLine(); // Consume el salto de línea después de leer el entero

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			switch (option) {
				case 1:
					// Consultar empleats
					System.out.println("Introduir el nom de departament:");
					String departmentName = scanner.nextLine();

					// Obtener el número de departamento
					Query<Departments> query = session.createQuery("FROM Departments WHERE deptname = :deptName", Departments.class);
					query.setParameter("deptName", departmentName);
					Departments department = query.uniqueResult();

					if (department != null) {
						int departmentNumber = department.getDeptno();

						// Mostrar los empleados del departamento
						Query<Employees> employeesQuery = session.createQuery("FROM Employees WHERE department.deptno = :deptNo", Employees.class);
						employeesQuery.setParameter("deptNo", departmentNumber);
						List<Employees> employees = employeesQuery.getResultList();

						// Imprimir los empleados
						System.out.println("Emp_no\tNom\tCognoms\tData_naix\tRol\tSalari");
						System.out.println("======\t====\t=======\t========\t===\t=====");
						for (Employees employee : employees) {
							System.out.println(employee.getEmp_no() + "\t" + employee.getFirst_name() + "\t" + employee.getLast_name() + "\t" +
									employee.getBirth_date() + "\t" + employee.getRole() + "\t" + employee.getSalary());
						}
					} else {
						System.out.println("El departament no existeix.");
					}
					break;

				case 2:
					// Inserir nou empleat
					System.out.println("===========================================");
					System.out.println("NOU EMPLEAT");
					System.out.println("===========================================");
					System.out.println("Nom:");
					String firstName = scanner.nextLine();
					System.out.println("Cognom:");
					String lastName = scanner.nextLine();
					System.out.println("Data de naixement:");
					String birthDate = scanner.nextLine();
					System.out.println("Gènere:");
					String gender = scanner.nextLine();
					System.out.println("Data d'incorporació:");
					String hireDate = scanner.nextLine();
					System.out.println("Salari:");
					double salary = scanner.nextDouble();
					scanner.nextLine(); // Consume el salto de línea después de leer el double
					System.out.println("Rol:");
					String role = scanner.nextLine();
					System.out.println("Departament:");
					int departmentNumber = scanner.nextInt();
					scanner.nextLine(); // Consume el salto de línea después de leer el entero

					// Verificar si el departamento existe
					Query<Departments> departmentQuery = session.createQuery("FROM Departments WHERE deptno = :deptNo", Departments.class);
					departmentQuery.setParameter("deptNo", departmentNumber);
					Departments existingDepartment = departmentQuery.uniqueResult();

					if (existingDepartment != null) {
						// Crear un nuevo objeto Employees con los datos proporcionados
						Employees newEmployee = new Employees();
						newEmployee.setFirst_name(firstName);
						newEmployee.setLast_name(lastName);
						newEmployee.setBirth_date(birthDate);
						newEmployee.setRole(role);
						newEmployee.setSalary(salary);
						newEmployee.setDepartment(existingDepartment);

						// Guardar el nuevo empleado en la base de datos
						session.save(newEmployee);
						System.out.println("Nou empleat creat correctament.");
					} else {
						System.out.println("El departament no existeix.");
					}
					break;

				default:
					System.out.println("Opció invàlida.");
					break;
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

