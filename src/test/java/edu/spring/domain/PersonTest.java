package edu.spring.domain;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import edu.spring.repostory.PersonRepository;
import edu.spring.repostory.UniversityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonTest {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private HibernateQueryInterceptor hibernateQueryInterceptor;

    @BeforeAll
    private static void createBase(
            @Autowired UniversityRepository universityRepository,
            @Autowired PersonRepository repository
    ) {
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
    }

    @Test
    @DisplayName("GET ALL !!!")
    public void getAll() {
        System.out.println("----------------------------------");
        assertThat(universityRepository.findAll()).isNotEmpty();
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    @DisplayName("get by name")
    public void getByNameUniversity() {
        System.out.println("----------------------------------");
        Optional<University> psu = universityRepository.findByShortName("ПГУ");
        assertThat(psu.isPresent());
        assertThat(psu.get().getFullName().equalsIgnoreCase("Пермский государственный университет"));
    }

    @Test
    @DisplayName("N+1")
    public void findOneProblemN1() {
        System.out.println("----------------N+1------------------");
        hibernateQueryInterceptor.startQueryCount();
        List<Person> list = repository.findAll();
        assertThat(!list.isEmpty());
        Assertions.assertNotNull(list.get(0).getUniversity().getShortName());
        assertThat(list.get(0).getUniversity().getShortName().equalsIgnoreCase("ПГУ"));

        assertThat(hibernateQueryInterceptor.getQueryCount()).isStrictlyBetween(2L,10L);
        System.out.println(list);
    }

    @Test
    @DisplayName("N+1 solved")
    public void findOneProblemN1Solved() {
        System.out.println("----------------N+1------------------");
        hibernateQueryInterceptor.startQueryCount();
        List<Person> list = repository.findAllFetch();
        assertThat(!list.isEmpty());
        Assertions.assertNotNull(list.get(0).getUniversity().getShortName());
        assertThat(list.get(0).getUniversity().getShortName().equalsIgnoreCase("ПГУ"));
        assertThat(hibernateQueryInterceptor.getQueryCount()).isEqualTo(1);
        System.out.println(list);
    }

}
