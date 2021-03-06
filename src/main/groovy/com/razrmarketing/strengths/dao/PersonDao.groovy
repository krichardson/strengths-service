package com.razrmarketing.strengths.dao

import com.razrmarketing.strengths.api.Person
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.SqlUpdate
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(PersonMapper)
interface PersonDao {

    @SqlQuery("select p.* from person p order by p.name")
    List<Person> listPeople()

    @SqlQuery("select p.* from person p where p.id = :id")
    Person findById(@Bind("id") Long id)

    @SqlQuery("""select p.* from person p
                 where p.id in (
                    select distinct person_id from person_strength where strength_id = :strengthId
                 )
                 order by p.name""")
    List<Person> findByStrength(@Bind("strengthId") Long strengthId)

    @SqlUpdate('insert into person (name) values (:name)')
    @GetGeneratedKeys
    Long create(@Bind('name') String name)

    @SqlUpdate('update person set name = :name where id = :id')
    void update(@Bind('id') Long id, @Bind('name') String name)

}
