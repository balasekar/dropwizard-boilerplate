package com.product.app.resources;


import com.product.app.core.Person;
import com.product.app.dao.PersonDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {

    PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GET
    @Path("/create")
    public void getCreate(){
        personDAO.createPersonTable();
    }

    @GET
    public List<Person> getAll(){
        return personDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") Integer id){
        return personDAO.findById(id);
    }

    @POST
    public Person add(@Valid Person person) {
        personDAO.insert(person);

        return person;
    }

    @PUT
    @Path("/{id}")
    public Person update(@PathParam("id") Integer id, @Valid Person person) {
        Person updatePerson = new Person(id, person.getName());

        personDAO.update(updatePerson);

        return updatePerson;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        personDAO.deleteById(id);
    }
}