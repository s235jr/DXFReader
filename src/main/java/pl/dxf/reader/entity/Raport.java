package pl.dxf.reader.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name="raports")
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdDate;
    private Date updatedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Status status;
    private String description;
    private long numberOfDxfFile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getNumberOfDxfFile() {
        return numberOfDxfFile;
    }

    public void setNumberOfDxfFile(long numberOfDxfFile) {
        this.numberOfDxfFile = numberOfDxfFile;
    }

    @Override
    public String toString() {
        return "Raport{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", user=" + user +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", numberOfDxfFile=" + numberOfDxfFile +
                '}';
    }
}
