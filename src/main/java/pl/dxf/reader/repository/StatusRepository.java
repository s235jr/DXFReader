package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dxf.reader.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
