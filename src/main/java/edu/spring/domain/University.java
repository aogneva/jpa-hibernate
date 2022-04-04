package edu.spring.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="training")
@SequenceGenerator(
        name ="TrainingSeq",
        sequenceName = "training_seq",
        initialValue = 1000,
        allocationSize = 10
)
public class University {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "TrainingSeq"
    )
    private Integer id;

    @Column(name="short_name")
    private String shortName;

    @Column(name="full_name")
    private String fullName;

    @OneToMany(mappedBy="university")
    private List<Person> students;

    public University() {}

    public University(String shortName, String fullName){
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public University(Integer id, String shortName, String fullName){
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Person> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}