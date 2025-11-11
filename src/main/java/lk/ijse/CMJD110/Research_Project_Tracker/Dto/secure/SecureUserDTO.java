package lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SecureUserDTO {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Role role; // This allows the client to choose the role
    private LocalDateTime createdAt;
}
