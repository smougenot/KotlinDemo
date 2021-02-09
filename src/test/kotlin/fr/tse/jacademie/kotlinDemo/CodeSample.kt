package fr.tse.jacademie.kotlinDemo

/** Sample code usd in the documentation */
class CodeSample {

    fun nonMutable() {
        // Non mutable
        val maConstante = "Never change"
        val mesChoix = setOf("ras")
    }

    fun mutable() {
        var maConstante = "Can change"
        maConstante = "toto"

        val mesChoix = mutableSetOf("ci")
        mesChoix.add("ou ça")
    }

    fun nom(param: String): String {
        return "the result"
    }

    fun nom(nullable: String?, nonNull: String) {
        //...
    }

    fun strings() {
        val qui = "who"
        val msg = "${qui.toUpperCase()} est sur la première base"

        val texte = """
            Lorem ipsum dolor sit amet,
            consectetur adipiscing elit,
            sed do eiusmod tempor incididunt
            ut labore et dolore magna aliqua.
        """.trimIndent()
    }
}

data class MessageK(
    val from: String,
    val to: String,
    val title: String? = null,
    val cc: String? = null,
    val body: String? = null
)

val t = MessageK(from = "From", to = "To", cc = "Cc")

open class ParentClass(val size: Int) {
    private fun wontOverride() {
        // ...
    }

    open fun canOverride() {
        // ...
    }
}

class DescendantClass(aSize: Int) : ParentClass(aSize) {
    override fun canOverride() {
        // ...
    }
}

fun f() {
    val numberFour = DescendantClass(4)
}
