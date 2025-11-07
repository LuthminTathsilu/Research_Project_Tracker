package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    // Constructor Injection (recommended)
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Get all projects
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // Get project by ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjectById(@PathVariable String id) {
        ProjectDto project = projectService.getProjectById(id);
        return (project != null)
                ? ResponseEntity.ok(project)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }

    // Create new project
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.createProject(projectDto), HttpStatus.CREATED);
    }

    // Update full project
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable String id, @RequestBody ProjectDto updatedProject) {
        ProjectDto updated = projectService.updateProject(id, updatedProject);
        return (updated != null)
                ? ResponseEntity.ok(updated)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }

    // Update project status
    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProjectStatus(@PathVariable String id, @RequestBody Status newStatus) {
        boolean updated = projectService.updateProjectStatus(id, newStatus);
        return (updated)
                ? ResponseEntity.ok("Project " + id + " status updated to " + newStatus)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }

    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        boolean deleted = projectService.deleteProject(id);
        return (deleted)
                ? ResponseEntity.ok("Project " + id + " deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Project with ID " + id + " not found.");
    }
}
