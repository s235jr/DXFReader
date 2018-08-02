package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dxf.reader.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


}
