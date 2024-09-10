package com.hcc.controllers;

import com.hcc.dto.AssignmentResponseDto;
import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @CrossOrigin
    @GetMapping
    public List<AssignmentResponseDto> getAssignmentsByUser(@AuthenticationPrincipal User user) {
        return assignmentService.getAssignmentsByUserId(user);
    }

    @GetMapping("/{id}")
    public AssignmentResponseDto getAssignmentById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return assignmentService.getAssignmentById(id);
    }

    @PutMapping("/{id}")
    public AssignmentResponseDto updateAssignment(@PathVariable Long id,
                                                  @RequestBody Assignment assignmentDetails, @AuthenticationPrincipal User user) {
        return assignmentService.updateAssignment(id, assignmentDetails);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment, @AuthenticationPrincipal User user) {
        assignment.setUser(user);
        return ResponseEntity.ok(assignmentService.createAssignment(assignment));
    }

}