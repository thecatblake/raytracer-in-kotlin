import Util.Companion.eq
import kotlin.math.sqrt

data class Tuple(val x: Double, val y: Double, val z: Double, val w: Double) {
    operator fun plus(other: Tuple): Tuple = Tuple(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Tuple): Tuple = Tuple(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(other: Tuple): Tuple = Tuple(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun times(t: Double): Tuple = Tuple(x * t, y * t, z * t, w * t)
    operator fun div(other: Tuple): Tuple = Tuple(x / other.x, y / other.y, z / other.z, w / other.w)
    operator fun div(t: Double): Tuple = Tuple(x / t, y / t, z / t, w / t)
    operator fun unaryMinus(): Tuple = Tuple(-x, -y, -z, -w)

    fun dot(other: Tuple): Double = x * other.x + y * other.y + z * other.z
    fun cross(other: Tuple): Tuple = Tuple(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x, 0.0)
    fun norm(): Double = sqrt(x * x + y * y + z * z)
    fun normalized(): Tuple = div(norm())
    fun reflect(normal: Tuple): Tuple = minus(normal * (dot(normal) * 2))

    override fun equals(other: Any?): Boolean {
        return if(other is Tuple)
            (x eq other.x) &&
                    (y eq other.y) &&
                    (z eq other.z) &&
                    (w eq other.w)
        else
            false
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        result = 31 * result + w.hashCode()
        return result
    }

    companion object {
        fun point(x: Double, y: Double, z: Double): Tuple = Tuple(x, y, z, 1.0)
        fun vector(x: Double, y: Double, z: Double): Tuple = Tuple(x, y, z, 0.0)
    }
}
