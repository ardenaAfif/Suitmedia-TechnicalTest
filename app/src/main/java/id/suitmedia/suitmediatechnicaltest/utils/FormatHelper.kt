package id.suitmedia.suitmediatechnicaltest.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object FormatHelper {

    fun isPalindrome(sentece: String): Boolean {
        val cleanString = sentece.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanString == cleanString.reversed()
    }

    fun ImageView.setImageFromUrl(context: Context, url: String) {
        Glide.with(context)
            .load(url)
            .transform(CircleCrop())
            .into(this)
    }
}