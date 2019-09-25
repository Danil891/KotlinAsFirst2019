 @file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import ru.spbstu.kotlin.generate.combinators.abs
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

 /**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val a = number / 100
    val b = number % 100
    return a / 10 + a % 10 == b / 10 + b % 10
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val a = x1 == x2 || y1 == y2
    val b = abs(x1 - x2) == abs(y1 - y2)
    return a || b
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val highness: Int = if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
        1
    } else 0

    return when {
        (month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0) -> 31
        highness == 1 && month == 2 -> 29
        highness != 1 && month == 2 -> 28
        else -> 30
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = r2 >= r1 + sqrt(sqr(x2 - x1) + sqr(y2 - y1))

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean{
    val c1 = max(max(a, b), c)
    val a1 = min(min(a, b), c)
    var b1 = b
    when {
        c1 == b && a1 == a || c1 == a && a1 == b -> b1 = c
        c1 == c && a1 == b || c1 == b && a1 == c -> b1 = a
    }
    return a1 <= min(r, s) && b1 <= max(r, s)
}
