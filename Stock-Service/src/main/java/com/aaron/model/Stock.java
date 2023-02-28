package com.aaron.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
@Table(name = "collection_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private String company;

}
