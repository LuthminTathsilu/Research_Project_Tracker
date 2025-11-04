package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.MilestoneDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/milestone")
public class MilestoneController {


    private final List<MilestoneDto> milestoneStore = new ArrayList<>();


    // List milestones for a project

    @GetMapping(value = "/projects/{projectId}/milestones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MilestoneDto>> getMilestonesByProject(@PathVariable String projectId) {
        List<MilestoneDto> filteredMilestones = new ArrayList<>();
        for (MilestoneDto milestone : milestoneStore) {
            if (milestone.getProject() != null &&
                    milestone.getProject().getId().equals(projectId)) {
                filteredMilestones.add(milestone);
            }
        }
        return ResponseEntity.ok(filteredMilestones);
    }


    //  Add a new milestone

    @PostMapping(value = "/projects/{projectId}/milestones",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MilestoneDto> addMilestone(
            @PathVariable String projectId,
            @RequestBody MilestoneDto milestoneDto
    ) {
        // Link milestone to project
        milestoneDto.setProject(new ProjectDto(projectId, null, null, null, null, null, null, null, null, null));

        // Add to mock list
        milestoneStore.add(milestoneDto);

        return new ResponseEntity<>(milestoneDto, HttpStatus.CREATED);
    }


    //  Update an existing milestone

    @PatchMapping(value = "/milestones/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMilestone(
            @PathVariable String id,
            @RequestBody MilestoneDto updatedMilestone
    ) {
        for (int i = 0; i < milestoneStore.size(); i++) {
            MilestoneDto existing = milestoneStore.get(i);
            if (existing.getId().equals(id)) {
                // Update fields
                existing.setTitle(updatedMilestone.getTitle());
                existing.setDescription(updatedMilestone.getDescription());
                existing.setDueDate(updatedMilestone.getDueDate());
                existing.setIsCompleted(updatedMilestone.getIsCompleted());
                existing.setCreatedBy(updatedMilestone.getCreatedBy());

                milestoneStore.set(i, existing);
                return ResponseEntity.ok(existing);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Milestone with ID " + id + " not found.");
    }

    //  Delete milestone

    @DeleteMapping("/milestones/{id}")
    public ResponseEntity<String> deleteMilestone(@PathVariable String id) {
        boolean removed = milestoneStore.removeIf(m -> m.getId().equals(id));
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Milestone with ID " + id + " not found.");
        }
        return ResponseEntity.ok("Milestone " + id + " deleted successfully.");
    }
}
