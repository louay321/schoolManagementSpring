package Louay.schoolManagement.repository;

import Louay.schoolManagement.domain.Student;
import Louay.schoolManagement.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where t.firstName =: name")
    Optional<Student> findByFirstName(String name);
}
