package ru.ilvladik.shelterforcats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min=2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Необходимо ввести дату рождения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "description")
    private String description;

    @NotNull(message = "Необходимо добавить фотографию")
    @OneToOne(mappedBy = "cat", cascade = CascadeType.ALL)
    private Picture picture;

    @OneToMany(mappedBy = "cat")
    private List<Application> people;

    public void setPicture(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            this.picture = new Picture(file);
            this.picture.setCat(this);
        }
    }
}
