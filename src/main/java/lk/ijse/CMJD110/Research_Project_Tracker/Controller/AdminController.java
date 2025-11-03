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
@RequestMapping("api/v1/admin")
public class AdminController {

    // Save Admin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> saveAdmin(@RequestBody UserDto userDTO) {
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    // Get selected Admin
    @GetMapping(value = "{adminId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getSelectedAdmin(@PathVariable String adminId) {
        System.out.println("Admin ID is " + adminId);
        var userDTO = new UserDto(
                "A001",
                "admin_user",
                "AdminPass456!",
                "Ravindu Perera",
                Role.Admin,
                LocalDateTime.now()
        );

        if (adminId.equals(userDTO.getId())) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // View All Admins
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllAdmins() {
        List<UserDto> userList = Arrays.asList(
                new UserDto("A001", "admin_user", "AdminPass456!", "Ravindu Perera", Role.Admin, LocalDateTime.now()),
                new UserDto("A002", "system_admin", "SysAdmin789!", "Dinithi Fernando", Role.Admin, LocalDateTime.now()),
                new UserDto("A003", "super_admin", "Super123!", "Kasun Jayasuriya", Role.Admin, LocalDateTime.now())
        );

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // Update Admin
    @PatchMapping
    public void updateAdmin(@RequestParam String Id, @RequestParam String pass, @RequestBody UserDto toBeUpdatedAdminDetails) {
        System.out.println("Admin ID: " + Id);
        System.out.println("Admin Password: " + pass);
        System.out.println("To be updated details " + toBeUpdatedAdminDetails);
    }

    // Delete Admin
    @DeleteMapping
    public void deleteAdmin(@RequestHeader("X-adminId") String adminId) {
        System.out.println("To be deleted Admin ID: " + adminId);
    }
}

