package com.learning.assignment.q2;

import java.io.*;
import java.util.*;

class Project implements Serializable {
	private String projectId;
	private String projectName;
	private int projectDuration;

	public Project(String projectId, String projectName, int projectDuration) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDuration = projectDuration;
	}

	@Override
	public String toString() {
		return "Project [projectCode=" + projectId + ", projectName=" + projectName + ", projectStrength="
				+ projectDuration + "]";
	}
}

class Employee implements Serializable {
	private String employeeId;
	private String employeeName;
	private String phoneNumber;
	private String address;
	private int salary;

	public Employee(String employeeId, String employeeName, String phoneNumber, String address, int salary) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePhone="
				+ phoneNumber + ", employeeAddress=" + address + ", employeeSalary=" + salary + "]";
	}
}

class Thread1 {
	HashMap<Project, ArrayList<Employee>> hMap;

	Thread1(HashMap<Project, ArrayList<Employee>> hMap) {
		this.hMap = hMap;
	}

	synchronized void serializeData() {
		System.out.println("Serialize called by Producer");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("project_data.ser"))) {
			oos.writeObject(hMap);
			System.out.println("Serialized Data :");
			for (Map.Entry<Project, ArrayList<Employee>> entry : hMap.entrySet()) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
			}
			notify();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	synchronized void deserializeData() {
		try {
			wait();
			System.out.println("Deserialize Called by Consumer");
			System.out.flush();
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("project_data.ser"))) {
				@SuppressWarnings("unchecked")
				HashMap<Project, ArrayList<Employee>> deserializedMap = (HashMap<Project, ArrayList<Employee>>) ois
						.readObject();
				System.out.println("DeSerialized Data");
				for (Map.Entry<Project, ArrayList<Employee>> entry : deserializedMap.entrySet()) {
					System.out.println(entry.getKey() + "=" + entry.getValue());
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Producer extends Thread {
	Thread1 obj;

	Producer(Thread1 obj) {
		this.obj = obj;
		start();
	}

	public void run() {
		obj.serializeData();
	}
}

class Consumer extends Thread {
	Thread1 obj;

	Consumer(Thread1 obj) {
		this.obj = obj;
		start();
	}

	public void run() {
		obj.deserializeData();
	}
}

class Driver {
	public static void main(String[] args) {
		HashMap<Project, ArrayList<Employee>> hMap1 = new HashMap<>();
		HashMap<Project, ArrayList<Employee>> hMap2 = new HashMap<>();
		HashMap<Project, ArrayList<Employee>> hMap3 = new HashMap<>();
		ArrayList<Employee> eList1 = new ArrayList<>();
		ArrayList<Employee> eList2 = new ArrayList<>();
		ArrayList<Employee> eList3 = new ArrayList<>();
		ArrayList<Employee> eList4 = new ArrayList<>();
		ArrayList<Employee> eList5 = new ArrayList<>();
		ArrayList<Employee> eList6 = new ArrayList<>();

		Employee e1 = new Employee("E001", "Harsha", "9383993933", "RTNagar", 1000);
		Employee e2 = new Employee("E002", "Harish", "9354693933", "Jayanagar", 2000);
		Employee e3 = new Employee("E003", "Meenal", "9383976833", "Malleswaram", 1500);
		Employee e4 = new Employee("E004", "Sundar", "9334593933", "Vijayanagar", 3000);
		Employee e5 = new Employee("E005", "Suman", "9383678933", "Indiranagar", 2000);
		Employee e6 = new Employee("E006", "Suma", "9385493933", "KRPuram", 1750);
		Employee e7 = new Employee("E007", "Chitra", "9383978933", "Koramangala", 4000);
		Employee e8 = new Employee("E008", "Suraj", "9383992133", "Malleswaram", 3000);
		Employee e9 = new Employee("E009", "Manu", "9345193933", "RTNagar", 2000);
		Employee e10 = new Employee("E010", "Kiran", "9383975673", "Koramangala", 4000);
		Employee e11 = new Employee("E011", "Mrinal", "9383992789", "Malleswaram", 3000);
		Employee e12 = new Employee("E012", "Mahesh", "9345193763", "RTNagar", 2000);

		eList1.add(e1);
		eList1.add(e2);
		eList2.add(e3);
		eList2.add(e4);
		eList3.add(e5);
		eList3.add(e6);
		eList4.add(e7);
		eList4.add(e8);
		eList5.add(e9);
		eList5.add(e10);
		eList6.add(e11);
		eList6.add(e12);

		Project project1 = new Project("P1", "Music Synthesizer", 23);
		Project project2 = new Project("P2", "Vehicle Movement Tracker", 13);
		Project project3 = new Project("P3", "Liquid Viscosity Finder", 15);
		Project project4 = new Project("P4", "InsuranceTool", 23);
		Project project5 = new Project("P5", "BankingTool", 13);
		Project project6 = new Project("P6", "PayrollTool", 15);

		hMap1.put(project1, eList1);
		hMap1.put(project2, eList2);
		hMap2.put(project3, eList3);
		hMap2.put(project4, eList4);
		hMap3.put(project5, eList5);
		hMap3.put(project6, eList6);

		Thread1 obj1 = new Thread1(hMap1);
		Producer p1 = new Producer(obj1);
		Consumer c1 = new Consumer(obj1);

		Thread1 obj2 = new Thread1(hMap2);
		Producer p2 = new Producer(obj2);
		Consumer c2 = new Consumer(obj2);

		Thread1 obj3 = new Thread1(hMap3);
		Producer p3 = new Producer(obj3);
		Consumer c3 = new Consumer(obj3);
	}
}