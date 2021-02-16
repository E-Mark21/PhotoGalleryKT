package com.myprog.photogallery.imageviewercreen.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprog.photogallery.R
import com.myprog.photogallery.fullsreenphoto.EXTRA_DATA
import com.myprog.photogallery.fullsreenphoto.EXTRA_URL
import com.myprog.photogallery.fullsreenphoto.FullScreenPhoto
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.presenter.PhotoViewerPresenter
import com.myprog.photogallery.imageviewercreen.repository.model.Photo
import com.squareup.picasso.Picasso


class PhotoViewerScreen : Fragment(), MainContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mPresenter: MainContract.Presenter
    private var adapter: PhotoAdapter? = null


    var images: ArrayList<String> = arrayListOf()
    var imgArray: ArrayList<Photo> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mPresenter = PhotoViewerPresenter(this, context)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_grid_view, container, false)
        recyclerView = view.findViewById(R.id.photo_recycler) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        adapter = PhotoAdapter(images, imgArray)
        recyclerView.adapter = adapter
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView

        searchView.apply {

            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(queryText: String): Boolean {
                    hideKeyboard()
                    mPresenter.searchIMG(queryText)
                    return true
                }

                override fun onQueryTextChange(queryText: String): Boolean {
                    return false
                }

            })
        }
    }

    fun hideKeyboard(){
        val imm: InputMethodManager = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken,0)
    }

    override fun updateAdapter(images: ArrayList<String>, imgArray: ArrayList<Photo>) {
        this.images = images
        this.imgArray = imgArray
        if (adapter == null) {
            adapter = PhotoAdapter(images, imgArray)
            recyclerView.adapter = adapter
        } else {
            adapter!!.updateItem(images, imgArray)
            adapter!!.notifyDataSetChanged()

        }
    }


    private inner class PhotoAdapter(
        var images: ArrayList<String>,
        var imgArray: ArrayList<Photo>
    ) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.photo_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.with(holder.itemView.context)
                .load(images[position])
                .into(holder.imageView)
        }

        override fun getItemCount(): Int {
            return images.size
        }

        fun updateItem(images: ArrayList<String>, imgArray: ArrayList<Photo>) {
            this.images = images
            this.imgArray = imgArray
            notifyDataSetChanged()
        }
    }


    private inner class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var imageView: ImageView = itemView.findViewById(R.id.imageView)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val intent = Intent(context, FullScreenPhoto::class.java)

            intent.putExtra(EXTRA_URL, images[position])
                .putExtra(EXTRA_DATA, imgArray[position])
            startActivity(intent)

        }
    }

    companion object {
        fun newInstance(): PhotoViewerScreen {
            return PhotoViewerScreen()
        }
    }

}