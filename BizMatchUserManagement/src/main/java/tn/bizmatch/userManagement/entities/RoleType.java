package tn.bizmatch.userManagement.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum RoleType {
    ADMIN(
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_POST,
                    Permission.ADMIN_PUT,
                    Permission.ADMIN_DELETE,
                    Permission.Investor_READ,
                    Permission.Investor_POST,
                    Permission.Investor_PUT,
                    Permission.Investor_DELETE,
                    Permission.Collaborator_READ,
                    Permission.Collaborator_POST,
                    Permission.Collaborator_PUT,
                    Permission.Collaborator_DELETE,
                    Permission.Entrepreneur_READ,
                    Permission.Entrepreneur_POST,
                    Permission.Entrepreneur_PUT,
                    Permission.Entrepreneur_DELETE,
                    Permission.CRepresentative_READ,
                    Permission.CRepresentative_POST,
                    Permission.CRepresentative_PUT,
                    Permission.CRepresentative_DELETE
                    )
            ))

    ),
    Investor(
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Permission.Investor_READ,
                    Permission.Investor_POST,
                    Permission.Investor_PUT,
                    Permission.Investor_DELETE)
            ))
    ),
    Collaborator(
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Permission.Collaborator_READ,
                    Permission.Collaborator_POST,
                    Permission.Collaborator_PUT,
                    Permission.Collaborator_DELETE)
            ))
    ),
    Entrepreneur(
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Permission.Entrepreneur_READ,
                    Permission.Entrepreneur_POST,
                    Permission.Entrepreneur_PUT,
                    Permission.Entrepreneur_DELETE)
            ))
    ),

        CRepresentative(
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    Permission.CRepresentative_READ,
                    Permission.CRepresentative_POST,
                    Permission.CRepresentative_PUT,
                    Permission.CRepresentative_DELETE)
            ))
    );


    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getUserAutohrities(){
        List<SimpleGrantedAuthority> authorities= getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
