package com.razrmarketing.strengths.api

import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class PersonStrength {

    @Valid
    @NotNull
    Strength strength

    @Valid
    @Max(5L)
    @Min(1L)
    Integer rank

}
