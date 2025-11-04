package lk.ijse.CMJD110.Research_Project_Tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentDto implements Serializable {
    private String id;
    private ProjectDto project;
    private String title;
    private String description;
    private String urlOrPath;
    private UserDto uploadedBy;
    private String uploadedAt;
}
