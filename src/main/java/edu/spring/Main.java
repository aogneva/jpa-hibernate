package edu.spring;

import edu.spring.domain.University;
import edu.spring.repostory.UniversityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import edu.spring.repostory.PersonRepository;
import edu.spring.domain.Person;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository repository = context.getBean(PersonRepository.class);
        UniversityRepository universityRepository = context.getBean(UniversityRepository.class);

        University psu = new University("ПГУ", "Пермский государственный университет");
        University msu = new University("МГУ", "Московский государственный университет");
        University usu = new University("УрГУ", "Уральский государственный университет");
        universityRepository.save(psu);
        universityRepository.save(msu);
        universityRepository.save(usu);
        repository.save(new Person("Petrov", psu));
        repository.save(new Person("Kuznetsov", psu));
        repository.save(new Person("Maltseva", msu));
        repository.save(new Person("Nazarov", msu));
        repository.save(new Person("Vasiliev", msu));
        repository.save(new Person("Monyakova", msu));
        repository.save(new Person("Kim", usu));
        repository.save(new Person("Ognev", usu));
//
//        Person pPerson = repository.findByName("Petrov").orElseThrow(RuntimeException::new);
//        System.out.println(pPerson.getId() + pPerson.getName());
//
        System.out.println("----------------------------");
//        Optional<University> university = universityRepository.findByShortName("ПГУ");
//        System.out.println(Optional.of(university).map(Optional::get).map(University::toString).get());
        List<Person> people = repository.findAll();
        people.forEach(System.out::println);
    }
}
