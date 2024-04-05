package labs.gas.musical.heart

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withConstructor
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.resideInPackage("..domain..")
            }
    }

    @Test
    fun test2() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .withConstructor {
                it.hasParameter { param ->
                    param.type.name.endsWith("Repository")
                }
            }
    }

    @Test
    fun test3() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .withConstructor {
                it.hasParameter { param ->
                    param.type.name.endsWith("Repository")
                }
            }.assertEmpty()
    }

    @Test
    fun test4() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .assertTrue { it.name.endsWith("ViewModel") }
    }
}
