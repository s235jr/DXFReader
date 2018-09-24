package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dxf.reader.entity.Status;
import pl.dxf.reader.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByValue(String string);

}
