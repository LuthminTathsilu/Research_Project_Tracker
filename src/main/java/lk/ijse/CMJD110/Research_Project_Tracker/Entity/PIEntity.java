package lk.ijse.CMJD110.Research_Project_Tracker.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pi")
public class PIEntity {

    @Id
    private String piId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;
    private String department;
    private String email;
    private LocalDateTime joinedAt;


    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberEntity> members;

    @OneToMany(mappedBy = "pi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectEntity> projects;
}
