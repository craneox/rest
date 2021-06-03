package com.example.lachamba;

import org.springframework.data.repository.CrudRepository;

public interface IEmpleados extends CrudRepository<EmpleadoDAO,Integer>{
    EmpleadoDAO findByCargo(String cargo);
    //findByNombreAndId
}
