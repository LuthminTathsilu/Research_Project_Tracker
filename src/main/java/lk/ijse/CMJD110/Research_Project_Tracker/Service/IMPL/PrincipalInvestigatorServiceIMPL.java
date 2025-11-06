package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.PrincipalInvestigatorDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.PIEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.PrincipalInvestigatorService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PrincipalInvestigatorServiceIMPL implements PrincipalInvestigatorService {

    private final PrincipalInvestigatorDao principalInvestigatorDao;
    private final EntityDTOConversionHandling conversionHandling;

    @Override
    public void savePrincipalInvestigator(UserDto principalInvestigator) {
        PIEntity entity = conversionHandling.toPIEntity(principalInvestigator);
        entity.setPiId(IDGenerator.principalInvestigatorIdGen());
        principalInvestigatorDao.save(entity);
    }

    @Override
    public UserDto getSelectedPrincipalInvestigator(String piId) throws Exception {
        Optional<PIEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new Exception("Principal Investigator not found");
        }
        return conversionHandling.toPrincipalInvestigatorDto(foundPI.get());
    }

    @Override
    public List<UserDto> getAllPrincipalInvestigators() {
        return conversionHandling.getUserDtoList(principalInvestigatorDao.findAll());
    }

    @Override
    public void updatePrincipalInvestigator(String piId, UserDto updatedPI) throws Exception {
        Optional<PIEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new Exception("Principal Investigator not found");
        }
        PIEntity entity = foundPI.get();
        entity.setFullName(updatedPI.getFullname());
        entity.setUsername(updatedPI.getUsername());
        entity.setPassword(updatedPI.getPassword());
    }

    @Override
    public void deletePrincipalInvestigator(String piId) throws Exception {
        Optional<PIEntity> foundPI = principalInvestigatorDao.findById(piId);
        if (foundPI.isEmpty()) {
            throw new Exception("Principal Investigator not found");
        }
        principalInvestigatorDao.deleteById(piId);
    }
}
