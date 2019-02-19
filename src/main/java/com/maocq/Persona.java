package com.maocq;

import java.math.BigDecimal;

public class Persona {

    private String nombre;
    private Integer edad;
    private BigDecimal salario;

    public Persona(String nombre, Integer edad, BigDecimal salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
