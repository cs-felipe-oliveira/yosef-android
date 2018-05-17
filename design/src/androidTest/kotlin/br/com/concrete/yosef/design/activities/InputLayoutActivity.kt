package br.com.concrete.yosef.design.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.concrete.yosef.OnActionListener
import br.com.concrete.yosef.api.DynamicViewCreator
import br.com.concrete.yosef.design.api.component.TextInputLayoutComponent
import br.com.concrete.yosef.design.api.component.TextInputLayoutComponent.Companion.TEXT_INPUT_LAYOUT

class InputLayoutActivity : AppCompatActivity(), OnActionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = InputLayoutActivity::class.java.getResource(
            "/assets/example_input_layout.json").readText()

        val dynamicView = DynamicViewCreator.Builder()
            .addComponentFor(TEXT_INPUT_LAYOUT, TextInputLayoutComponent())
            .build()

        setContentView(dynamicView.createViewFromJson(this, json, this))
    }

    override fun callAction(value: String) {}
}
