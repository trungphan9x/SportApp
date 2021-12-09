package us.reachmobi.sportapp.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import us.reachmobi.sportapp.R
import us.reachmobi.sportapp.di.GlideApp
import us.reachmobi.sportapp.data.remote.response.Leagues
import us.reachmobi.sportapp.ui.rvadapter.LeagueRVAdapter
import us.reachmobi.sportapp.util.extension.hideKeyboard
import us.reachmobi.sportapp.util.extension.showKeyboard
import us.reachmobi.sportapp.util.extension.toReadableTimeWIB

@BindingAdapter("hideKeyboard")
fun hideKeyboardOnclick(view: View, isHide: Boolean) {
    if (isHide)
        view.hideKeyboard()
}

@BindingAdapter("showKeyboard")
fun showKeyboardOnclick(view: View, isShow: Boolean) {
    if (isShow)
        view.showKeyboard()
}


@BindingAdapter("setUrlImage")
fun ImageView.setUrlImage(url: String?) {
    GlideApp.with(this)
        .load(url)
        .centerCrop()
        .fitCenter()
        .placeholder(R.drawable.default_image)
        .into(this)
}

@BindingAdapter("setUrlRoundedImage")
fun ImageView.setUrlRoundedImage(url: String?) {
    scaleType = ImageView.ScaleType.FIT_CENTER
    GlideApp.with(this)
        .load(url)
        .transforms(CircleCrop())
        .placeholder(R.drawable.default_image)
        .into(this)
}

@BindingAdapter(value = ["time", "dateEvent"], requireAll = true)
fun TextView.dateFormat(time: String?, dateEvent: String?) {
    this.text = dateEvent?.let { time?.toReadableTimeWIB(it) }
}

