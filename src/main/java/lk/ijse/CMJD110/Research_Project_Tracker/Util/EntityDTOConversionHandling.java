package lk.ijse.CMJD110.Research_Project_Tracker.Util;

import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ProjectDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ResearchMemberDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.DocumentDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.MilestoneDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.secure.SecureUserDTO;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.*;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.secure.SecureUserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDTOConversionHandling {
    private final ModelMapper modelMapper;
    private final ProjectDao projectDao;
    private final ResearchMemberDao researchMemberDao;

    public UserDto toPrincipalInvestigatorDto(PIEntity piEntity) {
        return modelMapper.map(piEntity, UserDto.class);
    }

    public PIEntity toPIEntity(UserDto userDto) {
        return modelMapper.map(userDto, PIEntity.class);
    }

    public List<UserDto> getPrincipalInvestigatorDtoList(List<PIEntity> piEntityList) {
        return modelMapper.map(piEntityList, new TypeToken<List<UserDto>>() {}.getType());
    }

    //  Research Members
    public UserDto toResearchMemberDto(MemberEntity memberEntity) {
        return modelMapper.map(memberEntity, UserDto.class);
    }

    public MemberEntity toMemberEntity(UserDto userDto) {
        return modelMapper.map(userDto, MemberEntity.class);
    }

    public List<UserDto> getResearchMemberDtoList(List<MemberEntity> memberEntityList) {
        return modelMapper.map(memberEntityList, new TypeToken<List<UserDto>>() {}.getType());
    }

    //  Admin
    public UserDto toAdminDto(AdminEntity adminEntity) {
        return modelMapper.map(adminEntity, UserDto.class);
    }

    public AdminEntity toAdminEntity(UserDto userDto) {
        return modelMapper.map(userDto, AdminEntity.class);
    }

    public List<UserDto> getAdminDtoList(List<AdminEntity> adminEntityList) {
        return modelMapper.map(adminEntityList, new TypeToken<List<UserDto>>() {}.getType());
    }

    //Document

    public DocumentDto toDocumentDTO(DocumentEntity documentEntity) {
        DocumentDto dto = new DocumentDto();

        dto.setId(documentEntity.getDocumentId());
        dto.setTitle(documentEntity.getTitle());
        dto.setDescription(documentEntity.getDescription());
        dto.setUrlOrPath(Base64.getEncoder().encodeToString(documentEntity.getFilePath()));
        dto.setUploadedAt(String.valueOf(documentEntity.getUploadedAt()));

        if (documentEntity.getProject() != null) {
            ProjectEntity projectEntity = documentEntity.getProject();
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(projectEntity.getProjectId());
            projectDto.setTitle(projectEntity.getTitle());
            projectDto.setStatus(projectEntity.getStatus());
            dto.setProject(projectDto);
        }

        if (documentEntity.getUploadedBy() != null) {
            MemberEntity memberEntity = documentEntity.getUploadedBy();
            UserDto userDto = new UserDto();
            userDto.setId(memberEntity.getMemberId());
            userDto.setFullname(memberEntity.getFullName());
            userDto.setUsername(memberEntity.getUsername());
            dto.setUploadedBy(userDto);
        }

        return dto;
    }


    public DocumentEntity toDocumentEntity(DocumentDto documentDTO) {
        DocumentEntity entity = new DocumentEntity();

        entity.setDocumentId(documentDTO.getId());
        entity.setTitle(documentDTO.getTitle());
        entity.setDescription(documentDTO.getDescription());
        entity.setFilePath(Base64.getDecoder().decode(documentDTO.getUrlOrPath()));

        entity.setUploadedAt(LocalDateTime.parse(documentDTO.getUploadedAt()));

        if (documentDTO.getProject() != null && documentDTO.getProject().getId() != null) {
            ProjectEntity selectedProject = projectDao.findById(documentDTO.getProject().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Project not found with id: " + documentDTO.getProject().getId()));
            entity.setProject(selectedProject);
        }

        if (documentDTO.getUploadedBy() != null && documentDTO.getUploadedBy().getId() != null) {
            MemberEntity uploadedBy = researchMemberDao.findById(documentDTO.getUploadedBy().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Member not found with id: " + documentDTO.getUploadedBy().getId()));
            entity.setUploadedBy(uploadedBy);
        }

        return entity;
    }


    public List<DocumentDto> toDocumentDTOList(List<DocumentEntity> entities) {
        return entities.stream().map(this::toDocumentDTO).toList();
    }

    public List<DocumentEntity> toDocumentEntityList(List<DocumentDto> dtos) {
        return dtos.stream().map(this::toDocumentEntity).toList();
    }


    //   Project
    public ProjectDto toProjectDTO(ProjectEntity projectEntity) {
        ProjectDto dto = new ProjectDto();
        dto.setId(projectEntity.getProjectId());
        dto.setTitle(projectEntity.getTitle());
        dto.setSummary(projectEntity.getSummary());
        dto.setStatus(projectEntity.getStatus());
        dto.setTags(projectEntity.getTags());
        dto.setStartDate(projectEntity.getStartDate());
        dto.setEndDate(projectEntity.getEndDate());
        dto.setCreatedAt(projectEntity.getCreatedAt());
        dto.setUpdatedAt(projectEntity.getUpdatedAt());

        if (projectEntity.getPi() != null) {
            dto.setPi(modelMapper.map(projectEntity.getPi(), lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto.class));
        }

        return dto;
    }

    public ProjectEntity toProjectEntity(ProjectDto dto) {
        ProjectEntity entity = new ProjectEntity();
        entity.setProjectId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSummary(dto.getSummary());
        entity.setStatus(dto.getStatus());
        entity.setTags(dto.getTags());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        if (dto.getPi() != null) {
            lk.ijse.CMJD110.Research_Project_Tracker.Entity.PIEntity piEntity =
                    modelMapper.map(dto.getPi(), lk.ijse.CMJD110.Research_Project_Tracker.Entity.PIEntity.class);
            entity.setPi(piEntity);
        }

        return entity;
    }

    public List<ProjectDto> toProjectDTOList(List<ProjectEntity> projectEntities) {
        return projectEntities.stream().map(this::toProjectDTO).toList();
    }

    public List<ProjectEntity> toProjectEntityList(List<ProjectDto> projectDTOs) {
        return projectDTOs.stream().map(this::toProjectEntity).toList();
    }


    // Milestone

    public MilestoneDto toMilestoneDTO(MilestoneEntity entity) {
        MilestoneDto dto = new MilestoneDto();

        dto.setId(entity.getMilestoneId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDueDate(entity.getDueDate());
        dto.setIsCompleted(entity.getIsCompleted());

        if (entity.getProject() != null) {
            ProjectEntity projectEntity = entity.getProject();
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(projectEntity.getProjectId());
            projectDto.setTitle(projectEntity.getTitle());
            projectDto.setStatus(projectEntity.getStatus());
            dto.setProject(projectDto);
        }

        if (entity.getCreatedBy() != null) {
            MemberEntity memberEntity = entity.getCreatedBy();
            UserDto userDto = new UserDto();
            userDto.setId(memberEntity.getMemberId());
            userDto.setFullname(memberEntity.getFullName());
            userDto.setUsername(memberEntity.getUsername());
            dto.setCreatedBy(userDto);
        }

        return dto;
    }

    public MilestoneEntity toMilestoneEntity(MilestoneDto dto) {
        MilestoneEntity entity = new MilestoneEntity();

        entity.setMilestoneId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDueDate(dto.getDueDate());
        entity.setIsCompleted(dto.getIsCompleted());


        if (dto.getProject() != null && dto.getProject().getId() != null) {
            ProjectEntity projectEntity = projectDao.findById(dto.getProject().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Project not found with id: " + dto.getProject().getId()));
            entity.setProject(projectEntity);
        }

        if (dto.getCreatedBy() != null && dto.getCreatedBy().getId() != null) {
            MemberEntity memberEntity = researchMemberDao.findById(dto.getCreatedBy().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Member not found with id: " + dto.getCreatedBy().getId()));
            entity.setCreatedBy(memberEntity);
        }

        return entity;
    }

    public List<MilestoneDto> toMilestoneDTOList(List<MilestoneEntity> entities) {
        return entities.stream().map(this::toMilestoneDTO).toList();
    }

    public List<MilestoneEntity> toMilestoneEntityList(List<MilestoneDto> dtos) {
        return dtos.stream().map(this::toMilestoneEntity).toList();
    }
    // user
    public SecureUserDTO toSecureUserDTO(SecureUserEntity secureUserEntity) {
        return modelMapper.map(secureUserEntity, SecureUserDTO.class);
    }
    public SecureUserEntity toSecureUserEntity(SecureUserDTO secureUserDTO){
        return modelMapper.map(secureUserDTO, SecureUserEntity.class);
    }
}
