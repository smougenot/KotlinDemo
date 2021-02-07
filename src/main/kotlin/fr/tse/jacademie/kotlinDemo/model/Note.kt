package fr.tse.jacademie.kotlinDemo.model

import javax.persistence.Embeddable

@Embeddable
class Note(var option: String, var note: Double) {
}
