package lk.ijse.CMJD110.Research_Project_Tracker.Dao.secure;


import lk.ijse.CMJD110.Research_Project_Tracker.Entity.secure.SecureUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecureDao extends JpaRepository<SecureUserEntity, String> {
    Optional<SecureUserEntity> findByUsername(String username);
}
