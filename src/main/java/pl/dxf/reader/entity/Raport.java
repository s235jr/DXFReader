package pl.dxf.reader.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@Table(name="raports")
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Status status;
    private String description;
    private long numberOfDxfFile;

}
