package com.gmail.bishoybasily.application.model.entity;

import com.github.bishoybasily.springframework.commons.jpa.generator.DatabaseIdGenerator;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author bishoybasily
 * @since 2020-12-07
 */
@Data
@Accessors(chain = true)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = DatabaseIdGenerator.NAME)
    @GenericGenerator(name = DatabaseIdGenerator.NAME, strategy = DatabaseIdGenerator.CLASS)
    private String id;
    private String name;

}
