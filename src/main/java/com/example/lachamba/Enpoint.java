package com.example.lachamba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.Optional;

@Endpoint
public class Enpoint {
    @Autowired
    private IEmpleados iEmpleados;

    @PayloadRoot (namespace = "http://www.example.com/lachamba", localPart = "NuevoEmpleadoRequest")

    @ResponsePayload
    public NuevoEmpleadoResponse nuevoEmpleado(@RequestPayload NuevoEmpleadoRequest peticion){
        NuevoEmpleadoResponse respuesta = new NuevoEmpleadoResponse();
        respuesta.setRespuesta("Registro de "+peticion.getNombre()+" exitoso");
        EmpleadoDAO empleado = new EmpleadoDAO();
        empleado.setNombre(peticion.getNombre());
        empleado.setApellido_Paterno(peticion.getApellidoPaterno());
        empleado.setApellido_Materno(peticion.getApellidoMaterno());
        empleado.setCorreo(peticion.getCorreo());
        empleado.setCargo(peticion.getCargo());
        empleado.setSalario_Mensual(peticion.getSalarioMensual());
        empleado.setHora_Entrada(peticion.getHoraEntrada());
        empleado.setHora_Salida(peticion.getHoraSalida());
        iEmpleados.save(empleado);
        return respuesta;
    }

    @PayloadRoot (namespace = "http://www.example.com/lachamba", localPart = "EmpleadosRegistradosRequest")

    @ResponsePayload
    public EmpleadosRegistradosResponse empleadosRegistrados(){
        EmpleadosRegistradosResponse resultado = new EmpleadosRegistradosResponse();
        Iterable<EmpleadoDAO>empleados = iEmpleados.findAll();
        for(EmpleadoDAO ls : empleados){
            EmpleadosRegistradosResponse.Empleado empleado = new EmpleadosRegistradosResponse.Empleado();
            empleado.setNombre(ls.getNombre());
            empleado.setId(ls.getId());
            resultado.getEmpleado().add(empleado);
        }
        return resultado;
    }
    
    @PayloadRoot (namespace = "http://www.example.com/lachamba", localPart = "EliminarEmpleadoRequest")

    @ResponsePayload
    public EliminarEmpleadoResponse eliminarEmpleado(@RequestPayload EliminarEmpleadoRequest peticion){
        EliminarEmpleadoResponse resultado = new EliminarEmpleadoResponse();
        Iterable<EmpleadoDAO>empleados = iEmpleados.findAll();
        int validador = 0;
        for(EmpleadoDAO ls : empleados){
            if(ls.getId() == peticion.getId()){
                iEmpleados.deleteById(peticion.getId());
                validador=1;
            }
        }
        if(validador == 1 ){
            resultado.setRespuesta("Empleado eliminado con exito");
        }else{
            resultado.setRespuesta("No se pudo eliminar a este empleado");
        }

        // Optional<Saludadores> i = isa.findById(peticion.getId());
        // if(i.isPresent()){
        //     isa.deleteById(peticion.getId());
        //     resultado.setRespuesta("Acualizado");
        // }else{
        //     resultado.setRespuesta("No se encontro ID");
        // }

        return resultado;
    }

    @PayloadRoot (namespace = "http://www.example.com/lachamba", localPart = "ActualizarEmpleadoRequest")

    @ResponsePayload
    public ActualizarEmpleadoResponse actualizarEmpleado(@RequestPayload ActualizarEmpleadoRequest peticion){
        ActualizarEmpleadoResponse resultado = new ActualizarEmpleadoResponse();
        Iterable<EmpleadoDAO>empleados = iEmpleados.findAll();
        int validador = 0;
        for(EmpleadoDAO ls : empleados){
            if(ls.getId() == peticion.getId()){
                EmpleadoDAO empleado = new EmpleadoDAO();
                empleado.setNombre(ls.getNombre());
                empleado.setApellido_Paterno(ls.getApellido_Paterno());
                empleado.setApellido_Materno(ls.getApellido_Materno());
                empleado.setId(peticion.getId());
                empleado.setCorreo(peticion.getCorreo());
                empleado.setCargo(peticion.getCargo());
                empleado.setSalario_Mensual(peticion.getSalarioMensual());
                empleado.setHora_Entrada(peticion.getHoraEntrada());                
                empleado.setHora_Salida(peticion.getHoraSalida());
                iEmpleados.save(empleado);
                validador=1;
            }
        }
        if(validador == 1 ){
            resultado.setRespuesta("Datos actualizados");
        }else{
            resultado.setRespuesta("No se pudo actualizar");
        }
        
        // Optional<Saludadores> i = isa.findById(peticion.getId());
        // if(i.isPresent()){
        //     Saludadores salu = new Saludadores();
        //     salu = i.get();
        //     salu.setNombre(peticion.getNombre());
        //     isa.save(salu);
        //     resultado.setRespuesta("Acualizado");
        // }else{
        //     resultado.setRespuesta("No se encontro ID");
        // }
        return resultado;
    }

    @PayloadRoot (namespace = "http://www.example.com/lachamba", localPart = "BuscarEmpleadoRequest")

    @ResponsePayload
    public BuscarEmpleadoResponse buscarSaludo(@RequestPayload BuscarEmpleadoRequest peticion){
        BuscarEmpleadoResponse respuesta = new BuscarEmpleadoResponse();
        EmpleadoDAO empleado = new EmpleadoDAO();
        empleado = iEmpleados.findByCargo(peticion.getCargo());
        respuesta.setId(empleado.getId());
        respuesta.setRespuesta(empleado.getNombre());
        return respuesta;
    }
}
