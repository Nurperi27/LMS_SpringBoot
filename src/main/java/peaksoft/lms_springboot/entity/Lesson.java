package peaksoft.lms_springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    @Column(name = "id")
    Long idLesson;
    @Column(name = "title_lesson")
    String titleLesson;
    @Column(name = "publisher_date")
    LocalDate publisherDate;
    @Column(name = "description")
    String descriptionLesson;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,  CascadeType.REFRESH})
    List<Course> courses = new ArrayList<>(); //bi

    public Lesson(String titleLesson, LocalDate publisherDate, String descriptionLesson) {
        this.titleLesson = titleLesson;
        this.publisherDate = publisherDate;
        this.descriptionLesson = descriptionLesson;
    }
}
