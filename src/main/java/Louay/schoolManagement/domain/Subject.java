package Louay.schoolManagement.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
@Entity(name = Subject.TABLE_NAME)

public class Subject extends AbstractEntity<Long>{

    public static final String TABLE_NAME = "subject";

    @Column( name = "subject_id", nullable = false, unique = true)
    private String subjectID;

    @Column( name = "credits")
    private int credits;

    @Column( name = "max_students")
    private int maxStudents;


}
