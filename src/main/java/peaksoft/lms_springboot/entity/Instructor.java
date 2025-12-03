package peaksoft.lms_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_gen", sequenceName = "instructor_seq", allocationSize = 1)
    @Column(name = "id")
    Long idInst;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
   // @Column(unique = true, columnDefinition = "varchar(255) check(email like '%@%')") //ERROR because of columnDefinition
    @Email(message = "Email must contain @") //валидация
    @Column(unique = true, length = 255)
    String email;
    //@Column(name = "phone_number", columnDefinition = "varchar(50) check(phone_number like '+%')")
    @Pattern(regexp = "\\+.*", message = "Phone number must start with +") //валидация
    @Column(name = "phone_number", length = 50)
    String phoneNumber;

    @ManyToMany(mappedBy = "instructors", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    List<Course> courses; //bi

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
