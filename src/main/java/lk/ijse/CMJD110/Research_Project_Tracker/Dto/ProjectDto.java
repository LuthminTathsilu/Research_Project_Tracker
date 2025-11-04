package lk.ijse.CMJD110.Research_Project_Tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDto implements Serializable {
    private String id;
    private String title;
    private String summary;
    private Status status;
    private UserDto pi;
    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
