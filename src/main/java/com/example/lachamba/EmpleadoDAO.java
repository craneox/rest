package com.example.lachamba;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmpleadoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String correo;
    private String cargo;
    private BigDecimal salario_mensual;
    private Integer hora_entrada;
    private Integer hora_salida;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setApellido_Paterno(String apellido_paterno){
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_Paterno(){
        return apellido_paterno;
    }

    public void setApellido_Materno(String apellido_materno){
        this.apellido_materno = apellido_materno;
    }

    public String getApellido_Materno(){
        return apellido_materno;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public String getCargo(){
        return cargo;
    }

    public void setSalario_Mensual(BigDecimal salario_mensual){
        this.salario_mensual = salario_mensual;
    }

    public BigDecimal getSalario_Mensual(){
        return salario_mensual;
    }

    public void setHora_Entrada(Integer hora_entrada){
        this.hora_entrada = hora_entrada;
    }

    public Integer getHora_Entrada(){
        return hora_entrada;
    }

    public void setHora_Salida(Integer hora_salida){
        this.hora_salida = hora_salida;
    }

    public Integer getHora_Salida(){
        return hora_salida;
    }
}
