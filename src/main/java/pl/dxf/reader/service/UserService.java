package pl.dxf.reader.service;

import pl.dxf.reader.entity.User;

public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);

}
