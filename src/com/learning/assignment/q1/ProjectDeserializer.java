package com.learning.assignment.q1;

import java.io.*;
import java.util.*;

public class ProjectDeserializer {
	public void deserializeAndDisplay() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("project_data.ser"))) {
			Map<Project, List<Employee>> deserializedMap = (Map<Project, List<Employee>>) ois.readObject();
			System.out.println("DeSerialized Data:");
			for (Map.Entry<Project, List<Employee>> entry : deserializedMap.entrySet()) {
				Project project = entry.getKey();
				List<Employee> employees = entry.getValue();
				System.out.println("The Project");
				System.out.println(project);
				System.out.println("Has the following Employees");
				System.out.println("Employees .............");
				for (Employee employee : employees) {
					System.out.println(employee);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
