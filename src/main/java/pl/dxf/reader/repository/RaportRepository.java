package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.dxf.reader.entity.Raport;
import pl.dxf.reader.entity.User;

import java.util.List;

public interface RaportRepository extends JpaRepository<Raport, Long> {

    @Query("select max(id) from Raport ")
    Long getLastId();

}

