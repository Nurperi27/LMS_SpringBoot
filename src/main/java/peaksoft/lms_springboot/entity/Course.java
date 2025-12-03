package peaksoft.lms_springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    @Column(name = "id")
    Long idCourse;
    @Column(name = "title_course")
    String titleCourse;
    String image;
    @Column(name = "start_date")
    LocalDate startDate;
    String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH,  CascadeType.DETACH})
    List<Instructor> instructors; //bi

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    List<Lesson> lessons; //bi

    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    List<Student> students = new ArrayList<>(); //bi

    public Course(String titleCourse, String image, LocalDate startDate, String description) {
        this.titleCourse = titleCourse;
        this.image = image;
        this.startDate = startDate;
        this.description = description;
    }
}
