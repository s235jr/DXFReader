package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dxf.reader.entity.Raport;

import java.util.List;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {

    @Query("select max(id) from Raport ")
    Long getLastId();

    List<Raport> findRaportByUserId(Long id);
}

