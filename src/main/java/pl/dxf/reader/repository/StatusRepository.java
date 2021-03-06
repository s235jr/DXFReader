package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dxf.reader.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
