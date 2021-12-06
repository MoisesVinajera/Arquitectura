package com.example.SpringProfessional.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Representa a la capa de persistencia con la que se accede a la base de datos. Es la entidad encargada de
 * comunicarse directamente con la base de datos. Es la capa del ORM.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Verifica si un email está actualmente registrado en la base de datos.
     * @param email - Representa al email que se quiere localizar en la base de datos
     * @return - Devuelve un booleano verdadero si se encontró en la base de datos
     */
    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Student s " +
            "WHERE s.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    /**
     * Elimina todos los estudiantes seleccionados de la base de datos a partir de una serie de ids almacenados
     * en una lista.
     * @param ids Representa la lista de estudiantes que se quieren eliminar
     */
    void deleteByIdIn(List<Long> ids);
}
