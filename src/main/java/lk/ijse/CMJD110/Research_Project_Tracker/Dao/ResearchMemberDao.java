package lk.ijse.CMJD110.Research_Project_Tracker.Dao;

import lk.ijse.CMJD110.Research_Project_Tracker.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchMemberDao extends JpaRepository<MemberEntity, String> {
}
