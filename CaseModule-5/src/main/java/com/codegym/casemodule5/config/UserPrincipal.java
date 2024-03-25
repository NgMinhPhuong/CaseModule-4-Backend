package com.codegym.casemodule5.config;
import com.codegym.casemodule5.model.Role;
import com.codegym.casemodule5.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        String roleName = user.getRole().getName();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (Optional.ofNullable(roleName).isPresent()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
            grantedAuthorities.add(grantedAuthority);
        }
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPrincipal that = (UserPrincipal) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities);
    }

//    public static UserPrincipal build(User user){
//        List<GrantedAuthority> authorities1 = new ArrayList<>();
//        for (Role role : user.getRole()){
//            authorities1.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return new UserPrincipal(user.getUsername(),user.getPassword(),authorities1);
//    }
}