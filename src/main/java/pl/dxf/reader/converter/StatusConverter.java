package pl.dxf.reader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.dxf.reader.entity.Status;
import pl.dxf.reader.repository.StatusRepository;

public class StatusConverter implements Converter<String, Status> {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public Status convert(String s) {
        return statusRepository.findOne(Long.valueOf(s));
    }
}
