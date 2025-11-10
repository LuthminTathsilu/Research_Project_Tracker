package lk.ijse.CMJD110.Research_Project_Tracker.Entity.secure;

import jakarta.persistence.*;
import lk.ijse.CMJD110.Research_Project_Tracker.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class SecureUserEntity implements Serializable, UserDetails {
    @Id
    private String id;
    private String username;
    private String password;
    private String fullname;
    private Role role;
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

}
