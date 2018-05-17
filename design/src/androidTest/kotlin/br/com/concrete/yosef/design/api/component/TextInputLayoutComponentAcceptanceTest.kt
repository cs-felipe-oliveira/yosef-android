package br.com.concrete.yosef.design.api.component;

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concrete.yosef.design.activities.InputLayoutActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TextInputLayoutComponentAcceptanceTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<InputLayoutActivity>(InputLayoutActivity::class.java)

    @Test
    fun whenAddInputLayoutThenShowInputLayoutView() {
        onView(Matchers.allOf(ViewMatchers.isAssignableFrom(TextInputLayout::class.java)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}