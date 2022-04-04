package edu.spring.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "person")
@SequenceGenerator(
        name = "PerSeq",
        sequenceName = "person_seq",
        initialValue = 1000,
        allocationSize = 10
)
public class Person {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PerSeq"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="university_id", referencedColumnName = "id")
    private University university;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, University university) {
        this.name = name;
        this.university = university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university=" + university.getShortName() +
                "}";
    }
}
