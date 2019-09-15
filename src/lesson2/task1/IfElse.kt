@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    age % 100 in 5..20 -> "$age лет"
    age % 10 in 2..4 -> "$age года"
    age % 10 in 5..9 -> "$age лет"
    age % 10 == 1 -> "$age год"
    else -> "$age лет"


}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfS = (t1 * v1 + t2 * v2 + t3 * v3) / 2

    return when{
        halfS <= t1*v1 -> t1 - (t1 * v1 - halfS ) / v1
        halfS <= (t1 * v1 + t2 * v2) -> t1 + (halfS - t1 * v1) / v2
        else -> t1 + t2 +  (halfS - t1 * v1 - t2 * v2)  / v3 }
 }
/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when{
    (kingX == rookX1 || kingX == rookX2) && (kingY == rookY1 || kingY == rookY2) -> 3
    kingX == rookX1  || kingY == rookY1  -> 1
    kingX == rookX2 || kingY == rookY2 -> 2
    else -> 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = when{
    (kingX == rookX || kingY == rookY) && (abs(kingX - bishopX) == abs(kingY - bishopY)) -> 3
    kingX == rookX  || kingY == rookY  -> 1
    abs (kingX - bishopX) == abs(kingY - bishopY) -> 2
    else -> 0}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int{
    val c1 = max(max(a, b), c)
    val a1 = min(min(a, b), c)
    var b1 = b
    when{
        c1 == b && a1 == a || c1 == a && a1 == b -> b1 = c
        c1 == c && a1 == b || c1 == b && a1 == c -> b1 = a
    }
    return if (a1 + b1 > c1 && a1 + c1 > b1 && c1 + b1 > a1) {
        when {
            (a1 * a1 + b1 * b1 == c1 * c1) -> 1
            (a1 * a1 + b1 * b1 > c1 * c1) -> 0
            else -> 2
        }
    }
    else -1
}



/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int{
    val a1 = abs(a)
    val b1 = abs(b)
    val c1 = abs(c)
    val d1 = abs(d)
    return when {
        a1 <= c1 && b1 <= d1 -> b1 - c1
        a1 <= c1 && b1 >= d1 -> d1 - c1
        c1 <= a1 && b1 <= d1  && c1 <= b1 -> b1 - a1
        c1 <= a1 && b1 >= d1 && a1 <= d1 -> d1 - a1
        else -> -1
    }
}
