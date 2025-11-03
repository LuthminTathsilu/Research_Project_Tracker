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
@RequestMapping("api/v1/researchMember")
public class ResearchMemberController {

    // Save Research Member
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> saveResearchMember(@RequestBody UserDto userDTO) {
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    // Get selected Research Member
    @GetMapping(value = "{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getSelectedResearchMember(@PathVariable String memberId) {
        System.out.println("Research Member ID is " + memberId);
        var userDTO = new UserDto(
                "RM001",
                "research_member",
                "Research789!",
                "Isuru Wijesinghe",
                Role.ResearchMember,
                LocalDateTime.now()
        );

        if (memberId.equals(userDTO.getId())) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // View All Research Members
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllResearchMembers() {
        List<UserDto> userList = Arrays.asList(
                new UserDto("RM001", "research_member", "Research789!", "Isuru Wijesinghe", Role.ResearchMember, LocalDateTime.now()),
                new UserDto("RM002", "assistant_member", "Assist234!", "Nadeesha Perera", Role.ResearchMember, LocalDateTime.now()),
                new UserDto("RM003", "junior_member", "Junior999!", "Tharindu Silva", Role.ResearchMember, LocalDateTime.now()),
                new UserDto("RM004", "project_member", "Proj654!", "Chathuri Gunasekara", Role.ResearchMember, LocalDateTime.now())
        );

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // Update Research Member
    @PatchMapping
    public void updateResearchMember(@RequestParam String Id, @RequestParam String pass, @RequestBody UserDto toBeUpdatedMemberDetails) {
        System.out.println("Research Member ID: " + Id);
        System.out.println("Research Member Password: " + pass);
        System.out.println("To be updated details " + toBeUpdatedMemberDetails);
    }

    // Delete Research Member
    @DeleteMapping
    public void deleteResearchMember(@RequestHeader("X-memberId") String memberId) {
        System.out.println("To be deleted Research Member ID: " + memberId);
    }
}
