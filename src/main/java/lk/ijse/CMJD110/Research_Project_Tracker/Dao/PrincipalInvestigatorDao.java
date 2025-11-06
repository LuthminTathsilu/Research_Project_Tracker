package lk.ijse.CMJD110.Research_Project_Tracker.Dao;

import lk.ijse.CMJD110.Research_Project_Tracker.Entity.PIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalInvestigatorDao extends JpaRepository<PIEntity, String> {
}
