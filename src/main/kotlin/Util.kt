import kotlin.math.abs

class Util {
    companion object {
        const val delta = 1e-5
        infix fun Double.eq(other: Double): Boolean = abs(this - other) < delta
    }
}