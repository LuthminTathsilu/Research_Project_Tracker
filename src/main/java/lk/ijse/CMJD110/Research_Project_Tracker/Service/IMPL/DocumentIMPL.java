package lk.ijse.CMJD110.Research_Project_Tracker.Service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.DocumentDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dao.ProjectDao;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.DocumentDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Entity.DocumentEntity;
import lk.ijse.CMJD110.Research_Project_Tracker.Exceptions.DocumentNotFoundException;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.DocumentService;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.EntityDTOConversionHandling;
import lk.ijse.CMJD110.Research_Project_Tracker.Util.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentIMPL implements DocumentService {

    private final DocumentDao documentDao;
    private final ProjectDao projectDao;
    private final EntityDTOConversionHandling conversion;

    @Override
    public void uploadDocument(String title, String description, MultipartFile file,
                             String uploadedAt, String projectId, String uploadedBy) throws IOException {

        byte[] fileBytes = file.getBytes();
        String base64File = Base64.getEncoder().encodeToString(fileBytes);

        String uploadTime = uploadedAt != null ? uploadedAt : LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);


        var docDTO = new DocumentDto();
        docDTO.setTitle(title);
        docDTO.setDescription(description);
        docDTO.setUrlOrPath(base64File);
        docDTO.setUploadedAt(uploadTime);
        ProjectDto project = new ProjectDto();
        project.setId(projectId);
        docDTO.setProject(project);

        UserDto user = new UserDto();
        user.setId(uploadedBy);
        docDTO.setUploadedBy(user);


        var documentEntity = conversion.toDocumentEntity(docDTO);
        documentEntity.setDocumentId(IDGenerator.documentIdGen());
        documentDao.save(documentEntity);
    }

    @Override
    public void deleteDocument(String documentId) throws Exception {
        Optional<DocumentEntity> found = documentDao.findById(documentId);
        if (found.isEmpty()) {
            throw new DocumentNotFoundException("Document not found");
        }
        documentDao.deleteById(documentId);
    }



    @Override
    public DocumentDto getSelectedDocument(String documentId) throws Exception {
        Optional<DocumentEntity> found = documentDao.findById(documentId);
        if (found.isEmpty()) {
            throw new DocumentNotFoundException("Document not found");
        }
        return conversion.toDocumentDTO(found.get());
    }


}
