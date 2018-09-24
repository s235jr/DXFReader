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
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private int id;
    @Column(unique = true)
    private String value;
    @Enumerated(EnumType.STRING)
    private Action action;
    @ManyToOne
    private User user;
    private Timestamp createdDate;

    public enum Action{
        ACTIVATEACC,
        RESETPASS
    }

}
