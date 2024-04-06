package labs.gas.musical.heart

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class UseCaseKonsistTest {

    companion object {
        private const val USE_CASE_SUFFIX = "UseCase"
    }

    @TestFactory
    fun viewModelDynamicTests(): Stream<DynamicTest> =
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(USE_CASE_SUFFIX)
            .stream()
            .flatMap { usecase ->
                Stream.of(
                    dynamicTest("usecaseShouldHaveTestTest") {
                        usecase.assertTrue { it.hasTestClasses() }
                    },
                    dynamicTest("usecaseShouldResideInDomainPackageTest") {
                        usecase.assertTrue { it.resideInPackage("..domain..usecase..") }
                    }
                )
            }

    @Test
    fun usecaseShouldHaveTestTest() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(USE_CASE_SUFFIX)
            .assertTrue {
                it.hasTestClasses()
            }
    }

    @Test
    fun usecaseShouldResideInDomainPackageTest() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(USE_CASE_SUFFIX)
            .assertTrue {
                it.resideInPackage("..domain..usecase..")
            }
    }
}
