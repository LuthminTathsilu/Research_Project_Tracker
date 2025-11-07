package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;

import java.util.List;

public interface AdminService {

    void saveAdmin(UserDto admin);

    UserDto getSelectedAdmin(String adminId) throws Exception;

    List<UserDto> getAllAdmins();

    void updateAdmin(String adminId, UserDto updatedAdmin) throws Exception;

    void deleteAdmin(String adminId) throws Exception;
}
