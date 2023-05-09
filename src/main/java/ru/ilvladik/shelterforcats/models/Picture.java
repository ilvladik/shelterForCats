package ru.ilvladik.shelterforcats.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private long size;

    @Column(name = "data")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Cat cat;

    public Picture(MultipartFile file) throws IOException {
        this.contentType = file.getContentType();
        this.size = file.getSize();
        this.data = file.getBytes();
    }
}
