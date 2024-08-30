package com.hcc.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Learner extends User {
    private List<Assignment> assignments;

    public Learner(Long id, Date cohortStartDate, String username, String password, List<String> authorities) {
        super(id, cohortStartDate, username, password, authorities);
        this.assignments = new ArrayList<>();
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void viewDashboard() {
        System.out.println("Dashboard for Learner: " + getUsername());
        for (Assignment assignment : assignments) {
            System.out.println("Assignment " + assignment.getNumber() + ": " + assignment.getTitle() + " - Status: " + assignment.getStatus());
        }
    }

    public void createAssignment(Long id, Integer number, String githubUrl, String branch) {
        Assignment newAssignment = new Assignment(id, "Working on", number, githubUrl, branch, null, this, null);
        assignments.add(newAssignment);
        System.out.println("New assignment created: " + newAssignment.getTitle());
    }


    public void editAssignment(int assignmentIndex, String githubUrl, String branch) {
        if (assignmentIndex >= 0 && assignmentIndex < assignments.size()) {
            Assignment assignment = assignments.get(assignmentIndex);
            if ("Rejected".equals(assignment.getStatus())) {
                assignment.setGithubUrl(githubUrl);
                assignment.setBranch(branch);
                assignment.setStatus("Resubmitted");
                System.out.println("Assignment resubmitted: " + assignment.getTitle());
            } else {
                System.out.println("Cannot edit this assignment. Current status: " + assignment.getStatus());
            }
        } else {
            System.out.println("Invalid assignment index.");
        }
    }


    public void viewCompletedAssignment(int assignmentIndex) {
        if (assignmentIndex >= 0 && assignmentIndex < assignments.size()) {
            Assignment assignment = assignments.get(assignmentIndex);
            if ("Completed".equals(assignment.getStatus())) {
                System.out.println("Viewing review video for assignment: " + assignment.getTitle());
                System.out.println("Review Video URL: " + assignment.getReviewVideoUrl());
            } else {
                System.out.println("This assignment is not completed yet.");
            }
        } else {
            System.out.println("Invalid assignment index.");
        }
    }
}
