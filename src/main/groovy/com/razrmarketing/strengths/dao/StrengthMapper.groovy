package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.Strength
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper

import java.sql.ResultSet
import java.sql.SQLException

class StrengthMapper implements ResultSetMapper<Strength> {
    public Strength map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Strength(
                id: resultSet.getLong('id'),
                name: resultSet.getString('name'),
                description: resultSet.getString('description'),
                color: resultSet.getString('color')
        )
    }
}
