package br.com.concrete.yosef.api.property.spacing

import android.view.View
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.dp
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Command class that implements the [DynamicPropertyCommand] applying
 * padding to a view view
 *
 * @see [View.setPadding]
 */
class PaddingPropertyCommand : DynamicPropertyCommand {

    companion object {
        /**
         * Name of the property that can be used in the json
         */
        const val PADDING = "padding"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {

        val (left, top, right, bottom) = generateMarginList(dynamicProperty, view)

        view.setPadding(left, top, right, bottom)
    }

    private fun generateMarginList(dynamicProperty: DynamicProperty, view: View): List<Int> {
        return if (dynamicProperty.value.contains(",")) {
            dynamicProperty.value
                .split(",")
                .map {
                    it.trim().toInt().dp(view.context)
                }
        } else {
            val value = dynamicProperty.value.trim().toInt().dp(view.context)
            listOf(value, value, value, value)
        }
    }
}
