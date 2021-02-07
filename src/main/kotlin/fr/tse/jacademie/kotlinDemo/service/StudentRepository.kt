package fr.tse.jacademie.kotlinDemo.service

import fr.tse.jacademie.kotlinDemo.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
    fun findByName(name: String): Set<Student>
}
