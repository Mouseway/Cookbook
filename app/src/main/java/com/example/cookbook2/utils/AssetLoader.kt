package com.example.cookbook2.utils

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import com.example.cookbook2.App
import com.example.cookbook2.R
import java.io.IOException
import java.io.InputStream

object AssetLoader {

    private val loadedImages: MutableMap<String, Drawable> = mutableMapOf()


    // Loads images from asset folder by path
    // Images are stored in map to prevent reloading the image more times
    // Capacity of stored images is 100, after reaching the maximum is the map cleared
    fun loadImage(path: String): Drawable? {
        return try {

            if(loadedImages.containsKey(path))
                return loadedImages[path]

            val ims: InputStream = App.appContext.assets.open("images/$path")
            val d = Drawable.createFromStream(ims, null) ?: throw IOException()

            if(loadedImages.size > 100)
                loadedImages.clear()

            loadedImages[path] = d
            return d
        } catch (ex: IOException) {
            AppCompatResources.getDrawable(App.appContext, R.drawable.default_recipe_image)
        }
    }
}