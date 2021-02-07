package fr.tse.jacademie.kotlinDemo.web

import fr.tse.jacademie.kotlinDemo.model.Note
import fr.tse.jacademie.kotlinDemo.model.Student
import fr.tse.jacademie.kotlinDemo.service.StudentRepository
import org.hamcrest.Matchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.properties.Delegates

@SpringBootTest
@AutoConfigureMockMvc
internal class StudentControllerIntegrationTest @Autowired constructor(val studentRepo: StudentRepository) {
    @Autowired
    lateinit var mockMvc: MockMvc

    companion object {
        private val AVERAGE_PATH = "/students/{id}/averages"
    }

    @Test
    fun shouldFailWithoutData() {
        val noFoundId = -42
        mockMvc.get(AVERAGE_PATH, noFoundId)
                .andExpect {
                    status { is4xxClientError() }
                    content { string(matcher = Matchers.stringContainsInOrder("Student", noFoundId.toString())) }
                }
    }

    @Nested
    inner class WithData {
        var existingStudent by Delegates.notNull<Long>()

        @BeforeEach
        fun prepareData() {
            existingStudent =
                    studentRepo.save(
                            Student(
                                    name = "toto",
                                    notes = mutableListOf(
                                            Note("Math", 12.5),
                                            Note("Kart", 17.75),
                                            Note("Cuisine", 2.0),
                                            Note("Kart", 18.25),
                                    )
                            )
                    ).id!!
            studentRepo.flush()
        }

        @AfterEach
        fun removeData() {
            studentRepo.deleteAll()
        }

        @Test
        fun shouldProduceAveragesOnExistingStudent() {
            mockMvc.get(AVERAGE_PATH, existingStudent) {
                contentType = MediaType.APPLICATION_JSON
            }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        content {
                            jsonPath("Cuisine") {
                                value(2.0)
                            }
                            jsonPath("Kart") {
                                value(18)
                            }
                            jsonPath("Math") {
                                value(12.5)
                            }
                        }
                    }
        }
    }
}
