package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import java.util.List;

public interface ResearchMemberService {

    void saveResearchMember(UserDto researchMember);

    UserDto getSelectedResearchMember(String memberId) throws Exception;

    List<UserDto> getAllResearchMembers();

    void updateResearchMember(String memberId, UserDto updatedMember) throws Exception;

    void deleteResearchMember(String memberId) throws Exception;
}
