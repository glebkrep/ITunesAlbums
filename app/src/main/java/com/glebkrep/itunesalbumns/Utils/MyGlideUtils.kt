package com.glebkrep.itunesalbumns.Utils

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.glebkrep.itunesalbumns.R

object MyGlideUtils {
    private fun getCircularProgressBar(context: Context):CircularProgressDrawable{
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    fun loadImage(context: Context,load:String,into:ImageView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Glide.with(context)
                .load(load)
                .placeholder(getCircularProgressBar(context))
                .error(context.resources.getDrawable(R.drawable.ic_no_internet,null))
                .into(into)
        }
        else{
            Glide.with(context)
                .load(load)
                .placeholder(getCircularProgressBar(context))
                .error(context.resources.getDrawable(R.drawable.ic_no_internet))
                .into(into)
        }
    }
    fun loadPlaceHolder(context: Context,into: ImageView){
        Glide.with(context)
            .load(getCircularProgressBar(context))
            .into(into)
    }
    fun loadNoInternetImage(context: Context,into: ImageView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Glide.with(context)
                .load(context.resources.getDrawable(R.drawable.ic_no_internet,null))
                .into(into)
        }
        else{
            Glide.with(context)
                .load(context.resources.getDrawable(R.drawable.ic_no_internet))
                .into(into)
        }
    }

    fun loadNothingFoundImage(context: Context, into: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Glide.with(context)
                .load(context.resources.getDrawable(R.drawable.ic_no_albums,null))
                .into(into)
        }
        else{
            Glide.with(context)
                .load(context.resources.getDrawable(R.drawable.ic_no_albums))
                .into(into)
        }

    }
}