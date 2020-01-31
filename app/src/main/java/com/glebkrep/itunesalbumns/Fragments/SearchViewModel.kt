package com.glebkrep.itunesalbumns.Fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.glebkrep.itunesalbumns.Models.Album
import com.glebkrep.itunesalbumns.Models.Song
import com.glebkrep.itunesalbumns.Repository.SearchRepository
import kotlinx.coroutines.Dispatchers

class SearchViewModel: ViewModel(){
    private val repository: SearchRepository = SearchRepository()

    private var savedAlbums:List<Album> = listOf()

    fun searchForAlbums(searchText:String) = liveData(Dispatchers.IO){
        val encodedSearchText = encodeSearchText(searchText)
        try {
            val albumSearchResult = repository.searchForAlbum(encodedSearchText)
            var albums = albumSearchResult.results
            //sorting alphabetically
            albums =albums.sortedBy { it.collectionName }
            //removing duplicates
            albums = albums.distinctBy { it.collectionId }
            albums = albums.distinctBy { Pair(it.artistName,it.collectionName) }
            savedAlbums = albums
            emit(albums)
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun savedAlbums():List<Album>?{
        if (savedAlbums.isNullOrEmpty()) return null
        else return savedAlbums
    }

    private fun encodeSearchText(searchText: String): String {
        var newText = searchText
        newText = newText.replace(" ","+")
        return newText
    }

}