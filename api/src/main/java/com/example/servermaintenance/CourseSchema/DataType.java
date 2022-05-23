package com.example.servermaintenance.CourseSchema;

import com.opencsv.bean.CsvIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "data_type")
public class DataType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "dataType")
    private List<CourseSchemaPart> courseSchemaParts;
}
