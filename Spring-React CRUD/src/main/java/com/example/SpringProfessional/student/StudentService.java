package com.example.SpringProfessional.student;

import com.example.SpringProfessional.student.exceptions.BadRequestException;
import com.example.SpringProfessional.student.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Contiene la lógica del negocio. Se comunica con la capa de acceso a la base de datos. Contiene la lógica de
 * para obtener todos los estudiantes, agregar un nuevo estudiante, editar un nuevo estudiante y eliminar un
 * estudiante. Lanza una excepción (BadRequestException) si el email de un nuevo estudiante ya está registrado.
 * Lanza una excepción (StudentNotFoundException) si el estudiante no se encontró en la base de datos.
 */
@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Provee una lista con todos los estudiantes almacenados en la base de datos.
     * @return - Devuelve la lista con los estudiantes almacenados en la base de datos.
     */
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     * Guarda un nuevo estudiante a la base de datos.
     * @param student - Representa al estudiante que se quiere dar de alta en el sistema
     */
    public void addStudent(Student student) {
        //check if email is taken
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if(existsEmail){
            throw new BadRequestException("Email " + student.getEmail() +  " taken");
        }
        studentRepository.save(student);
    }

    /**
     * Elimina un estudiante de la base de datos
     * @param studentId - Representa el Id del estudiante
     */
    public void deleteStudent(Long studentId) {
        //Check if student exist
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("The student with id " + studentId + " doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }

    /**
     * Actualiza un estudiante existente a la base de datos.
     * @param studentId - Representa el Id del estudiante
     * @param student - Representa al estudiante que se quiere actualizar en el sistema
     */
    public void updateStudent(Long studentId, StudentRequest student) {
        if(!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("The student with id " + studentId + " doesn't exists");
        }
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if(existsEmail){
            throw new BadRequestException("Email " + student.getEmail() +  " taken");
        }
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(student.getName() != null){
            studentOptional.get().setName(student.getName());
        }
        if(student.getEmail() != null){
            studentOptional.get().setEmail(student.getEmail());
        }
        if(student.getGender() != null){
            studentOptional.get().setGender(student.getGender());
        }
        if(student.getAge() != null){
            studentOptional.get().setAge(student.getAge());
        }
        studentRepository.save(studentOptional.get());
    }

    /**
     * Elimina a los estudiantes seleccionados de la base de datos.
     * @param ids - Representa los ids de los estudiantes que se quieren eliminar del sitema
     */
    @Transactional
    public void deleteSelectStudents(List<Long> ids) {
        studentRepository.deleteByIdIn(ids);
    }
}
