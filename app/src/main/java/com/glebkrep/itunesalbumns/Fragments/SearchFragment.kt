package com.glebkrep.itunesalbumns.Fragments


import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.glebkrep.itunesalbumns.Models.Album
import com.glebkrep.itunesalbumns.R
import com.glebkrep.itunesalbumns.RecycleViewAdapters.AlbumsAdapter
import com.glebkrep.itunesalbumns.Utils.MyGlideUtils
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    lateinit var viewModel:SearchViewModel
    lateinit var adapter:AlbumsAdapter
    lateinit var layoutManager:LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        adapter = AlbumsAdapter(context!!,this)
        layoutManager = LinearLayoutManager(context)
        albumsRecyclerView.adapter = adapter
        albumsRecyclerView.layoutManager = layoutManager

        val savedAlbums = viewModel.savedAlbums()
        if (savedAlbums!=null){
            adapter.setAlbums(savedAlbums)
        }


        searchView.setOnQueryTextListener(
            object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        observe(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        observe(it)
                    }
                    return false
                }

            }
        )
    }

    private fun observe(text:String){
        searchStartText.visibility = View.INVISIBLE
        searchRVImage.visibility = View.VISIBLE
        context?.let {
            MyGlideUtils.loadPlaceHolder(it,searchRVImage)
        }

        viewModel.searchForAlbums(text).observe(this@SearchFragment, Observer {
            if (it.isNullOrEmpty()){
                //IMAGE: NOTHING IS FOUND
                context?.let {
                    MyGlideUtils.loadNothingFoundImage(it,searchRVImage)
                }
                adapter.setAlbums(listOf())
            }
            else {
                searchRVImage.visibility = View.INVISIBLE
                adapter.setAlbums(it)
            }
        })
    }


}
