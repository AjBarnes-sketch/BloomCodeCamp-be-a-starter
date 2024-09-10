package com.hcc.services;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<AssignmentResponseDto> getAssignmentsByUserId(User user) {
        List<Assignment> assignments = assignmentRepository.findAllByUser(user);

        return assignments.stream()
                .map(AssignmentResponseDto::new)
                .collect(Collectors.toList());
    }

    public AssignmentResponseDto getAssignmentById(Long id) {
        return new AssignmentResponseDto(assignmentRepository.findById(id).get());
    }

    public Assignment createAssignment(Assignment assignment) {
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return savedAssignment;
    }

    public AssignmentResponseDto updateAssignment(Long id, Assignment assignmentDetails) {
        Assignment assignment = assignmentRepository.findById(id).get();
        assignment.setStatus(assignmentDetails.getStatus());
        assignment.setNumber(assignmentDetails.getNumber());
        assignment.setGithubUrl(assignmentDetails.getGithubUrl());
        assignment.setBranch(assignmentDetails.getBranch());
        assignment.setReviewVideoUrl(assignmentDetails.getReviewVideoUrl());
        assignment.setUser(assignmentDetails.getUser());
        assignment.setCodeReviewer(assignmentDetails.getCodeReviewer());

        return new AssignmentResponseDto(assignmentRepository.save(assignment));
    }
}