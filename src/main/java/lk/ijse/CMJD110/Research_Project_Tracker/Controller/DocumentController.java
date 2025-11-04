package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.DocumentDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.ProjectDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@RestController
@RequestMapping("api/v1/document")
public class DocumentController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentDto> uploadDocument(
            @RequestParam String documentId,
            @RequestParam String projectId,
            @RequestParam String uploadedBy,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile file,
            @RequestParam( required = false) String uploadedAt
    ) {
        DocumentDto documentDto = new DocumentDto();

        try {
            // Step 1: Convert file to Base64
            byte[] fileBytes = file.getBytes();
            String base64File = Base64.getEncoder().encodeToString(fileBytes);

            // Step 2: Handle uploaded time (if null, use current time)
            String uploadTime = uploadedAt != null ? uploadedAt :
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

            // Step 3: Build the DocumentDto object
            documentDto.setId(documentId);
            documentDto.setProject(new ProjectDto(projectId, null, null, null, null, null, null, null, null, null)); // Only ID for now
            documentDto.setTitle(title);
            documentDto.setDescription(description);
            documentDto.setUrlOrPath(base64File); // Store Base64 temporarily instead of URL/path
            documentDto.setUploadedBy(new UserDto(uploadedBy, null, null, null, null, null)); // Only ID for now
            documentDto.setUploadedAt(String.valueOf(LocalDateTime.now()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(documentDto);
    }
}
