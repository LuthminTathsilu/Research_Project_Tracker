package lk.ijse.CMJD110.Research_Project_Tracker.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "milestone")
public class MilestoneEntity {

    @Id
    private String milestoneId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "projectId")
    private ProjectEntity project;

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "memberId")
    private MemberEntity createdBy;
}
