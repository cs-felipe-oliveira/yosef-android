package br.com.concrete.yosef.design.api.property

import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.text.InputType
import android.widget.EditText
import br.com.concrete.yosef.design.R
import br.com.concrete.yosef.design.api.property.PasswordToggleCommand.Companion.PASSWORD_TOGGLE_ENABLED
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.layoutAndAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordToggleCommandTest {

    @Rule
    @JvmField
    val exceptionRule: ExpectedException = ExpectedException.none()

    private val context = InstrumentationRegistry.getContext()!!

    private lateinit var passwordToggleCommand: PasswordToggleCommand

    @Before
    fun setup() {
        passwordToggleCommand = PasswordToggleCommand()
        context.setTheme(R.style.Theme_AppCompat)
    }

    @Test
    fun renderingTextInputLayoutShouldApplyPasswordToggleEnabled() {
        val dynamicProperty = DynamicProperty(PASSWORD_TOGGLE_ENABLED, "string", "true")

        val textInputLayout = TextInputLayout(context).apply {
            addView(
                EditText(context).apply {
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            )
        }
        passwordToggleCommand.apply(textInputLayout, dynamicProperty)

        textInputLayout.layoutAndAssert {
            Assert.assertTrue(textInputLayout.isPasswordVisibilityToggleEnabled
                    == dynamicProperty.value.toBoolean())
        }
    }

    @Test
    fun renderingViewDifferentFromTextInputLayoutShouldThrow() {
        val dynamicProperty = DynamicProperty(PASSWORD_TOGGLE_ENABLED, "string", "true")

        val anotherViewType = EditText(context)

        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("The value (${dynamicProperty.value})" +
                " cannot be parsed as a color")

        passwordToggleCommand.apply(anotherViewType, dynamicProperty)
    }

    @Test
    fun renderingEditTextWithoutPasswordInputTypeShouldThrow() {
        val dynamicProperty = DynamicProperty(PASSWORD_TOGGLE_ENABLED, "string", "true")

        val editText = EditText(context)
        val textInputLayout = TextInputLayout(context).apply {
            addView(editText)
        }

        exceptionRule.expect(IllegalStateException::class.java)
        exceptionRule.expectMessage("The EditText input type value " +
                "(${editText.inputType}) is not a valid inputType")

        passwordToggleCommand.apply(textInputLayout, dynamicProperty)
    }
}