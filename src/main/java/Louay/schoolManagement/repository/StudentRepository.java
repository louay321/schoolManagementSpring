package Louay.schoolManagement.repository;

import Louay.schoolManagement.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student where s.firstName =: name")
    Optional<Student> findByFirstName(String name);
}
