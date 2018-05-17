package br.com.concrete.yosef.design.api.property

import android.support.design.widget.TextInputLayout
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty


/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the passwordToggleEnabled property to the view([TextInputLayout])
 */
class PasswordToggleCommand : DynamicPropertyCommand {

    companion object {
        /**
         * Name of the property that can be used in the json
         */
        const val PASSWORD_TOGGLE_ENABLED = "passwordToggleEnabled"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        val textInputLayout = view as? TextInputLayout ?: throw IllegalArgumentException(
            "The value (${dynamicProperty.value}) cannot be parsed as a color"
        )

        val inputFrame: FrameLayout = textInputLayout.getChildAt(0) as FrameLayout
        val editText: EditText? = inputFrame.getChildAt(0) as? EditText

        val passwordTypes = listOf(
            InputType.TYPE_TEXT_VARIATION_PASSWORD,
            InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD,
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
            InputType.TYPE_NUMBER_VARIATION_PASSWORD
        )

        val passwordTypesWithBaseType = passwordTypes.map { it + InputType.TYPE_CLASS_TEXT }

        if (editText?.inputType in passwordTypes
            || editText?.inputType in passwordTypesWithBaseType) {
            view.isPasswordVisibilityToggleEnabled = dynamicProperty.value.toBoolean()
        } else {
            throw IllegalStateException(
                "The EditText input type value (${editText?.inputType}) is not a valid inputType"
            )
        }
    }
}