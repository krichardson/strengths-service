package com.razrmarketing.strengths.module

import com.razrmarketing.strengths.api.Person
import com.razrmarketing.strengths.api.PersonStrength
import com.razrmarketing.strengths.dao.PersonDao
import com.razrmarketing.strengths.dao.PersonStrengthDao

class PeopleModule {

    private final PersonDao personDao
    private final PersonStrengthDao personStrengthDao

    PeopleModule(PersonDao personDao, PersonStrengthDao personStrengthDao) {
        this.personDao = personDao
        this.personStrengthDao = personStrengthDao
    }

    List<Person> listPeople() {
        return personDao.listPeople().collect { Person person ->
            populateStrengths(person)
        }
    }

    List<Person> findPeopleWithStrength(Long strengthId) {
        return personDao.findByStrength(strengthId).collect { Person person ->
            populateStrengths(person)
        }
    }

    Person createPerson(Person person) {

        Long personId = personDao.create(person.name)
        person.strengths.each { PersonStrength ps ->
            personStrengthDao.create(personId, ps.strength.id, ps.rank)
        }
        return getPerson(personId)

    }

    Person getPerson(Long id) {
        return populateStrengths(personDao.findById(id))
    }

    private Person populateStrengths(Person person) {
        person.strengths = personStrengthDao.findByPerson(person.id)
        return person
    }

}
