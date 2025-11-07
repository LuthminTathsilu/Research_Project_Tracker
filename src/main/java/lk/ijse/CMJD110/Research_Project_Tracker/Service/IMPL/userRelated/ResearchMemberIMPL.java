package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL.userRelated;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ResearchMemberDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.MemberEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Exceptions.UserNotFoundException;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.ResearchMemberService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ResearchMemberIMPL implements ResearchMemberService {

    private final ResearchMemberDao researchMemberDao;
    private final EntityDTOConversionHandling conversionHandling;

    @Override
    public void saveResearchMember(UserDto researchMember) {
        MemberEntity entity = conversionHandling.toMemberEntity(researchMember);
        entity.setMemberId(IDGenerator.researchMemberIdGen());
        researchMemberDao.save(entity);
    }

    @Override
    public UserDto getSelectedResearchMember(String memberId) {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new UserNotFoundException("Research Member not found with id: " + memberId);
        }
        return conversionHandling.toResearchMemberDto(foundMember.get());
    }

    @Override
    public List<UserDto> getAllResearchMembers() {
        return conversionHandling.getResearchMemberDtoList(researchMemberDao.findAll());
    }

    @Override
    public void updateResearchMember(String memberId, UserDto updatedMember) {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new UserNotFoundException("Research Member not found with id: " + memberId);
        }
        MemberEntity entity = foundMember.get();
        entity.setFullName(updatedMember.getFullname());
        entity.setUsername(updatedMember.getUsername());
        entity.setPassword(updatedMember.getPassword());
    }

    @Override
    public void deleteResearchMember(String memberId) {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new UserNotFoundException("Research Member not found with id: " + memberId);
        }
        researchMemberDao.deleteById(memberId);
    }
}
