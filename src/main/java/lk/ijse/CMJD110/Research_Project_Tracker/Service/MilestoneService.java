package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.MilestoneDto;

import java.util.List;

public interface MilestoneService {

    List<MilestoneDto> getMilestonesByProject(String projectId);

    MilestoneDto addMilestone(String projectId, MilestoneDto milestoneDto);

    MilestoneDto updateMilestone(String id, MilestoneDto updatedMilestone);

    boolean deleteMilestone(String id);
}
