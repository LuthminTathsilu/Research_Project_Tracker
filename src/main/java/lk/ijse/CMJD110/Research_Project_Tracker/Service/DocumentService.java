package lk.ijse.CMJD110.Research_Project_Tracker.Service;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface DocumentService {

    void uploadDocument(String title,
                      String description,
                      MultipartFile file,
                      String uploadedAt,
                      String projectId,
                      String uploadedBy) throws IOException;

    void deleteDocument(String documentId) throws Exception;


    DocumentDto getSelectedDocument(String documentId) throws Exception;


}
