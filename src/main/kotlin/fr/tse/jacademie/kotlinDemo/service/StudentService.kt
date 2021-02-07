package fr.tse.jacademie.kotlinDemo.service

import fr.tse.jacademie.kotlinDemo.model.Note
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class StudentService(val repo: StudentRepository) {
    fun averageForStudentById(id: Long): Map<String, Double> {
        val student = repo.findById(id).orElseGet { throw MissingDataException("Student with id $id not found") }
        return student.notes.stream()
                .collect(Collectors.groupingBy(Note::option, Collectors.averagingDouble(Note::note)))
    }
}
