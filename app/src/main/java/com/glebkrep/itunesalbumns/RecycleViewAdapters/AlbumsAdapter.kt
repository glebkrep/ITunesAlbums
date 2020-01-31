package com.glebkrep.itunesalbumns.RecycleViewAdapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glebkrep.itunesalbumns.Fragments.DetailsFragment
import com.glebkrep.itunesalbumns.Models.Album
import com.glebkrep.itunesalbumns.R
import com.glebkrep.itunesalbumns.Utils.MyGlideUtils

class AlbumsAdapter internal constructor(val context: Context, val fragment:Fragment): RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var albumsList = emptyList<Album>()

    inner class AlbumsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val albumArt = itemView.findViewById<ImageView>(R.id.albumArtImage)
        val artistText = itemView.findViewById<TextView>(R.id.albumArtistText)
        val albumExcplicitImage = itemView.findViewById<ImageView>(R.id.albumExplicitImage)
        val albumName = itemView.findViewById<TextView>(R.id.albumName)
        val view = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView = inflater.inflate(R.layout.album_rv_item,parent,false)
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount() = albumsList.size

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val current = albumsList[position]

        MyGlideUtils.loadImage(context,current.artworkUrl100,holder.albumArt)

        holder.artistText.text = current.artistName
        holder.albumName.text = current.collectionName

        if (current.collectionExplicitness == DetailsFragment.NOT_EXPLICIT) holder.albumExcplicitImage.visibility = View.INVISIBLE
        else holder.albumExcplicitImage.visibility = View.VISIBLE

        holder.view.setOnClickListener {
            fragment?.let {
                val bundle = Bundle()
                bundle.putParcelable(DetailsFragment.ALBUM_KEY,current)
                it.findNavController().navigate(R.id.action_searchFragment_to_detailsFragment,bundle)
            }
        }
    }

    fun setAlbums(albums:List<Album>){
        this.albumsList = albums
        notifyDataSetChanged()
    }

    fun getAlbums():List<Album> = this.albumsList
}