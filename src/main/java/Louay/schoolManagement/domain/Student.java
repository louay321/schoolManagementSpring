package Louay.schoolManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Entity
@Table(name = Student.TABLE_NAME)

public class Student extends Person{

    public static final String TABLE_NAME = "student";

    @Column(name = "student_id", nullable = false, unique = true)
    private String studentID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "subjects_taking")
    private String subjectsTaking;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "speciality")
    private String speciality;

    @Column(name = "class_number")
    private String classNumber;

    @Column(name = "expected_graduation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date expectedGraduation;
}
