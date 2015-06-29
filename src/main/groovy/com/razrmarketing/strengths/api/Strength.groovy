package com.razrmarketing.strengths.api

import javax.validation.Valid
import javax.validation.constraints.NotNull

class Strength {

    @Valid
    @NotNull
    Long id
    String name
    String description
    String color

}
