package Louay.schoolManagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Entity
@Table(name = Teacher.TABLE_NAME)

public class Teacher extends Person {

    public static final String TABLE_NAME = "teacher";

    @Column(name = "teacher_id", nullable = false, unique = true)
    private String teacherID;

    @Column(name = "primary_subject", nullable = false)
    private String primarySubject;

    @Column(name = "secondary_subject")
    private String secondarySubject;

    @Column(name = "students_supervising")
    private String studentsSupervising;
    /*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "primary_subject", nullable = false)
    private Subject primarySubject;

    //Each Teacher will have one primary subject
    //might have a secondary one.
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "secondary_subject")
    private Subject secondarySubject;

    //Teachers can supervise students (one-2-many) relation
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student studentsSupervising;
*/
}
