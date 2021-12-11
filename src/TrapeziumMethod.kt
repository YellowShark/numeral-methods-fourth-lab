import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

const val S = 2f

class TrapeziumMethod {
    fun calculate(a: Float, b: Float, n: Int, eps: Double) {
        var e: Float
        var prevIntegral = calc(a, b, n)
        var newN = n
        do {
            newN *= 2
            val currIntegral = calc(a, b, newN)
            e = abs((prevIntegral - currIntegral) / ( (0.5f).pow(S) - 1) )
            prevIntegral = currIntegral
        } while (e > eps)
        println("Точность $eps достигнута $newN разбиениями.")
        println("Интеграл равен $prevIntegral.")
    }

    private fun calc(a: Float, b: Float, n: Int): Float {
        val h = (b - a) / n
        var x = a
        var f = (f(x) + f(b)) / 2
        for (i in 1 until n) {
            x += h
            f += f(x)
        }
        return h * f
    }

    private fun f(x: Float) = 1 / ln(x)
}