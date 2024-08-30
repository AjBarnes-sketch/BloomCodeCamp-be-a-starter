package com.hcc.entities;

import javax.persistence.*;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status; // "Working on", "Submitted", "Rejected", "Completed"
    private Integer number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    @ManyToOne
    private User user;
    @ManyToOne
    private User codeReviewer;

    // Constructor
    public Assignment(Long id, String status, Integer number, String githubUrl, String branch,
                      String reviewVideoUrl, User user, User codeReviewer) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.reviewVideoUrl = reviewVideoUrl;
        this.user = user;
        this.codeReviewer = codeReviewer;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for number
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    // Getter and Setter for githubUrl
    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    // Getter and Setter for branch
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // Getter and Setter for reviewVideoUrl
    public String getReviewVideoUrl() {
        return reviewVideoUrl;
    }

    public void setReviewVideoUrl(String reviewVideoUrl) {
        this.reviewVideoUrl = reviewVideoUrl;
    }

    // Getter and Setter for user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and Setter for codeReviewer
    public User getCodeReviewer() {
        return codeReviewer;
    }

    public void setCodeReviewer(User codeReviewer) {
        this.codeReviewer = codeReviewer;
    }

    // Method to update assignment status
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Assignment status updated to: " + newStatus);
    }

    // Method to add a review video URL
    public void addReviewVideo(String videoUrl) {
        this.reviewVideoUrl = videoUrl;
        System.out.println("Review video added: " + videoUrl);
    }

    public void editSubmission(String githubUrl, String branch) {
    }

    public String getTitle() {
        return getTitle();
    }
}

