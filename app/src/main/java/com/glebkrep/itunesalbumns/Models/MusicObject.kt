package com.glebkrep.itunesalbumns.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MusicObject(
    val wrapperType:String,
    val artistName:String,
    val collectionId:Long,
    val collectionName:String,
    val artworkUrl100:String,
    val collectionPrice:Float,
    val collectionExplicitness:String,
    val releaseDate:String,
    val primaryGenreName:String,
    val currency:String,
    val country:String,

    //album only
    val copyright:String? = null,

    //song only
    val trackName:String? = null,
    val trackNumber:Int? = null,
    val trackTimeMillis:Long? = null,
    val trackExplicitness:String? = null
)

data class Song(
    val trackName:String,
    val trackNumber:Int,
    val trackTimeMillis:Long,
    val trackExplicitness:String
)
@Parcelize
data class Album(
    val artistName:String,
    val collectionId:Long,
    val collectionName:String,
    val artworkUrl100:String,
    val collectionPrice:Float,
    val collectionExplicitness:String,
    val releaseDate:String,
    val primaryGenreName:String,
    val currency:String,
    val country:String,
    val copyright:String
    ):Parcelable

data class AlbumSearchResult(
    val results:List<Album>
)
data class SongSearchResult(
    val results: List<MusicObject>
)