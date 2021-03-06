package br.com.concrete.yosef.glide

import android.view.View
import android.widget.ImageView
import br.com.concrete.yosef.api.property.DynamicPropertyCommand
import br.com.concrete.yosef.entity.DynamicProperty
import br.com.concrete.yosef.glide.DummyContentProvider.Companion.appContext
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.get

/**
 * Command pattern for the image url property
 */
class ImageUrlCommand(private val glide: Glide = get(appContext)) : DynamicPropertyCommand {

    companion object {
        const val IMAGE_URL = "url"
    }

    override fun apply(view: View, dynamicProperty: DynamicProperty) {
        if (view is ImageView) {
            glide.requestManagerRetriever.get(glide.context).load(dynamicProperty.value).into(view)
        }
    }
}
