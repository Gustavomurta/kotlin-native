import kotlin.test.*
import kotlin.properties.*

class UnsafeNullableLazyValTest {
    var resultA = 0
    var resultB = 0

    val a: Int? by lazy(LazyThreadSafetyMode.NONE) { resultA++; null }
    val b by lazy(LazyThreadSafetyMode.NONE) { foo() }

    fun doTest() {
        a
        b

        assertTrue(a == null, "fail: a should be null")
        assertTrue(b == null, "fail: a should be null")
        assertTrue(resultA == 1, "fail: initializer for a should be invoked only once")
        assertTrue(resultB == 1, "fail: initializer for b should be invoked only once")
    }

    fun foo(): String? {
        resultB++
        return null
    }
}

fun box() {
    UnsafeNullableLazyValTest().doTest()
}