package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.PersonStrength
import com.razrmarketing.strengths.api.Strength
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper

import java.sql.ResultSet
import java.sql.SQLException

class PersonStrengthMapper implements ResultSetMapper<PersonStrength> {
    public PersonStrength map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new PersonStrength(
                strength: new Strength(
                        id: resultSet.getLong('strength_id'),
                        name: resultSet.getString('name'),
                        description: resultSet.getString('description'),
                        color: resultSet.getString('color')
                ),
                rank: resultSet.getInt('rank')
        )
    }
}
