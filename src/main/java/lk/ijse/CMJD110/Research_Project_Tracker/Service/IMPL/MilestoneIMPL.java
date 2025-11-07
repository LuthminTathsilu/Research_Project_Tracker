package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL;

import lk.ijse.CMJD110.Research_Project_Tracker.Dao.MilestoneDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.MilestoneDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.MilestoneEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.MilestoneService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilestoneIMPL implements MilestoneService {

    private final MilestoneDao milestoneDao;
    private final EntityDTOConversionHandling conversion;

    @Override
    public List<MilestoneDto> getMilestonesByProject(String projectId) {
        return milestoneDao.findAll()
                .stream()
                .filter(m -> m.getProject() != null && projectId.equals(m.getProject().getProjectId()))
                .map(conversion::toMilestoneDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MilestoneDto addMilestone(String projectId, MilestoneDto milestoneDto) {
        if (milestoneDto.getId() == null || milestoneDto.getId().isEmpty()) {
            milestoneDto.setId(UUID.randomUUID().toString());
        }

        if (milestoneDto.getProject() == null) {
            milestoneDto.setProject(new lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto());
        }
        milestoneDto.getProject().setId(projectId);

        var entity = conversion.toMilestoneEntity(milestoneDto);
        milestoneDao.save(entity);
        return conversion.toMilestoneDTO(entity);
    }

    @Override
    public MilestoneDto updateMilestone(String id, MilestoneDto updatedMilestone) {
        Optional<MilestoneEntity> existingOpt = milestoneDao.findById(id);

        if (existingOpt.isPresent()) {
            MilestoneEntity existing = existingOpt.get();
            existing.setTitle(updatedMilestone.getTitle());
            existing.setDescription(updatedMilestone.getDescription());
            existing.setDueDate(updatedMilestone.getDueDate());
            existing.setIsCompleted(updatedMilestone.getIsCompleted());

            if (updatedMilestone.getCreatedBy() != null) {
                existing.setCreatedBy(conversion.toMilestoneEntity(updatedMilestone).getCreatedBy());
            }
            if (updatedMilestone.getProject() != null) {
                existing.setProject(conversion.toMilestoneEntity(updatedMilestone).getProject());
            }

            milestoneDao.save(existing);
            return conversion.toMilestoneDTO(existing);
        }

        return null;
    }


    @Override
    public boolean deleteMilestone(String id) {
        if (milestoneDao.existsById(id)) {
            milestoneDao.deleteById(id);
            return true;
        }
        return false;
    }
}
