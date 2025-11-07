package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.MilestoneDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/milestone")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    // List milestones for a project
    @GetMapping(value = "/projects/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MilestoneDto>> getMilestonesByProject(@PathVariable String projectId) {
        List<MilestoneDto> milestones = milestoneService.getMilestonesByProject(projectId);
        return ResponseEntity.ok(milestones);
    }

    // Add a new milestone
    @PostMapping(value = "/projects/{projectId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MilestoneDto> addMilestone(
            @PathVariable String projectId,
            @RequestBody MilestoneDto milestoneDto
    ) {
        MilestoneDto created = milestoneService.addMilestone(projectId, milestoneDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Update an existing milestone
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMilestone(
            @PathVariable String id,
            @RequestBody MilestoneDto updatedMilestone
    ) {
        MilestoneDto updated = milestoneService.updateMilestone(id, updatedMilestone);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Milestone with ID " + id + " not found.");
        }
    }

    // Delete milestone
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMilestone(@PathVariable String id) {
        boolean deleted = milestoneService.deleteMilestone(id);
        if (deleted) {
            return ResponseEntity.ok("Milestone " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Milestone with ID " + id + " not found.");
        }
    }
}
