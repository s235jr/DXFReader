package pl.dxf.reader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.dxf.reader.entity.Status;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.repository.StatusRepository;
import pl.dxf.reader.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User convert(String s) {
        return userRepository.findOne(Long.valueOf(s));
    }
}
