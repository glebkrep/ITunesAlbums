package com.glebkrep.itunesalbumns.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.glebkrep.itunesalbumns.Models.Album
import com.glebkrep.itunesalbumns.Utils.MyTextUtils
import com.glebkrep.itunesalbumns.R
import com.glebkrep.itunesalbumns.RecycleViewAdapters.SongsAdapter
import com.glebkrep.itunesalbumns.Utils.MyGlideUtils
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(R.layout.fragment_details) {

    lateinit var viewModel:DetailsViewModel
    lateinit var album:Album
    lateinit var adapter:SongsAdapter
    lateinit var layoutManager:LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mActivity = activity as AppCompatActivity
        mActivity?.let {
            it.setSupportActionBar(detailsToolbar)
            it.supportActionBar!!.setDisplayShowHomeEnabled(true)
            it.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

            detailsToolbar.setNavigationOnClickListener {
                activity?.let {
                 it.onBackPressed()
                }
            }
        }

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        if (arguments!=null){
            arguments?.let {
                if (it.containsKey(ALBUM_KEY)){
                    album = it.getParcelable(ALBUM_KEY)!!
                    viewModel.setAlbum(album)
                    updateUi(album)
                }
            }
        }
        else{
            val tempAlbum = viewModel.getAlbum()
            tempAlbum?.let {
                album = it
                updateUi(album)
            }
        }

    }

    private fun updateUi(album: Album){
        context?.let {
            MyGlideUtils.loadImage(it,album.artworkUrl100,detailsAlbumArtImage)
        }

        detailsAlbumArtist.text = album.artistName

        if (album.collectionExplicitness == NOT_EXPLICIT) detailsAlbumExcplicitImage.visibility = View.INVISIBLE
        else detailsAlbumExcplicitImage.visibility = View.VISIBLE


        detailsAlbumGenreAndReleaseDate.text = album.primaryGenreName + getString(R.string.commaSpace) + MyTextUtils.formatReleaseDate(album.releaseDate)

        detailsAlbumName.text = album.collectionName

        detailsAlbumPriceCurrency.text = album.collectionPrice.toString()+getString(R.string.space)+album.currency

        detailsCountryCopyright.text = album.country + getString(R.string.space) + album.copyright

        adapter = SongsAdapter(context!!)
        layoutManager = LinearLayoutManager(context)
        tracksRecyclerView.layoutManager = layoutManager
        tracksRecyclerView.adapter = adapter
        tracksRecyclerView.isNestedScrollingEnabled = false

        viewModel.getSongsFromAlbum(albumId = album.collectionId).observe(this, Observer {
            adapter.setSongs(it)
        })
    }

    companion object{
        const val ALBUM_KEY = "album"
        const val NOT_EXPLICIT = "notExplicit"
    }
}
