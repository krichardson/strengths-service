package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.Person
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper

import java.sql.ResultSet
import java.sql.SQLException

class PersonMapper implements ResultSetMapper<Person> {
    public Person map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Person(
                id: resultSet.getLong('id'),
                name: resultSet.getString('name')
        )
    }
}
