package com.myprog.photogallery.imageviewercreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprog.photogallery.R
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.presenter.PhotoViewerPresenter
import com.squareup.picasso.Picasso


class PhotoViewerScreen : Fragment(), MainContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mPresenter: MainContract.Presenter
    private var adapter: PhotoAdapter? = null

    var images: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = PhotoViewerPresenter(this, context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_grid_view, container, false)
        recyclerView = view.findViewById(R.id.photo_recycler) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = PhotoAdapter(images)
        recyclerView.adapter = adapter
        return view
    }


    override fun updateAdapter(images: ArrayList<String>) {
        if (adapter == null) {
            adapter = PhotoAdapter(images)
            recyclerView.adapter = adapter
        } else {
            adapter!!.updateItem(images)
            adapter!!.notifyDataSetChanged()
        }
    }





    private inner class PhotoAdapter(images: ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {

        var images = images

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.photo_grid_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.with(holder.itemView.context)
                .load(images[position])
               // .placeholder(R.drawable.placeholder)
                .into(holder.imageView)

        }

        override fun getItemCount(): Int {
            return images.size
        }

        fun updateItem(images: ArrayList<String>) {

            notifyDataSetChanged()
        }
    }



    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        var imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {this}
        }

        override fun onClick(v: View) {

        }
    }

    companion object {
        fun newInstance(): PhotoViewerScreen {
            return PhotoViewerScreen()
        }
    }

}