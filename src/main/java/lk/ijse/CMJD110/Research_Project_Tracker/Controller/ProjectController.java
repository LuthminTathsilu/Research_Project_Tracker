package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    // Temporary in-memory project list
    private final List<ProjectDto> projectStore = new ArrayList<>();


    //  Get all projects

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectStore);
    }


    //  Get project by ID

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjectById(@PathVariable String id) {
        for (ProjectDto project : projectStore) {
            if (project.getId().equals(id)) {
                return ResponseEntity.ok(project);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }


    // Create new project (PI/Admin only — simulated)

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        projectDto.setCreatedAt(LocalDateTime.now());
        projectDto.setUpdatedAt(LocalDateTime.now());

        projectStore.add(projectDto);
        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }


    //  Update full project


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable String id, @RequestBody ProjectDto updatedProject) {
        for (int i = 0; i < projectStore.size(); i++) {
            ProjectDto existing = projectStore.get(i);
            if (existing.getId().equals(id)) {

                existing.setTitle(updatedProject.getTitle());
                existing.setSummary(updatedProject.getSummary());
                existing.setTags(updatedProject.getTags());
                existing.setPi(updatedProject.getPi());
                existing.setStatus(updatedProject.getStatus());
                existing.setStartDate(updatedProject.getStartDate());
                existing.setEndDate(updatedProject.getEndDate());
                existing.setUpdatedAt(LocalDateTime.now());

                projectStore.set(i, existing);
                return ResponseEntity.ok(existing);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }


    // Update project status (PATCH)

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProjectStatus(@PathVariable String id, @RequestBody Status newStatus) {
        for (ProjectDto project : projectStore) {
            if (project.getId().equals(id)) {
                project.setStatus(newStatus);
                project.setUpdatedAt(LocalDateTime.now());
                return ResponseEntity.ok("Project " + id + " status updated to " + newStatus);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }


    //  Delete project (Admin only — simulated)

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        boolean removed = projectStore.removeIf(p -> p.getId().equals(id));
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Project with ID " + id + " not found.");
        }
        return ResponseEntity.ok("Project " + id + " deleted successfully.");
    }
}
