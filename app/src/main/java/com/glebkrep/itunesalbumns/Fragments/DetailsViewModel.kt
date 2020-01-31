package com.glebkrep.itunesalbumns.Fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.glebkrep.itunesalbumns.Models.Album
import com.glebkrep.itunesalbumns.Models.Song
import com.glebkrep.itunesalbumns.Repository.SearchRepository
import kotlinx.coroutines.Dispatchers

class DetailsViewModel:ViewModel(){
    private val repository: SearchRepository = SearchRepository()
    private var album:Album? = null
    private var songsSaved:List<Song>? = null
    private var albumIdSaved:Long? = null

    fun getSongsFromAlbum(albumId:Long) = liveData(Dispatchers.IO){
        if (albumId==albumIdSaved && !songsSaved.isNullOrEmpty()){
            emit(songsSaved!!)
        }
        else{
            val songSearchResult = repository.getSongsFromAlbum(albumId)
            var songsList = mutableListOf<Song>()
            for (musicObject in songSearchResult.results.subList(1,songSearchResult.results.lastIndex+1)){
                val song = Song(musicObject.trackName!!,musicObject.trackNumber!!,musicObject.trackTimeMillis!!,musicObject.trackExplicitness!!)
                songsList.add(song)
            }
            songsSaved = songsList.toList()
            albumIdSaved = albumId
            emit(songsSaved!!)
        }

    }


    fun setAlbum(album:Album){
        this.album = album
    }
    fun getAlbum():Album?{
        return this.album
    }
}