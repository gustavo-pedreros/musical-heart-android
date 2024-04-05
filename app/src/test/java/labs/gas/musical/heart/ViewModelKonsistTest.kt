package labs.gas.musical.heart

import androidx.lifecycle.ViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withConstructor
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class ViewModelKonsistTest {
    companion object {
        private const val VIEW_MODEL_SUFFIX = "ViewModel"
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
