import Tuple.Companion.vector
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.sqrt

class TupleTest {

    @Test
    fun plus() {
        val a1 = Tuple(3.0, -2.0, 5.0, 1.0)
        val a2 = Tuple(-2.0, 3.0, 1.0, 0.0)

        val t = Tuple(1.0, 1.0, 6.0, 1.0)

        assertEquals(t, a1 + a2)
    }

    @Test
    fun minus() {
        val a1 = Tuple(3.0, -2.0, 5.0, 1.0)
        val a2 = Tuple(-2.0, 3.0, 1.0, 0.0)

        val t = Tuple(5.0, -5.0, 4.0, 1.0)

        assertEquals(t, a1 - a2)
    }

    @Test
    fun times() {
        val a1 = Tuple(3.0, -2.0, 5.0, 1.0)
        val a2 = Tuple(-2.0, 3.0, 1.0, 0.0)

        val t = Tuple(-6.0, -6.0, 5.0, 0.0)

        assertEquals(t, a1 * a2)
    }

    @Test
    fun div() {
        val a1 = Tuple(3.0, -4.0, 5.0, 1.0)
        val a2 = Tuple(-2.0, 2.0, 1.0, 1.0)

        val t = Tuple(-1.5, -2.0, 5.0, 1.0)

        assertEquals(t, a1 / a2)
    }

    @Test
    operator fun unaryMinus() {
        val a = Tuple(3.0, -2.0, 5.0, 1.0)

        val t = Tuple(-3.0, 2.0, -5.0, -1.0)

        assertEquals(t, -a)
    }

    @Test
    fun dot() {
        val a1 = Tuple(3.0, -2.0, 5.0, 1.0)
        val a2 = Tuple(-2.0, 3.0, 1.0, 0.0)

        val t = -7.0

        assertEquals(t, a1.dot(a2))
    }

    @Test
    fun cross() {
        val a = vector(1.0, 2.0, 3.0)
        val b = vector(2.0, 3.0, 4.0)

        val t1 = vector(-1.0, 2.0, -1.0)
        val t2 = vector(1.0, -2.0, 1.0)

        assertEquals(t1, a.cross(b))
        assertEquals(t2, b.cross(a))
    }

    @Test
    fun norm() {
        val a = vector(1.0, 2.0, 3.0)
        val t = sqrt(14.0)

        assertEquals(t, a.norm())
    }

    @Test
    fun normalized() {
        val a = vector(1.0, 2.0, 3.0)

        assertEquals(1.0, a.normalized().norm())
    }

    @Test
    fun reflect() {
        val v = vector(0.0, -1.0, 0.0)
        val n = vector(sqrt(2.0)/2, sqrt(2.0)/2, 0.0)
        val t = vector(1.0, 0.0, 0.0)

        assertEquals(t, v.reflect(n))
    }
}