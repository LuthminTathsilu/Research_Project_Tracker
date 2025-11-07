package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL.userRelated;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.AdminDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.AdminEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Exceptions.UserNotFoundException;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.AdminService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminIMPL implements AdminService {

    private final AdminDao adminDao;
    private final EntityDTOConversionHandling conversionHandling;

    @Override
    public void saveAdmin(UserDto admin) {
        AdminEntity entity = conversionHandling.toAdminEntity(admin);
        entity.setAdminId(IDGenerator.adminIdGen());
        adminDao.save(entity);
    }

    @Override
    public UserDto getSelectedAdmin(String adminId) {
        Optional<AdminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new UserNotFoundException("Admin not found with id: " + adminId);
        }
        return conversionHandling.toAdminDto(foundAdmin.get());
    }

    @Override
    public List<UserDto> getAllAdmins() {
        return conversionHandling.getAdminDtoList(adminDao.findAll());
    }

    @Override
    public void updateAdmin(String adminId, UserDto updatedAdmin) {
        Optional<AdminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new UserNotFoundException("Admin not found with id: " + adminId);
        }
        AdminEntity entity = foundAdmin.get();
        entity.setFullName(updatedAdmin.getFullname());
        entity.setUsername(updatedAdmin.getUsername());
        entity.setPassword(updatedAdmin.getPassword());
    }

    @Override
    public void deleteAdmin(String adminId) {
        Optional<AdminEntity> foundAdmin = adminDao.findById(adminId);
        if (foundAdmin.isEmpty()) {
            throw new UserNotFoundException("Admin not found with id: " + adminId);
        }
        adminDao.deleteById(adminId);
    }
}
