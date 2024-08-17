package com.discovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discovery.dto.ApiResponse;
import com.discovery.dto.FineDTO;
import com.discovery.service.FineServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/fines")
public class FineController {

    @Autowired
    private FineServiceImpl fineService;

    // Endpoint to get all fines
    @GetMapping
    @Operation(description = "Get list of all fines")
    public ResponseEntity<?> listAllFines() {
        List<FineDTO> list = fineService.getAllFines();
        
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("No fines available"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // Endpoint to calculate fine based on borrowId
    @PostMapping("/calculate/{borrowId}")
    @Operation(description = "Calculate fine for a specific borrow")
    public ResponseEntity<?> calculateFine(@PathVariable Long borrowId) {
        try {
            FineDTO fineDTO = fineService.calculateFine(borrowId);
            return ResponseEntity.status(HttpStatus.OK).body(fineDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }
    
    
    // Endpoint to mark a fine as paid
    @PatchMapping("/pay/{fineId}")
    @Operation(description = "Mark a fine as paid")
    public ResponseEntity<?> payFine(@PathVariable Long fineId) {
        try {
            FineDTO updatedFine = fineService.markFineAsPaid(fineId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedFine);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
        }
    }
}
