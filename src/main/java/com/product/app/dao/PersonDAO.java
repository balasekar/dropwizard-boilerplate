package com.product.app.dao;

import com.product.app.core.Person;
import com.product.app.core.mapper.PersonMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(PersonMapper.class)
public interface PersonDAO {

    @SqlUpdate("create table PERSON (id int primary key, name varchar(100))")
    void createPersonTable();

    @SqlQuery("select * from PERSON")
    List<Person> getAll();

    @SqlQuery("select * from PERSON where ID = :id")
    Person findById(@Bind("id") int id);

    @SqlUpdate("delete from PERSON where ID = :id")
    int deleteById(@Bind("id") int id);

    @SqlUpdate("update PERSON set NAME = :name where ID = :id")
    int update(@BindBean Person person);

    @SqlUpdate("insert into PERSON (ID, NAME) values (:id, :name)")
    int insert(@BindBean Person person);
}