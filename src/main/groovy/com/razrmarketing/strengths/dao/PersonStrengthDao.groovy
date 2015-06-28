package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.PersonStrength
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(PersonStrengthMapper)
interface PersonStrengthDao {

    @SqlQuery("""select * from person_strength ps
                    join strength s
                        on ps.strength_id = s.id
                 where ps.person_id = :personId
                    order by ps.rank""")
    List<PersonStrength> findByPerson(@Bind("personId") Long personId)

}
