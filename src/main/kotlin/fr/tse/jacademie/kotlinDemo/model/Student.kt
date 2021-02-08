package fr.tse.jacademie.kotlinDemo.model

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Student(
        var name: String,
        @ElementCollection var notes: MutableList<Note> = mutableListOf<Note>(),
        @Id @GeneratedValue var id: Long? = null) {
}
