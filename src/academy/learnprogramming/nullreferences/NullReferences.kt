package academy.learnprogramming.nullreferences

fun main(args: Array<String>) {

    val str: String? = "This isn't null"
    str?.let { printText(it) }

    val str4 : String? = null
    val anotherStr = "This isn't nullable"
    println(str4 == anotherStr)

    val str2 = str!!
    val str3 = str2.toUpperCase()

}

fun printText(text: String) {
    println(text)
}





