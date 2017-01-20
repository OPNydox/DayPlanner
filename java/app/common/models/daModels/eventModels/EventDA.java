package app.common.models.daModels.eventModels;

import app.common.enums.Marker;
import app.common.models.daModels.userModels.UserDA;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by Ico on 27.12.2016 г..
 */
@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class EventDA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateTime;

    @Column(name = "description")
    private String description;

    @Enumerated
    private Marker marker;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserDA owner;

    public EventDA() {
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

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public UserDA getOwner() {
        return owner;
    }

    public void setOwner(UserDA owner) {
        this.owner = owner;
    }
}
