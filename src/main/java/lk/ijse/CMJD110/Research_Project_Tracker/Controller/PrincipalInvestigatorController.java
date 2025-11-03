package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/principalInvestigator")
public class PrincipalInvestigatorController {
    //to do : handle curd of principal Investigator
    // save principal investigator
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> savePrincipalInvestigator(@RequestBody UserDto userDTO){
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    // Get selected Principal Investigator
    @GetMapping(value = "{principalInvestigatorId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getSelectedStudent(@PathVariable String principalInvestigatorId){
        System.out.println("Student ID is "+principalInvestigatorId);
        var userDTO = new UserDto(
                "U001",
                "principal_investigator",
                "SecurePass123!",
                "Dr. Ayesha Fernando",
                Role.PrincipalInvestigator,
                LocalDateTime.now()
        );

        if(principalInvestigatorId.equals(userDTO.getId())){
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // View All Principal Investigators
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllPrincipalInvestigators(){
        List<UserDto> userList = Arrays.asList(
                new UserDto("U001", "principal_investigator",
                        "SecurePass123!", "Dr. Ayesha Fernando",
                        Role.PrincipalInvestigator, LocalDateTime.now()),

                new UserDto("U002", "admin_user",
                        "AdminPass456!", "Ravindu Perera",
                        Role.Admin, LocalDateTime.now()),

                new UserDto("U003", "research_member",
                        "Research789!", "Isuru Wijesinghe",
                        Role.ResearchMember, LocalDateTime.now()),

                new UserDto("U004", "viewer_user",
                        "Viewer321!", "Nimasha Gunawardena",
                        Role.Viewer, LocalDateTime.now()),

                new UserDto("U005", "assistant_investigator",
                        "Assist987!", "Kavindu Silva",
                        Role.PrincipalInvestigator, LocalDateTime.now())
        );

        return new  ResponseEntity<>(userList, HttpStatus.OK);
    }

    // Update Principal Investigator
    @PatchMapping
    public void updatePrincipalInvestigator(@RequestParam String Id, @RequestParam String pass, @RequestBody UserDto toBeUpdatedStudentDetails){
        System.out.println("Principal Investigator ID: "+Id);
        System.out.println("Principal Investigator Password: "+pass);
        System.out.println("To be updated details "+toBeUpdatedStudentDetails);
    }
    // Delete Principal Investigator
    @DeleteMapping
    public void deleteStudent(@RequestHeader ("X-principalInvestigatorId") String principalInvestigatorId){
        System.out.println("To be deleted ID: "+principalInvestigatorId);
    }

}
