package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.JWTResponse;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.SecureUserDTO;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.UserLogin;

public interface AuthService {
    JWTResponse signIn(UserLogin userLogin);
    JWTResponse signUp(SecureUserDTO secureUserDTO);
}
