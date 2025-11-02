package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
}
