package lk.ijse.CMJD110.Research_Project_Tracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto implements Serializable {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Role role;
    private LocalDateTime createdAt;
}
