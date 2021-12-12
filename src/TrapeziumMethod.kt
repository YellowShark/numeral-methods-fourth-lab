import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

const val S = 2f

class TrapeziumMethod {
    fun calculate(a: Float, b: Float, n: Int, eps: Float, hMin: Float) {
        if (a >= b)
            throw ArithmeticException("IER = 3. Ошибка входных данных.")
        var e: Float
        var prevIntegral = calc(a, b, n, hMin)
        var newN = n
        do {
            newN *= 2
            val currIntegral = calc(a, b, newN, hMin)
            if (currIntegral >= prevIntegral)
                throw ArithmeticException("IER = 1. Решение не получено, т.к. погрешность перестала уменьшаться.")
            e = abs((prevIntegral - currIntegral) / ( (0.5f).pow(S) - 1) )
            prevIntegral = currIntegral
        } while (e > eps)

        writeResultInTheFile(e, eps, newN, a, b, prevIntegral)
    }

    private fun writeResultInTheFile(e: Float, eps: Float, newN: Int, a: Float, b: Float, result: Float) {
        File(OUTPUT_FILE).bufferedWriter().use { out ->
            out.write("IER = 0. Решение получено.\n")
            out.write("Точность $e <= $eps достигнута $newN разбиениями (шагом ${(b - a) / newN}).\n")
            out.write("Интеграл равен $result.\n")
        }
    }

    private fun calc(a: Float, b: Float, n: Int, hMin: Float): Float {
        val h = (b - a) / n
        if (h < hMin)
            throw ArithmeticException("IER = 2. Шаг интегррования стал слишком мал.")
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