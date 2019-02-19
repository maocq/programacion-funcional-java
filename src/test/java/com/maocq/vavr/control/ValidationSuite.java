package com.maocq.vavr.control;

import com.maocq.Persona;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationSuite {

    @Test
    public void testValid() {
        String nombre = "Mauricio";
        Integer edad = 20;
        BigDecimal salario = new BigDecimal("100");

        Validation<Seq<String>, Persona> persona = Validation.combine(
          validarNombre(nombre),
          validarEdad(edad),
          validarSalario(salario)
        ).ap((n, e, s) -> new Persona(n, e, s));

        assertTrue("", persona.isValid());
        assertEquals("", nombre, persona.get().getNombre());
    }

    @Test
    public void testInvalid() {
        String nombre = null;
        Integer edad = 10;
        BigDecimal salario = new BigDecimal("100");

        Validation<Seq<String>, Persona> persona = Validation.combine(
          validarNombre(nombre),
          validarEdad(edad),
          validarSalario(salario)
        ).ap((n, e, s) -> new Persona(n, e, s));

        assertTrue("", persona.isInvalid());
        assertEquals("", 2, persona.getError().length());
    }

    private Validation<String, String> validarNombre(String nombre) {
        return nombre == null ? Validation.invalid("Error nombre") : Validation.valid(nombre);
    }

    private Validation<String, Integer> validarEdad(Integer edad) {
        return edad == null || edad < 18 ? Validation.invalid("Error edad") : Validation.valid(edad);
    }

    private Validation<String, BigDecimal> validarSalario(BigDecimal salario) {
        return salario == null ? Validation.invalid("Error nombre") : Validation.valid(salario);
    }

}
