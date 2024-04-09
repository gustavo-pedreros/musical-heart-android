package labs.gas.musical.heart

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withConstructor
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class ViewModelKonsistTest {
    companion object {
        private const val VIEW_MODEL_SUFFIX = "ViewModel"
    }


    @TestFactory
    fun viewModelDynamicTests(): Stream<DynamicTest> =
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(VIEW_MODEL_SUFFIX)
            .stream()
            .flatMap { viewModel ->
                Stream.of(
                    DynamicTest.dynamicTest("${viewModel.name} should have unit test") {
                        viewModel.assertTrue { it.hasTestClasses() }
                    },
                    DynamicTest.dynamicTest("${viewModel.name} should reside in domain package") {
                        viewModel.assertTrue { it.resideInPackage("..presentation..viewmodel..") }
                    },
                    DynamicTest.dynamicTest("${viewModel.name} should not use repository directly") {
                        viewModel.assertTrue { it.name.endsWith(VIEW_MODEL_SUFFIX) }
                    },
                )
            }

    @Test
    fun viewModelShouldNotUseRepositoryDirectly() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith(VIEW_MODEL_SUFFIX)
            .withConstructor {
                it.hasParameter { param ->
                    param.type.name.endsWith("Repository")
                }
            }.assertEmpty()
    }

    @Test
    fun viewModelShouldHasAtEndViewModel() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(ViewModel::class)
            .assertTrue { it.name.endsWith(VIEW_MODEL_SUFFIX) }
    }
}
