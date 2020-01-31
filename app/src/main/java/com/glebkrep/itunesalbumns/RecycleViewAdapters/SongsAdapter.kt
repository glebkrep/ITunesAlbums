package com.glebkrep.itunesalbumns.RecycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glebkrep.itunesalbumns.Fragments.DetailsFragment
import com.glebkrep.itunesalbumns.Models.Song
import com.glebkrep.itunesalbumns.Utils.MyTextUtils
import com.glebkrep.itunesalbumns.R

class SongsAdapter internal constructor(val context: Context): RecyclerView.Adapter<SongsAdapter.SongsViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var songsList = emptyList<Song>()

    inner class SongsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val trackNumber = itemView.findViewById<TextView>(R.id.trackNumber)
        val trackName = itemView.findViewById<TextView>(R.id.trackName)

        val trackDuration = itemView.findViewById<TextView>(R.id.trackDuration)
        val trackExcplicitImage = itemView.findViewById<ImageView>(R.id.trackExcplicitImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = inflater.inflate(R.layout.track_rv_item,parent,false)
        return SongsViewHolder(itemView)
    }

    override fun getItemCount() = songsList.size

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        val current = songsList[position]
        holder.trackNumber.text = current.trackNumber.toString()
        holder.trackName.text = current.trackName
        holder.trackDuration.text = MyTextUtils.formatDuration(current.trackTimeMillis)

        if (current.trackExplicitness==DetailsFragment.NOT_EXPLICIT){
            holder.trackExcplicitImage.visibility = View.INVISIBLE
        }
        else holder.trackExcplicitImage.visibility = View.VISIBLE
    }



    fun setSongs(songs:List<Song>){
        this.songsList = songs
        notifyDataSetChanged()
    }

    fun getSongs():List<Song> = this.songsList
}