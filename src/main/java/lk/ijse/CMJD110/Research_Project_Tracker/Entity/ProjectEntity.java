package lk.ijse.CMJD110.Research_Project_Tracker.Entity;



import jakarta.persistence.*;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    private String projectId;

    private String title;
    private String summary;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "pi_id", referencedColumnName = "piId")
    private PIEntity pi;

    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
