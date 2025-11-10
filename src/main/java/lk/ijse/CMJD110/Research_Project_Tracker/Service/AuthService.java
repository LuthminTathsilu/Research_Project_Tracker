package lk.ijse.CMJD110.Research_Project_Tracker.Service;


import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.JWTResponse;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.SecureUserDTO;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.UserLogin;

public interface AuthService {
    JWTResponse SignIn(UserLogin userLogin);
    JWTResponse SignUp(SecureUserDTO secureUserDTO);
}
