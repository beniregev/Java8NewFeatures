package com.beniregev.e_streamsapi.model;

import lombok.*;

/**
 * <p>  Model {@code Employee} class used in demonstration and examples
 *      of Java 8 Stream API. </p>
 * <p>
 *     <ul>Using {@code Data} annotation includes the following annotaions inside:
 *     <li>Getter</li>
 *     <li>Setter</li>
 *     <li>EqualsAndHashCode</li>
 *     <li>ToString</li>
 *     <li>RequiredArgsConstructor</li>
 *     <li>lombok.Value</li>
 *     </ul>
 * </p>
 * @author Binyamin Regev
 * @since 1.8
 */
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
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
