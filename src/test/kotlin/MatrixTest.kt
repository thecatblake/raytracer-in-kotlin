import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun times() {
        val A = Matrix(doubleArrayOf(
            1.0, 2.0, 3.0, 4.0,
            5.0, 6.0, 7.0, 8.0,
            9.0, 8.0, 7.0, 6.0,
            5.0, 4.0, 3.0, 2.0
        ))

        val B = Matrix(doubleArrayOf(
            -2.0, 1.0, 2.0, 3.0,
            3.0, 2.0, 1.0, -1.0,
            4.0, 3.0, 6.0, 5.0,
            1.0, 2.0, 7.0, 8.0
        ))

        val T = Matrix(doubleArrayOf(
            20.0, 22.0, 50.0, 48.0,
            44.0, 54.0, 114.0, 108.0,
            40.0, 58.0, 110.0, 102.0,
            16.0, 26.0, 46.0, 42.0
        ))

        assertEquals(T, A*B)
    }

    @Test
    fun timesTuple() {
        val A = Matrix(doubleArrayOf(
            1.0, 2.0, 3.0, 4.0,
            2.0, 4.0, 4.0, 2.0,
            8.0, 6.0, 4.0, 1.0,
            0.0, 0.0, 0.0, 1.0
        ))

        val b = Tuple(1.0, 2.0, 3.0, 1.0)

        val t = Tuple(18.0, 24.0, 33.0, 1.0)

        assertEquals(t, A * b)
    }

    @Test
    fun t() {
        val A = Matrix(doubleArrayOf(
            0.0, 9.0, 3.0, 0.0,
            9.0, 8.0, 0.0, 8.0,
            1.0, 8.0, 5.0, 3.0,
            0.0, 0.0, 5.0, 8.0
        ))

        val T = Matrix(doubleArrayOf(
            0.0, 9.0, 1.0, 0.0,
            9.0, 8.0, 8.0, 0.0,
            3.0, 0.0, 5.0, 5.0,
            0.0, 8.0, 3.0, 8.0
        ))

        assertEquals(T, A.T())
    }

    @Test
    fun det() {
        val A = Matrix(doubleArrayOf(
            -2.0, -8.0, 3.0, 5.0,
            -3.0, 1.0, 7.0, 3.0,
            1.0, 2.0, -9.0, 6.0,
            -6.0, 7.0, 7.0, -9.0
        ))

        assertEquals(-4071.0, A.det())
    }

    @Test
    fun inverse() {
        val A = Matrix(doubleArrayOf(
            -2.0, 1.0, 2.0, 3.0,
            3.0, 2.0, 1.0, -1.0,
            4.0, 3.0, 6.0, 5.0,
            1.0, 2.0, 7.0, 8.0
        ))

        assertEquals(Matrix.identity(), A * A.inverse())
    }
}