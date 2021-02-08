package fr.tse.jacademie.kotlinDemo.service

import org.springframework.stereotype.Service

@Service
class StudentService(val repo: StudentRepository) {
    fun averageForStudentById(id: Long): Map<String, Double> {
        val student = repo.findById(id).orElseGet { throw MissingDataException("Student with id $id not found") }
        return student.notes
                .groupBy { it.option }
                .mapValues { it.value.map { it.note }.average() }
    }
}
