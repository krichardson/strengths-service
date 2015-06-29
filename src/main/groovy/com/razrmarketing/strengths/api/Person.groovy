package com.razrmarketing.strengths.api

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.Valid
import javax.validation.constraints.Size

class Person {

    Long id

    @Valid
    @NotBlank
    String name

    @Valid
    @NotEmpty
    @Size(min = 5, max = 5)
    List<PersonStrength> strengths

}
