package com.learning.assignment.q1;

import java.io.Serializable;

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
