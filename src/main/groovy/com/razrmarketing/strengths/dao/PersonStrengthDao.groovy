package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.PersonStrength
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(PersonStrengthMapper)
interface PersonStrengthDao {

    @SqlQuery("""select * from person_strength ps
                    join strength s
                        on ps.strength_id = s.id
                 where ps.person_id = :personId
                    order by ps.rank""")
    List<PersonStrength> findByPerson(@Bind("personId") Long personId)

    @SqlUpdate('insert into person_strength (person_id, strength_id, rank) values (:personId, :strengthId, :rank)')
    @GetGeneratedKeys
    Long create(@Bind('personId') Long personId, @Bind('strengthId') Long strengthId, @Bind('rank') Integer rank)

    @SqlUpdate('delete from person_strength where person_id = :personId')
    void clear(@Bind('personId') Long personId)

}
