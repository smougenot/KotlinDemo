package fr.tse.jacademie.kotlinDemo.web

import fr.tse.jacademie.kotlinDemo.service.MissingDataException
import fr.tse.jacademie.kotlinDemo.service.StudentService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("students")
class StudentController(val service: StudentService) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(StudentController::class.java)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun errorHandler(error: MissingDataException): String {
        LOGGER.error("errorHandler ${error.message}")
        return error.message ?: "No data found"
    }

    @GetMapping("{id}/averages")
    fun averageByStudentId(@PathVariable id: Long): Map<String, Double> {
        LOGGER.info("averageByStudentId for id=$id")
        return service.averageForStudentById(id)
    }

}
