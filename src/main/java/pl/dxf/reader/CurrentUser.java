package pl.dxf.reader;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@ToString
public class CurrentUser extends User {

    private final pl.dxf.reader.entity.User user;

    public CurrentUser(String username, String password, Collection<?
            extends GrantedAuthority> authorities, pl.dxf.reader.entity.User user) {
        super(username, password, authorities);
        this.user = user;
        this.user.setPassword("PROTECTED");
    }

    public pl.dxf.reader.entity.User getUser() {
        return user;
    }


}
