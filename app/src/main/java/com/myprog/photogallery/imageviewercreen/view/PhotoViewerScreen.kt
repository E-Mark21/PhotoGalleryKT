package com.myprog.photogallery.imageviewercreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprog.photogallery.R
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.presenter.PhotoViewerPresenter

class PhotoViewerScreen : Fragment(), MainContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mPresenter: MainContract.Presenter
    private var adapter: PhotoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = PhotoViewerPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.photo_grid_view, container, false)
        recyclerView.layoutManager = GridLayoutManager(context, 2 )
        adapter = PhotoAdapter()
        recyclerView.adapter = adapter
        return view
    }

    fun updateAdapter() {
        if (adapter == null) {
            adapter = PhotoAdapter()
            recyclerView.adapter = adapter
        } else {
            adapter!!.updateItem()
            adapter!!.notifyDataSetChanged()
        }
    }

    private inner class PhotoAdapter(): RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.photo_grid_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind()

        }

        override fun getItemCount(): Int {
            return 10
        }

        fun updateItem(


        ) {

            notifyDataSetChanged()
        }

    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {



        init {
            itemView.setOnClickListener {this}
        }

        fun bind(

        ) {

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