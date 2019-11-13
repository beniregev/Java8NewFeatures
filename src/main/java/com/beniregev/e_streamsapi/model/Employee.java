package com.beniregev.e_streamsapi.model;

import lombok.*;

import javax.persistence.Id;

/**
 * <p>
 * Model {@code Employee} class used in demonstration
 * and examples of Java 8 Stream API.
 * </p>
 * @author Binyamin Regev e-mail: beniregev@gmail.com
 * @since 1.8
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String dept;
    private int salary;
    private boolean active;

    public String getFullName() {
        return this.getFirstName() + ' ' + this.getLastName();
    }
}
