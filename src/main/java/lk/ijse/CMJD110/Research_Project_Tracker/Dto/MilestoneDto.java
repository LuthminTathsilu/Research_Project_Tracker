package lk.ijse.CMJD110.Research_Project_Tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MilestoneDto implements Serializable {
    private String id;              // Primary key
    private ProjectDto project;     // Associated project
    private String title;           // Milestone title
    private String description;     // Notes or task details
    private LocalDate dueDate;      // Deadline date
    private Boolean isCompleted;    // Completion flag
    private UserDto createdBy;      // User who created the milestone
}
