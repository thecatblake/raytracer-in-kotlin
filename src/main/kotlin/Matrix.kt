import Util.Companion.eq
import kotlin.math.cos
import kotlin.math.sin

data class Matrix(val elements: DoubleArray) {
    operator fun times(other: Matrix): Matrix = Matrix(
        doubleArrayOf(
            elements[0] * other.elements[0] + elements[1] * other.elements[4] + elements[2] * other.elements[8] + elements[3] * other.elements[12],
            elements[0] * other.elements[1] + elements[1] * other.elements[5] + elements[2] * other.elements[9] + elements[3] * other.elements[13],
            elements[0] * other.elements[2] + elements[1] * other.elements[6] + elements[2] * other.elements[10] + elements[3] * other.elements[14],
            elements[0] * other.elements[3] + elements[1] * other.elements[7] + elements[2] * other.elements[11] + elements[3] * other.elements[15],
            elements[4] * other.elements[0] + elements[5] * other.elements[4] + elements[6] * other.elements[8] + elements[7] * other.elements[12],
            elements[4] * other.elements[1] + elements[5] * other.elements[5] + elements[6] * other.elements[9] + elements[7] * other.elements[13],
            elements[4] * other.elements[2] + elements[5] * other.elements[6] + elements[6] * other.elements[10] + elements[7] * other.elements[14],
            elements[4] * other.elements[3] + elements[5] * other.elements[7] + elements[6] * other.elements[11] + elements[7] * other.elements[15],
            elements[8] * other.elements[0] + elements[9] * other.elements[4] + elements[10] * other.elements[8] + elements[11] * other.elements[12],
            elements[8] * other.elements[1] + elements[9] * other.elements[5] + elements[10] * other.elements[9] + elements[11] * other.elements[13],
            elements[8] * other.elements[2] + elements[9] * other.elements[6] + elements[10] * other.elements[10] + elements[11] * other.elements[14],
            elements[8] * other.elements[3] + elements[9] * other.elements[7] + elements[10] * other.elements[11] + elements[11] * other.elements[15],
            elements[12] * other.elements[0] + elements[13] * other.elements[4] + elements[14] * other.elements[8] + elements[15] * other.elements[12],
            elements[12] * other.elements[1] + elements[13] * other.elements[5] + elements[14] * other.elements[9] + elements[15] * other.elements[13],
            elements[12] * other.elements[2] + elements[13] * other.elements[6] + elements[14] * other.elements[10] + elements[15] * other.elements[14],
            elements[12] * other.elements[3] + elements[13] * other.elements[7] + elements[14] * other.elements[11] + elements[15] * other.elements[15]
        )
    )
    
    operator fun times(other: Tuple): Tuple = Tuple(
            elements[0] * other.x + elements[1] * other.y + elements[2] * other.z + elements[3] * other.w,
            elements[4] * other.x + elements[5] * other.y + elements[6] * other.z + elements[7] * other.w,
            elements[8] * other.x + elements[9] * other.y + elements[10] * other.z + elements[11] * other.w,
            elements[12] * other.x + elements[13] * other.y + elements[14] * other.z + elements[15] * other.w,
    )

    override fun equals(other: Any?): Boolean {
        return if(other is Matrix)
            (elements[0] eq other.elements[0]) &&
                    (elements[1] eq other.elements[1]) &&
                    (elements[2] eq other.elements[2]) &&
                    (elements[3] eq other.elements[3]) &&
                    (elements[4] eq other.elements[4]) &&
                    (elements[5] eq other.elements[5]) &&
                    (elements[6] eq other.elements[6]) &&
                    (elements[7] eq other.elements[7]) &&
                    (elements[8] eq other.elements[8]) &&
                    (elements[9] eq other.elements[9]) &&
                    (elements[10] eq other.elements[10]) &&
                    (elements[11] eq other.elements[11]) &&
                    (elements[12] eq other.elements[12]) &&
                    (elements[13] eq other.elements[13]) &&
                    (elements[14] eq other.elements[14]) &&
                    (elements[15] eq other.elements[15])
        else false
    }
    
    fun T(): Matrix = Matrix(
        doubleArrayOf(
            elements[0], elements[4], elements[8], elements[12],
            elements[1], elements[5], elements[9], elements[13],
            elements[2], elements[6], elements[10], elements[14],
            elements[3], elements[7], elements[11], elements[15]
        )
    )
    
    fun det(): Double = elements[0] * (elements[5] * elements[10] * elements[15] -
            elements[5] * elements[11] * elements[14] -
            elements[9] * elements[6] * elements[15] +
            elements[9] * elements[7] * elements[14] +
            elements[13] * elements[6] * elements[11] -
            elements[13] * elements[7] * elements[10]) +
            elements[1] * (-elements[4] * elements[10] * elements[15] +
            elements[4] * elements[11] * elements[14] +
            elements[8] * elements[6] * elements[15] -
            elements[8] * elements[7] * elements[14] -
            elements[12] * elements[6] * elements[11] +
            elements[12] * elements[7] * elements[10]) +
            elements[2] * (elements[4] * elements[9] * elements[15] -
            elements[4] * elements[11] * elements[13] -
            elements[8] * elements[5] * elements[15] +
            elements[8] * elements[7] * elements[13] +
            elements[12] * elements[5] * elements[11] -
            elements[12] * elements[7] * elements[9]) +
            elements[3] * (-elements[4] * elements[9] * elements[14] +
            elements[4] * elements[10] * elements[13] +
            elements[8] * elements[5] * elements[14] -
            elements[8] * elements[6] * elements[13] -
            elements[12] * elements[5] * elements[10] +
            elements[12] * elements[6] * elements[9])
    
    fun inverse(): Matrix {
        val inv = DoubleArray(16) { 0.0 }

        inv[0] = elements[5] * elements[10] * elements[15] -
                elements[5] * elements[11] * elements[14] -
                elements[9] * elements[6] * elements[15] +
                elements[9] * elements[7] * elements[14] +
                elements[13] * elements[6] * elements[11] -
                elements[13] * elements[7] * elements[10]

        inv[4] = -elements[4] * elements[10] * elements[15] +
                elements[4] * elements[11] * elements[14] +
                elements[8] * elements[6] * elements[15] -
                elements[8] * elements[7] * elements[14] -
                elements[12] * elements[6] * elements[11] +
                elements[12] * elements[7] * elements[10]

        inv[8] = elements[4] * elements[9] * elements[15] -
                elements[4] * elements[11] * elements[13] -
                elements[8] * elements[5] * elements[15] +
                elements[8] * elements[7] * elements[13] +
                elements[12] * elements[5] * elements[11] -
                elements[12] * elements[7] * elements[9]

        inv[12] = -elements[4] * elements[9] * elements[14] +
                elements[4] * elements[10] * elements[13] +
                elements[8] * elements[5] * elements[14] -
                elements[8] * elements[6] * elements[13] -
                elements[12] * elements[5] * elements[10] +
                elements[12] * elements[6] * elements[9]

        inv[1] = -elements[1] * elements[10] * elements[15] +
                elements[1] * elements[11] * elements[14] +
                elements[9] * elements[2] * elements[15] -
                elements[9] * elements[3] * elements[14] -
                elements[13] * elements[2] * elements[11] +
                elements[13] * elements[3] * elements[10]

        inv[5] = elements[0] * elements[10] * elements[15] -
                elements[0] * elements[11] * elements[14] -
                elements[8] * elements[2] * elements[15] +
                elements[8] * elements[3] * elements[14] +
                elements[12] * elements[2] * elements[11] -
                elements[12] * elements[3] * elements[10]

        inv[9] = -elements[0] * elements[9] * elements[15] +
                elements[0] * elements[11] * elements[13] +
                elements[8] * elements[1] * elements[15] -
                elements[8] * elements[3] * elements[13] -
                elements[12] * elements[1] * elements[11] +
                elements[12] * elements[3] * elements[9]

        inv[13] = elements[0] * elements[9] * elements[14] -
                elements[0] * elements[10] * elements[13] -
                elements[8] * elements[1] * elements[14] +
                elements[8] * elements[2] * elements[13] +
                elements[12] * elements[1] * elements[10] -
                elements[12] * elements[2] * elements[9]

        inv[2] = elements[1] * elements[6] * elements[15] -
                elements[1] * elements[7] * elements[14] -
                elements[5] * elements[2] * elements[15] +
                elements[5] * elements[3] * elements[14] +
                elements[13] * elements[2] * elements[7] -
                elements[13] * elements[3] * elements[6]

        inv[6] = -elements[0] * elements[6] * elements[15] +
                elements[0] * elements[7] * elements[14] +
                elements[4] * elements[2] * elements[15] -
                elements[4] * elements[3] * elements[14] -
                elements[12] * elements[2] * elements[7] +
                elements[12] * elements[3] * elements[6]

        inv[10] = elements[0] * elements[5] * elements[15] -
                elements[0] * elements[7] * elements[13] -
                elements[4] * elements[1] * elements[15] +
                elements[4] * elements[3] * elements[13] +
                elements[12] * elements[1] * elements[7] -
                elements[12] * elements[3] * elements[5]

        inv[14] = -elements[0] * elements[5] * elements[14] +
                elements[0] * elements[6] * elements[13] +
                elements[4] * elements[1] * elements[14] -
                elements[4] * elements[2] * elements[13] -
                elements[12] * elements[1] * elements[6] +
                elements[12] * elements[2] * elements[5]

        inv[3] = -elements[1] * elements[6] * elements[11] +
                elements[1] * elements[7] * elements[10] +
                elements[5] * elements[2] * elements[11] -
                elements[5] * elements[3] * elements[10] -
                elements[9] * elements[2] * elements[7] +
                elements[9] * elements[3] * elements[6]

        inv[7] = elements[0] * elements[6] * elements[11] -
                elements[0] * elements[7] * elements[10] -
                elements[4] * elements[2] * elements[11] +
                elements[4] * elements[3] * elements[10] +
                elements[8] * elements[2] * elements[7] -
                elements[8] * elements[3] * elements[6]

        inv[11] = -elements[0] * elements[5] * elements[11] +
                elements[0] * elements[7] * elements[9] +
                elements[4] * elements[1] * elements[11] -
                elements[4] * elements[3] * elements[9] -
                elements[8] * elements[1] * elements[7] +
                elements[8] * elements[3] * elements[5]

        inv[15] = elements[0] * elements[5] * elements[10] -
                elements[0] * elements[6] * elements[9] -
                elements[4] * elements[1] * elements[10] +
                elements[4] * elements[2] * elements[9] +
                elements[8] * elements[1] * elements[6] -
                elements[8] * elements[2] * elements[5]

        var det: Double = elements[0] * inv[0] + elements[1] * inv[4] + elements[2] * inv[8] + elements[3] * inv[12]

        if (det eq 0.0) {
            return identity()
        }

        det = 1.0 / det

        for (i in 0..15) inv[i] = inv[i] * det

        return Matrix(inv)
    }


    override fun hashCode(): Int {
        return elements.contentHashCode()
    }

    companion object {
        fun identity(): Matrix = Matrix(doubleArrayOf(
            1.0, 0.0, 0.0, 0.0,
            0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
        )
        )

        fun translationMatrix(x: Double, y: Double, z: Double): Matrix = Matrix(doubleArrayOf(
            1.0, 0.0, 0.0, x,
            0.0, 1.0, 0.0, y,
            0.0, 0.0, 1.0, z,
            0.0, 0.0, 0.0, 1.0
        ))

        fun translationMatrix(v: Tuple) = translationMatrix(v.x, v.y, v.z)

        fun scalingMatrix(x: Double, y: Double, z: Double): Matrix = Matrix(doubleArrayOf(
            x, 0.0, 0.0, 0.0,
            0.0, y, 0.0, 0.0,
            0.0, 0.0, z, 0.0,
            0.0, 0.0, 0.0, 1.0
        ))

        fun scalingMatrix(v: Tuple) = scalingMatrix(v.x, v.y, v.z)

        fun rotationXMatrix(rad: Double) = Matrix(doubleArrayOf(
            0.0, 0.0, 0.0, 0.0,
            0.0, cos(rad), -sin(rad), 0.0,
            0.0, sin(rad), cos(rad), 0.0,
            0.0, 0.0, 0.0, 1.0
        ))

        fun rotationYMatrix(rad: Double) = Matrix(doubleArrayOf(
            cos(rad), 0.0, sin(rad), 0.0,
            0.0, 1.0, 0.0, 0.0,
            -sin(rad), 0.0, cos(rad), 0.0,
            0.0, 0.0, 0.0, 1.0
        ))

        fun rotationZMatrix(rad: Double) = Matrix(doubleArrayOf(
            cos(rad), -sin(rad), 0.0, 0.0,
            sin(rad), cos(rad), 0.0, 0.0,
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
        ))

        fun viewTransform(from: Tuple, to: Tuple, up: Tuple): Matrix {
            val forward = (to - from).normalized()
            val left = forward.cross(up.normalized())
            val trueUp = left.cross(forward)

            val orientation = Matrix(doubleArrayOf(
                left.x, left.y, left.z, 0.0,
                trueUp.x, trueUp.y, trueUp.z, 0.0,
                -forward.x, -forward.y, -forward.z, 0.0,
                0.0, 0.0, 0.0, 1.0
            ))

            return orientation * translationMatrix(-from)
        }
    }
}
