package lk.ijse.CMJD110.Research_Project_Tracker.Entity;


import jakarta.persistence.*;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id
    private String adminId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
}
