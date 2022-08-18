package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;
import java.util.Locale;

public class Cadastro {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);

		Employee emp = new Employee();

		List<Employee> list = new ArrayList<>();

		char repeat = 's';
		int control = 0;
		int finalId = 0;

		System.out.print("Quantos funcionarios deseja cadastrar ? : ");
		int employeesQty = sc.nextInt();
		while (employeesQty <= 0) {
			System.out.print("Número invalido de funcionarios, digite um número maior que 0 : ");
			employeesQty = sc.nextInt();
		}

		for (int i = 0; i < employeesQty; i++) {
			System.out.println("--------------------------------------");
			System.out.printf("Digite o NOME do funcionario #%d : ", (i + 1));
			String name = scString.nextLine();
			System.out.printf("Digite a IDADE do funcionario #%d : ", (i + 1));
			int age = sc.nextInt();
			System.out.printf("Digite o ID do funcionario #%d : ", (i + 1));
			int id = sc.nextInt();
			emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
			while (emp != null) {
				System.out.print("Id em uso, digite um novo ID :");
				int newId = sc.nextInt();
				emp = list.stream().filter(x -> x.getId() == newId).findFirst().orElse(null);
				control = 1;
				finalId = newId;
			}
			System.out.printf("Digite o SALARIO do funcionario #%d : ", (i + 1));
			double salary = sc.nextDouble();
			if (control == 1) {
				list.add(new Employee(name, age, finalId, salary));
			} else {
				list.add(new Employee(name, age, id, salary));
			}
			control = 0;
		}
		while (repeat != 'n'){
			System.out.println("--------------------------------------");
			System.out.println(list);
			System.out.print("Qual funcionario deseja selecionar ? (ID) : ");
			int empSelected = sc.nextInt();
			emp = list.stream().filter(x -> x.getId() == empSelected).findFirst().orElse(null);
			while (emp == null) {
				System.out.print("ID Invalido, digite um valido : ");
				int newSearch = sc.nextInt();
				emp = list.stream().filter(x -> x.getId() == newSearch).findFirst().orElse(null);
			}
			System.out.print("Digite o novo salario dessa pessoa : ");
			emp.changeSalary(sc.nextDouble());
			System.out.println(list.stream().filter(x -> x.getId() == empSelected).findFirst().orElse(null));
			System.out.println("--------------------------------------");
			System.out.println(list);
			System.out.println("Deseja alterar mais algum salario ? (s ou n) : ");
			repeat = scString.nextLine().charAt(0);
		}
		System.out.println("FIM");
		sc.close();
		scString.close();
	}
}
