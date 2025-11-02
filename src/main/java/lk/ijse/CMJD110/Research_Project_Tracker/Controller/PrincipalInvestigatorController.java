package lk.ijse.CMJD110.Research_Project_Tracker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/principalInvestigator/")
public class PrincipalInvestigatorController {


        @GetMapping("Healthtest")
        public String studentDataTest(){
            return "Principal Investigator control is Running";
        }

}
