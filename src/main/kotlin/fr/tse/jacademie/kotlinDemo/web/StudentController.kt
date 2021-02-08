package fr.tse.jacademie.kotlinDemo.web

import fr.tse.jacademie.kotlinDemo.service.MissingDataException
import fr.tse.jacademie.kotlinDemo.service.StudentService
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("students")
class StudentController(val service: StudentService, val logger: Logger) {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun errorHandler(error: MissingDataException): String {
        logger.error("errorHandler ${error.message}")
        return error.message ?: "No data found"
    }

    @GetMapping("{id}/averages")
    fun averageByStudentId(@PathVariable id: Long): Map<String, Double> {
        logger.info("averageByStudentId for id=$id")
        return service.averageForStudentById(id)
    }

}
