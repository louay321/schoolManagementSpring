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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subjects_id")
    private String subjectsTaking;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "speciality", nullable = false)
    private String speciality;

    @Column(name = "class_number")
    private String classNumber;

    @Column(name = "expected_graduation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date expectedGraduation;
}
