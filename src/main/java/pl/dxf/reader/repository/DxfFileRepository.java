package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dxf.reader.parsefile.DxfFile;

import java.util.List;

@Repository
public interface DxfFileRepository extends JpaRepository<DxfFile, Long> {

    List<DxfFile> findDxfFileByRaportId(Long id);

}
