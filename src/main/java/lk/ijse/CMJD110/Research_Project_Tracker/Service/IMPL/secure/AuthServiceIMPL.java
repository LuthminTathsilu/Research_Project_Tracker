package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL.secure;


import jakarta.transaction.Transactional;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.JWTResponse;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.SecureUserDTO;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.UserLogin;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    @Override
    public JWTResponse SignIn(UserLogin userLogin) {
        return null;
    }

    @Override
    public JWTResponse SignUp(SecureUserDTO secureUserDTO) {
        return null;
    }
}