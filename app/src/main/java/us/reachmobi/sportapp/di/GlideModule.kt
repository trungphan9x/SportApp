package us.reachmobi.sportapp.di

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val diskCacheSizeBytes = 1024 * 1024 * 2000 // 2000 MB
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        builder.setDiskCache(
            InternalCacheDiskCacheFactory(context, "cache_news_app", diskCacheSizeBytes.toLong())
        )
        //
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
    }

    override fun isManifestParsingEnabled(): Boolean = false

}