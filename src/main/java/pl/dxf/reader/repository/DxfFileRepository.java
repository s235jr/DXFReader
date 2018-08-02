package pl.dxf.reader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dxf.reader.entity.User;
import pl.dxf.reader.parsefile.DxfFile;
import sun.text.normalizer.RangeValueIterator;

import java.util.List;

public interface DxfFileRepository extends JpaRepository<DxfFile, Long> {

    List<DxfFile> findDxfFileByRaportId(Long id);

}
