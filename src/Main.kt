import java.io.File
import java.util.*

const val INPUT_FILE = "in.txt"
const val OUTPUT_FILE = "out.txt"

fun main() {
    fun writeErrorInTheFile(errorMessage: String) {
        File(OUTPUT_FILE).bufferedWriter().use { out ->
            out.write(errorMessage)
        }
    }

    val s = Scanner(File(INPUT_FILE))
    println("Функция f(x) = 1 / ln (x)")
    val a = s.nextFloat()
    val b = s.nextFloat()
    val eps = s.nextFloat()
    val n = s.nextInt()
    val hMin = s.nextFloat()
    println("Границы интегрирования:")
    println("a = $a")
    println("b = $b")
    println("Точность вычисления: $eps")
    println("Начальное число частичных отрезков: $n")
    println("Наименьшее допустимое значение шага интегрирования: $hMin")
    println("------------------------------------------------------------------")

    TrapeziumMethod().runCatching {
        calculate(a = a, b = b, n = n, eps = eps, hMin = hMin)
    }.onFailure { t ->
        t.message?.let { writeErrorInTheFile(it) }
    }
}