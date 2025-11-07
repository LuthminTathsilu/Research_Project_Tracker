package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL;

import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ProjectDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.ProjectEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Exceptions.ProjectNotFoundException;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.ProjectService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectIMPL implements ProjectService {

    private final ProjectDao projectDao;
    private final ModelMapper modelMapper;

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectDao.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ProjectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(String id) {
        ProjectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));
        return modelMapper.map(entity, ProjectDto.class);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        // Auto-generate project ID
        projectDto.setId(IDGenerator.projectIdGen());
        projectDto.setCreatedAt(LocalDateTime.now());
        projectDto.setUpdatedAt(LocalDateTime.now());

        ProjectEntity entity = modelMapper.map(projectDto, ProjectEntity.class);
        projectDao.save(entity);
        return modelMapper.map(entity, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(String id, ProjectDto updatedProject) {
        ProjectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));

        entity.setTitle(updatedProject.getTitle());
        entity.setSummary(updatedProject.getSummary());
        entity.setTags(updatedProject.getTags());
        if (updatedProject.getPi() != null) {
            entity.setPi(modelMapper.map(updatedProject.getPi(), entity.getPi().getClass()));
        }
        entity.setStatus(updatedProject.getStatus());
        entity.setStartDate(updatedProject.getStartDate());
        entity.setEndDate(updatedProject.getEndDate());
        entity.setUpdatedAt(LocalDateTime.now());

        projectDao.save(entity);
        return modelMapper.map(entity, ProjectDto.class);
    }

    @Override
    public boolean updateProjectStatus(String id, Status newStatus) {
        ProjectEntity entity = projectDao.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + id));

        entity.setStatus(newStatus);
        entity.setUpdatedAt(LocalDateTime.now());
        projectDao.save(entity);
        return true;
    }

    @Override
    public boolean deleteProject(String id) {
        if (!projectDao.existsById(id)) {
            throw new ProjectNotFoundException("Project not found with id: " + id);
        }
        projectDao.deleteById(id);
        return true;
    }
}
