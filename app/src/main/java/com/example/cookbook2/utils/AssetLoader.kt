package com.example.cookbook2.utils

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import com.example.cookbook2.App
import com.example.cookbook2.R
import java.io.IOException
import java.io.InputStream

object AssetLoader {

    private val loadedImages: MutableMap<String, Drawable> = mutableMapOf()

    fun loadImage(path: String): Drawable? {
        return try {

            if(loadedImages.containsKey(path))
                return loadedImages[path]

            val ims: InputStream = App.appContext.assets.open("images/$path")
            val d = Drawable.createFromStream(ims, null) ?: throw IOException()
            loadedImages[path] = d

            if(loadedImages.size > 100)
                loadedImages.clear()

            return d
        } catch (ex: IOException) {
            AppCompatResources.getDrawable(App.appContext, R.drawable.default_recipe_image);
        }
    }
}