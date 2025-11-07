package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();

    ProjectDto getProjectById(String id);

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(String id, ProjectDto updatedProject);

    boolean updateProjectStatus(String id, Status newStatus);

    boolean deleteProject(String id);
}
