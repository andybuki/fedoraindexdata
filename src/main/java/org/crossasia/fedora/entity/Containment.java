package org.crossasia.fedora.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "containment")
public class Containment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fedora_id" , unique = true)
    private String fedora_id;


    @Column(name = "parent")
    private String parent;

    @Column(name = "start_time")
    private Date start_time;

    @Column(name = "end_time")
    private Date end_time;

    @Column(name = "updated")
    private Date updated;

    public Containment() {
    }


    public Containment(String fedora_id, String parent, Date start_time, Date end_time, Date updated) {
        this.fedora_id = fedora_id;
        this.parent = parent;
        this.start_time = start_time;
        this.end_time = end_time;
        this.updated = updated;
    }


    public String getFedora_id() {
        return fedora_id;
    }
    public void setFedora_id(String fedora_id) {
        this.fedora_id = fedora_id;
    }


    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }


    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }


    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    @Override
    public String toString() {
        return "Containment{" +
                "fedora_id='" + fedora_id + '\'' +
                ", parent='" + parent + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", updated=" + updated +
                '}';
    }
}
