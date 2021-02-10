package fr.tse.jacademie.kotlinDemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/** Sample code usd in the documentation */
class CodeSampleTest {

    fun nonMutable() {
        // Non mutable
        val maConstante = "Never change me"
        val mesChoix = setOf("ras")
    }

    fun mutable() {
        var maConstante = "Can change"
        maConstante = "toto"

        val mesChoix = mutableSetOf("ci")
        mesChoix.add("ou ça")
    }

    fun sum(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    fun nom(param: String): String {
        return "the result"
    }

    fun nom(nullable: String?, nonNull: String) {
        // nullable.length // do not compile
        nullable!!.length
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // `obj` is automatically cast to `String` in this branch
            return obj.length
        }

        // `obj` is still of type `Any` outside of the type-checked branch
        return null
    }


    fun nom(files: String?) {
        // accède à size si files est non null
        // valeur par défaut (files==null ||  files.size==null)
        println(files?.length ?: "empty")
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


fun String.asKebab() = this.replace(' ', '-')

class KebabExtensionTest {
    @Test
    fun testKebab() {
        assertThat("a Bc d".asKebab()).isEqualTo("a-Bc-d")
    }
}
