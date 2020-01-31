package com.glebkrep.itunesalbumns.Repository

import com.glebkrep.itunesalbumns.Api.ITunesApi
import com.glebkrep.itunesalbumns.Api.RetrofitClient

class SearchRepository {
    var client:ITunesApi = RetrofitClient.client

    suspend fun searchForAlbum(searchText:String) = client.searchForAlbums(searchText)

    suspend fun getSongsFromAlbum(albumId:Long) = client.getSongsForAlbum(albumId)
}