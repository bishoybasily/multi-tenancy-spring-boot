package com.gmail.bishoybasily.manager.model.entity;

import com.github.bishoybasily.springframework.commons.core.data.Updatable;
import com.github.bishoybasily.springframework.commons.jpa.generator.DatabaseIdGenerator;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author bishoybasily
 * @since 2020-12-12
 */
@Data
@Accessors(chain = true)
@Entity(name = "tenants")
public class Tenant implements Updatable<Tenant> {

    @Id
    @GeneratedValue(generator = DatabaseIdGenerator.NAME)
    @GenericGenerator(name = DatabaseIdGenerator.NAME, strategy = DatabaseIdGenerator.CLASS)
    private String id;

}
