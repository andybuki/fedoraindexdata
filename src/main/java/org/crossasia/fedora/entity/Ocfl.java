package org.crossasia.fedora.entity;

import javax.persistence.*;

@Entity
@Table(name = "ocfl_id_map")
public class Ocfl {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "fedora_id")
    private String fedora_id;

    @Column(name = "fedora_root_id")
    private String fedora_root_id;

    @Column(name = "ocfl_id")
    private String ocfl_id;

    public Ocfl() {
    }

    public Ocfl(String fedora_id, String fedora_root_id, String ocfl_id) {
        this.fedora_id = fedora_id;
        this.fedora_root_id = fedora_root_id;
        this.ocfl_id = ocfl_id;
    }

    public String getFedora_id() {
        return fedora_id;
    }

    public void setFedora_id(String fedora_id) {
        this.fedora_id = fedora_id;
    }

    public String getFedora_root_id() {
        return fedora_root_id;
    }

    public void setFedora_root_id(String fedora_root_id) {
        this.fedora_root_id = fedora_root_id;
    }

    public String getOcfl_id() {
        return ocfl_id;
    }

    public void setOcfl_id(String ocfl_id) {
        this.ocfl_id = ocfl_id;
    }

    @Override
    public String toString() {
        return "Ocfl{" +
                "fedora_id='" + fedora_id + '\'' +
                ", fedora_root_id='" + fedora_root_id + '\'' +
                ", ocfl_id='" + ocfl_id + '\'' +
                '}';
    }
}
