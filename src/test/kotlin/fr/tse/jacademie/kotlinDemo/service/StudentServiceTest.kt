package fr.tse.jacademie.kotlinDemo.service

import fr.tse.jacademie.kotlinDemo.model.Note
import fr.tse.jacademie.kotlinDemo.model.Student
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.any
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@Import(StudentService::class)
internal class StudentServiceTest(@Autowired val service: StudentService) {
    @MockBean
    lateinit var repo: StudentRepository

    @Test
    fun `should compute average on student having notes`() {
        given(repo.findById(any())).willReturn(Optional.of(
                Student(
                        name = null,
                        notes = mutableListOf(
                                Note("A", 10.0),
                                Note("B", 15.0),
                                Note("B", 17.0),
                        ))))
        val averages = service.averageForStudentById(1)
        assertThat(averages).isEqualTo(
                mapOf("A" to 10.0, "B" to 16.0)
        )
    }

    @Test
    fun `should throw exception on missing student`() {
        given(repo.findById(any())).willReturn(Optional.empty())
        val id: Long = 1
        assertThatThrownBy {
            service.averageForStudentById(id)
        }.hasMessageContainingAll("Student", id.toString())
    }
}
