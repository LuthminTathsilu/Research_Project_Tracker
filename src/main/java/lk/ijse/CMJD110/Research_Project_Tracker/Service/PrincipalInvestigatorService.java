package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import java.util.List;

public interface PrincipalInvestigatorService {

    void savePrincipalInvestigator(UserDto principalInvestigator);

    UserDto getSelectedPrincipalInvestigator(String piId) throws Exception;

    List<UserDto> getAllPrincipalInvestigators();

    void updatePrincipalInvestigator(String piId, UserDto updatedPI) throws Exception;

    void deletePrincipalInvestigator(String piId) throws Exception;
}
