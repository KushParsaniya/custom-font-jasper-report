package dev.kush.jasperdemo1;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Employee{
        private Integer employeeId;
        private String firstName;
        private String lastName;
        private String email;
}
