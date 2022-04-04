package edu.spring.repostory;

import edu.spring.domain.Person;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //void insert(Person p);

    Optional<Person> findById(int i);

//    @Query("select p from Person p where p.name=:personName join fetch p.university")
    Optional<Person> findByName(String personName);

    //Person getFirst();

    List<Person> findAll();

    @Query("select p from Person p join fetch p.university")
    List<Person> findAllFetch();

    //Person getByName(String name);
}
