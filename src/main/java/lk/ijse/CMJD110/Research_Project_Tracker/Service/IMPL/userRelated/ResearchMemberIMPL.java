package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL.userRelated;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ResearchMemberDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.MemberEntity;
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
    public UserDto getSelectedResearchMember(String memberId) throws Exception {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new Exception("Research Member not found");
        }
        return conversionHandling.toResearchMemberDto(foundMember.get());
    }

    @Override
    public List<UserDto> getAllResearchMembers() {
        return conversionHandling.getResearchMemberDtoList(researchMemberDao.findAll());
    }

    @Override
    public void updateResearchMember(String memberId, UserDto updatedMember) throws Exception {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new Exception("Research Member not found");
        }
        MemberEntity entity = foundMember.get();
        entity.setFullName(updatedMember.getFullname());
        entity.setUsername(updatedMember.getUsername());
        entity.setPassword(updatedMember.getPassword());
    }

    @Override
    public void deleteResearchMember(String memberId) throws Exception {
        Optional<MemberEntity> foundMember = researchMemberDao.findById(memberId);
        if (foundMember.isEmpty()) {
            throw new Exception("Research Member not found");
        }
        researchMemberDao.deleteById(memberId);
    }
}
