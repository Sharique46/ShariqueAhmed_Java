package com.learning.assignment.q2;

import java.io.*;
import java.util.*;

public class ProjectSerializer {
	public static void serializeData(Map<Project, List<Employee>> hMap) {
		System.out.println("Serialize called by Producer");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("project_data.ser"))) {
			oos.writeObject(hMap);
			System.out.println("Serialized Data :");
			for (Map.Entry<Project, List<Employee>> entry : hMap.entrySet()) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}