package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL;

import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ProjectDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.ProjectEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.ProjectService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        Optional<ProjectEntity> entity = projectDao.findById(id);
        return entity.map(e -> modelMapper.map(e, ProjectDto.class)).orElse(null);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        // 🔹 Auto-generate project ID
        projectDto.setId(IDGenerator.projectIdGen());
        projectDto.setCreatedAt(LocalDateTime.now());
        projectDto.setUpdatedAt(LocalDateTime.now());

        ProjectEntity entity = modelMapper.map(projectDto, ProjectEntity.class);
        projectDao.save(entity);
        return modelMapper.map(entity, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(String id, ProjectDto updatedProject) {
        Optional<ProjectEntity> existing = projectDao.findById(id);

        if (existing.isPresent()) {
            ProjectEntity entity = existing.get();
            entity.setTitle(updatedProject.getTitle());
            entity.setSummary(updatedProject.getSummary());
            entity.setTags(updatedProject.getTags());
            entity.setPi(modelMapper.map(updatedProject.getPi(), entity.getPi().getClass()));
            entity.setStatus(updatedProject.getStatus());
            entity.setStartDate(updatedProject.getStartDate());
            entity.setEndDate(updatedProject.getEndDate());
            entity.setUpdatedAt(LocalDateTime.now());

            projectDao.save(entity);
            return modelMapper.map(entity, ProjectDto.class);
        }

        return null;
    }

    @Override
    public boolean updateProjectStatus(String id, Status newStatus) {
        Optional<ProjectEntity> existing = projectDao.findById(id);
        if (existing.isPresent()) {
            ProjectEntity entity = existing.get();
            entity.setStatus(newStatus);
            entity.setUpdatedAt(LocalDateTime.now());
            projectDao.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProject(String id) {
        if (projectDao.existsById(id)) {
            projectDao.deleteById(id);
            return true;
        }
        return false;
    }
}
