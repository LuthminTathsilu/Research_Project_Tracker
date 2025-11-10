package lk.ijse.CMJD110.Research_Project_Tracker.Util;

import java.util.UUID;

public class IDGenerator {

    public static String principalInvestigatorIdGen() {
        return "PI-" + UUID.randomUUID();
    }

    public static String researchMemberIdGen() {
        return "RM-" + UUID.randomUUID();
    }

    public static String adminIdGen() {
        return "ADM-" + UUID.randomUUID();
    }

    public static String projectIdGen() {
        return "PRJ-" + UUID.randomUUID();
    }

    public static String documentIdGen() {
        return "DOC-" + UUID.randomUUID();
    }

    public static String milestoneIdGen() {
        return "MLS-" + UUID.randomUUID();
    }
    public static String userIdGen(){
        return "USR-"+ UUID.randomUUID();
    }

}
