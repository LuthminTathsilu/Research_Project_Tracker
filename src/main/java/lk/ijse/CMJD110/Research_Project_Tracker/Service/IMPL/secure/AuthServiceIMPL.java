package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL.secure;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.secure.UserSecureDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.JWTResponse;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.SecureUserDTO;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.UserLogin;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.secure.SecureUserEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.AuthService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lk.ijse.CMJD110.Research_Project_Tracker.secureConfig.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {

    private final UserSecureDao userSecureDao;
    private final JWTUtils jwtUtils;
    private final EntityDTOConversionHandling conversion;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTResponse signIn(UserLogin userLogin) {
        // Authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );

        var user = userSecureDao.findByUsername(userLogin.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var token = jwtUtils.generateToken(user.getUsername(), user.getAuthorities());

        return JWTResponse.builder().token(token).build();
    }

    @Override
    public JWTResponse signUp(SecureUserDTO secureUserDTO) {
        secureUserDTO.setId(IDGenerator.userIdGen());
        secureUserDTO.setPassword(passwordEncoder.encode(secureUserDTO.getPassword()));

        // save the user with chosen role
        var savedUser = userSecureDao.save(conversion.toSecureUserEntity(secureUserDTO));

        var token = jwtUtils.generateToken(savedUser.getUsername(), savedUser.getAuthorities());

        return JWTResponse.builder().token(token).build();
    }

}
