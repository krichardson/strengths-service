package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.Strength
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(StrengthMapper)
interface StrengthDao {

    @SqlQuery("select * from strength order by name")
    List<Strength> listStrengths()
    
    @SqlQuery("select * from strength where id = :id")
    Strength findById(@Bind("id") Long id)

}
