package com.jesil.projectmvi.mviapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jesil.projectmvi.mviapplication.R
import com.jesil.projectmvi.mviapplication.model.Blog
import com.jesil.projectmvi.mviapplication.retrofit.BlogNetworkEntity
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogAdapter : RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {
    private var blog : List<Blog> = ArrayList<Blog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        when(holder){
            is BlogViewHolder ->{
                holder.bind(blog[position])
            }
        }
    }

    override fun getItemCount(): Int = blog.size

    class BlogViewHolder constructor(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView){

        private val title: TextView = itemView.text_view_title_blog
        private val body : TextView = itemView.text_view_body_blog
        private val image : ImageView = itemView.image_view_blog
        private val category : TextView = itemView.text_view_category_blog

        fun bind(blog : Blog){
            title.text = blog.title
            body.text = blog.body
            category.text = blog.category

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_error)

            Glide.with(itemView.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(blog.image)
                    .into(image)
        }
    }

    fun setBlogData(blogList : List<Blog>){
        blog = blogList
    }




}