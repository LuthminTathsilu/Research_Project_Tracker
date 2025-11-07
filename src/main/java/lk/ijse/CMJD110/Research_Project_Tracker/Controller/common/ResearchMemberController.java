package lk.ijse.CMJD110.Research_Project_Tracker.Controller.common;

import lk.ijse.CMJD110.Research_Project_Tracker.Dto.UserDto;
import lk.ijse.CMJD110.Research_Project_Tracker.Service.ResearchMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/researchMember")
@RequiredArgsConstructor
public class ResearchMemberController {

    private final ResearchMemberService researchMemberService;

    //  Save Research Member
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveResearchMember(@RequestBody UserDto userDTO) {
        researchMemberService.saveResearchMember(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //  Get Selected Research Member
    @GetMapping(value = "{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getSelectedResearchMember(@PathVariable String memberId) {
        try {
            UserDto selectedMember = researchMemberService.getSelectedResearchMember(memberId);
            return new ResponseEntity<>(selectedMember, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  Get All Research Members
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllResearchMembers() {
        return new ResponseEntity<>(researchMemberService.getAllResearchMembers(), HttpStatus.OK);
    }

    //  Update Research Member
    @PatchMapping
    public ResponseEntity<Void> updateResearchMember(@RequestParam("id") String memberId,
                                                     @RequestBody UserDto toBeUpdatedMember) {
        try {
            researchMemberService.updateResearchMember(memberId, toBeUpdatedMember);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //  Delete Research Member
    @DeleteMapping("{memberId}")
    public ResponseEntity<Void> deleteResearchMember(@PathVariable String memberId) {
        try {
            researchMemberService.deleteResearchMember(memberId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
