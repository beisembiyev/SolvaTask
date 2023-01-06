package com.example.solva.Controllers;

import com.example.solva.Models.DTO.LimitDTO;
import com.example.solva.Services.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/limits")
@Tag(name = "Limits", description = "Limit API")
public class LimitController {

    private final LimitService limitService;

    @GetMapping("/all")
    @Operation(summary = "Method to get all limits")
    public ResponseEntity<List<LimitDTO>> getAllLimits(){
        return ResponseEntity.ok(limitService.getAllLimits());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Method to get limit by id")
    public ResponseEntity<LimitDTO> getLimit(
            @Parameter(description = "Limit id") @PathVariable Long id) {
        return ResponseEntity.ok(limitService.getLimit(id));
    }

    @PostMapping()
    @Operation(summary = "Method to create limit")
    public ResponseEntity<LimitDTO> createLimit(
            @Parameter(description = "Request body of limit") @RequestBody LimitDTO limitDTO) {
        return ResponseEntity.ok(limitService.createLimit(limitDTO));
    }

    @PutMapping()
    @Operation(summary = "Method to update limit by id")
    public ResponseEntity<LimitDTO> updateLimit(
            @Parameter(description = "Request body of limit") @RequestBody LimitDTO limitDTO) {
        return ResponseEntity.ok(limitService.updateLimit(limitDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Method to delete limit by id")
    public ResponseEntity<?> deleteLimit(
            @Parameter(description = "Limit id") @PathVariable Long id) {
        limitService.deleteLimit(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
