package lk.ijse.CMJD110.Research_Project_Tracker.secureConfig;

import lk.ijse.CMJD110.Research_Project_Tracker.Dao.secure.UserSecureDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserSecureDao userSecureDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userSecureDao.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
