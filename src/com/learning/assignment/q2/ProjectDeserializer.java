package com.learning.assignment.q2;

import java.io.*;
import java.util.*;
public class ProjectDeserializer {
	public static void deserializeData() {
		try {
			System.out.println("Deserialize Called by Consumer");
			System.out.flush();
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("project_data.ser"))) {
				@SuppressWarnings("unchecked")
				Map<Project, List<Employee>> deserializedMap = (Map<Project, List<Employee>>) ois.readObject();
				System.out.println("DeSerialized Data");
				for (Map.Entry<Project, List<Employee>> entry : deserializedMap.entrySet()) {
					System.out.println(entry.getKey() + "=" + entry.getValue());
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}