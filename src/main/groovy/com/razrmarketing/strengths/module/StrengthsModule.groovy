package com.razrmarketing.strengths.module

import com.razrmarketing.strengths.api.Strength
import com.razrmarketing.strengths.dao.PersonDao
import com.razrmarketing.strengths.dao.StrengthDao

class StrengthsModule {

    private final StrengthDao strengthDao

    StrengthsModule(StrengthDao strengthDao) {
        this.strengthDao = strengthDao
    }

    List<Strength> listStrengths() {
        return strengthDao.listStrengths()
    }

    Strength getStrength(Long id) {
        return strengthDao.findById(id)
    }

}
