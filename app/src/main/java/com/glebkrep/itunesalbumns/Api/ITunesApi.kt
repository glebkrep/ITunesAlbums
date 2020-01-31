package com.glebkrep.itunesalbumns.Api

import com.glebkrep.itunesalbumns.Models.AlbumSearchResult
import com.glebkrep.itunesalbumns.Models.SongSearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITunesApi {
    @GET("/search")
    //?term={search_text}&entity=album"
    suspend fun searchForAlbums(
        @Query("term",encoded = true)searchText:String,
        @Query("entity") entity:String = "album"): AlbumSearchResult

    @GET("/lookup")
    //?id={albumId}&entity=song&media=music
    suspend fun getSongsForAlbum(
        @Query("id") albumId:Long,
        @Query("entity")entity: String = "song",
        @Query("media")media:String = "music"):SongSearchResult
}