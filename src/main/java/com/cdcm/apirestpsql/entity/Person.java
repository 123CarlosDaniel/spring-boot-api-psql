package com.cdcm.apirestpsql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class Person {
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "person_id",
            referencedColumnName = "id"
    )
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(email, person.email) && Objects.equals(password, person.password) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }
}
