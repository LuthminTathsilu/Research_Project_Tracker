package lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogin implements Serializable {
    private String username;
    private String password;
}
