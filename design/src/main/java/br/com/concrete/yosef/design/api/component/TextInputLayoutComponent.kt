package br.com.concrete.yosef.design.api.component

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.api.component.Component
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.api.property.id.IdCommand
import br.com.concrete.yosef.api.property.size.HeightCommand
import br.com.concrete.yosef.api.property.size.WidthCommand
import br.com.concrete.yosef.api.property.spacing.MarginPropertyCommand
import br.com.concrete.yosef.api.property.spacing.PaddingPropertyCommand
import br.com.concrete.yosef.design.api.property.PasswordToggleCommand
import br.com.concrete.yosef.entity.DynamicComponent
import br.com.concrete.yosef.entity.DynamicProperty

/**
 * Class that implements the [Component] interface and creates the component
 * TextField([TextInputLayout]), applying its properties
 */
class TextInputLayoutComponent : Component {

    companion object {
        /**
         * This constant documents which type is associated with the component
         */
        const val TEXT_INPUT_LAYOUT = "textInputLayout"
    }

    private val commands: Map<String, DynamicPropertyCommand> = mapOf(
        WidthCommand.WIDTH_TYPE to WidthCommand(),
        HeightCommand.HEIGHT_TYPE to HeightCommand(),
        PaddingPropertyCommand.PADDING to PaddingPropertyCommand(),
        MarginPropertyCommand.MARGIN to MarginPropertyCommand(),
        IdCommand.ID to IdCommand(),
        PasswordToggleCommand.PASSWORD_TOGGLE_ENABLED to PasswordToggleCommand()
    )

    override fun applyProperties(
        view: View,
        dynamicProperties: List<DynamicProperty>,
        actionListener: OnActionListener?
    ) {
        dynamicProperties.forEach {
            commands[it.name]?.apply(view, it)
        }
    }

    override fun createView(context: Context): View {
        return TextInputLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
    }

    override fun addComponentsAsChildren(
        children: List<DynamicComponent>,
        view: View,
        components: Map<String, Component>,
        listener: OnActionListener?
    ) {
        if (children.size > 1) {
            throw IllegalArgumentException("The TextInputLayout should be just one child")
        }

        children.firstOrNull()?.let {
            val childComponent = DynamicViewCreator.getComponentByType(it, components)
            val childView = childComponent.createView(view.context)

            if ((childView is EditText).not()) {
                throw IllegalArgumentException("The child of ${view::class.java.simpleName} " +
                        "should an EditText")
            }

            childView.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            (view as TextInputLayout).addView(childView)
            childComponent.applyProperties(childView, it.dynamicProperties, listener)
        }
    }
}