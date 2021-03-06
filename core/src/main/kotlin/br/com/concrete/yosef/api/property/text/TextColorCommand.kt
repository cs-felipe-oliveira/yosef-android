package br.com.concrete.yosef.api.property.text

import android.graphics.Color
import android.view.View
import android.widget.TextView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * the textColor property to the view([TextView])
 *
 * @see [TextView.setTextColor]
 */
class TextColorCommand : DynamicPropertyCommand {

    companion object {
        /**
         * This constant documents which name is associated with the property
         */
        const val TEXT_COLOR = "textColor"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is TextView) {
            try {
                view.setTextColor(Color.parseColor(dynamicProperty.value))
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
                    "cannot be parsed as a color")
            }
            return
        }
        throw IllegalArgumentException("The value (${dynamicProperty.value}) " +
            "for the $TEXT_COLOR property is not compatible with ${view.javaClass.name}")
    }
}
