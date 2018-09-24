package pl.dxf.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication

public class DxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(DxfApplication.class, args);
    }

}
