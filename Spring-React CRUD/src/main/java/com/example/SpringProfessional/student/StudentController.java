package com.example.SpringProfessional.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Provee acceso al comportamiento del sistema backend por medio de la asignación de servicios específicos
 * a partir de las peticiones que llegan a través del cliente y verifica que los datos enviados por el cliente
 * sean válidos.
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Intercepta las peticiones GET en el endpoint "api/v1/students" y asigna al servicio específico
     * (StudentService - getAllStudents) para devolver todos los estudiantes almacenados en la base de datos
     * @return - Devuelve una lista con todos los estudiantes.
     */
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    /**
     * Intercepta las peticiones POST en el endpoint "api/v1/students" y asigna al servicio específico
     * (StudentService - addStudent) para guardar un nuevo estudiante en la base de datos.
     * @param student - Representa al estudiante que se quiere dar de alta en el sistema
     */
    @PostMapping
    private void addStudent(@Valid @RequestBody Student student){
        studentService.addStudent(student);
    }

    /**
     * Intercepta las peticiones PUT en el endpoint "api/v1/students/{studentId}" y asigna al servicio específico
     * (StudentService - updateStudent) para actualizar los datos de un estudiante existente en la base de datos.
     * @param studentId - Representa el Id del estudiante
     * @param request - Representa los datos que se quieren actualizar del estudiante
     */
    @PutMapping(path = "{studentId}")
    private void updateStudent(@Valid @PathVariable("studentId") Long studentId , @RequestBody StudentRequest request){
        studentService.updateStudent(studentId,request);
    }

    /**
     * Intercepta las peticiones DELETE en el endpoint "api/v1/students/{studentId}" y asigna al servicio
     * específico (StudentService - deleteStudent) para eliminar un estudiante de la base de datos.
     * @param studentId - Representa el Id del estudiante
     */
    @DeleteMapping(path = "{studentId}")
    private void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

    }

    /**
     * Intercepta las peticiones DELETE en el endpoint "api/v1/students/deletestudents" y asigna al servicio
     * específico (StudentService - deleteSelectStudents) para eliminar una serie de estudiante seleccionados
     * de la base de datos.
     * @param ids - Representa la lista de estudiantes que se quieren eliminar
     */
    @DeleteMapping(path = "/deletestudents")
    private void deleteSelectStudents(@RequestBody List<Long> ids){
        studentService.deleteSelectStudents(ids);
    }
}

