package app.common.models.daModels.userModels;

import app.common.models.daModels.eventModels.EventDA;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private int password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<EventDA> events;

    public UserDA() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public List<EventDA> getEvents() {
        return events;
    }

    public void setEvents(List<EventDA> events) {
        this.events = events;
    }
}
