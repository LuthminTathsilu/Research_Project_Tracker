package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.DocumentDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("api/v1/document")
public class DocumentController {

    // In-memory mock database (you can replace this later with service/DB)
    private final List<DocumentDto> documentStore = new ArrayList<>();

    //  Upload a document
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDto> uploadDocument(
            @RequestParam String documentId,
            @RequestParam String projectId,
            @RequestParam String uploadedBy,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String uploadedAt
    ) {
        DocumentDto documentDto = new DocumentDto();

        try {
            byte[] fileBytes = file.getBytes();
            String base64File = Base64.getEncoder().encodeToString(fileBytes);

            String uploadTime = uploadedAt != null ? uploadedAt :
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

            documentDto.setId(documentId);
            documentDto.setProject(new ProjectDto(projectId, null, null, null, null, null, null, null, null, null));
            documentDto.setTitle(title);
            documentDto.setDescription(description);
            documentDto.setUrlOrPath(base64File);
            documentDto.setUploadedBy(new UserDto(uploadedBy, null, null, null, null, null));
            documentDto.setUploadedAt(uploadTime);


            documentStore.add(documentDto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(documentDto);
    }

    //  List all documents for a specific project
    @GetMapping(value = "/api/projects/{projectId}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentDto>> getDocumentsByProject(@PathVariable String projectId) {
        // Filter documents by project ID
        List<DocumentDto> filteredDocs = new ArrayList<>();
        for (DocumentDto doc : documentStore) {
            if (doc.getProject() != null && doc.getProject().getId().equals(projectId)) {
                filteredDocs.add(doc);
            }
        }

        return ResponseEntity.ok(filteredDocs);
    }


    // Delete document (only Admin or PI)

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<String> deleteDocument(
            @PathVariable String id,
            @RequestHeader("X-User-Role") String userRole // temporary role check
    ) {
        // Check role validity
        if (!(userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("PrincipalInvestigator"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: only Admin or Principal Investigator can delete documents.");
        }


        boolean removed = documentStore.removeIf(doc -> doc.getId().equals(id));

        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Document with ID " + id + " not found.");
        }

        return ResponseEntity.ok("Document " + id + " deleted successfully.");
    }
}
