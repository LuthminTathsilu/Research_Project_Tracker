package lk.ijse.CMJD110.Research_Project_Tracker.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "document")
public class DocumentEntity {

    @Id
    private String documentId;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "projectId")
    private ProjectEntity project;

    private String title;
    private String description;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", referencedColumnName = "memberId")
    private MemberEntity uploadedBy;

    private LocalDateTime uploadedAt;
}
