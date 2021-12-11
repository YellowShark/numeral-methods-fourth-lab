import java.util.*

const val N = 5

fun main() {
    val s = Scanner(System.`in`)
    println("Функция f(x) = 1 / ln (x)")
    println("Введите границы интегрирования:")
    println("a =")
    val a = s.nextFloat()
    println("b =")
    val b = s.nextFloat()
    println("Введите точность вычисления")
    val eps = s.nextDouble()
    TrapeziumMethod().run {
        calculate(a = a, b = b, n = N, eps = eps)
    }
}