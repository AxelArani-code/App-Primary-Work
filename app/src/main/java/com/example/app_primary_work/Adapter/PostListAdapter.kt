package com.example.app_primary_work.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_primary_work.PostModel
import com.example.app_primary_work.R
import kotlinx.android.synthetic.main.item_with_desc.view.*
import kotlinx.android.synthetic.main.item_with_image.view.*


private  const val  POST_TYPE_DESC: Int = 0
private const val  POST_TYPE_IMAGE: Int = 1

class PostListAdapter (var postListItems:List<PostModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    //View holders For all types of items
    class DescViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postModel:PostModel){
            itemView.desc_post_title.text = postModel. title
            itemView.desc_post_desc.text = postModel.desc

        }

    }

    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(postModel:PostModel){
            itemView.image_post_title.text = postModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       if (viewType == POST_TYPE_DESC){
           val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_desc,parent,false)
           return  DescViewHolder(view)
       }else{
           val view = LayoutInflater.from(parent.context).inflate(R.layout.item_with_image, parent,false)
           return ImageViewHolder(view)
       }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         if(getItemViewType(position) == POST_TYPE_DESC){
            (holder as DescViewHolder).bind(postListItems[position])
        }else{
             (holder as ImageViewHolder).bind(postListItems[position])
         }
    }


    override fun getItemCount(): Int {
        return postListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (postListItems[position].post_type == 0L){
            POST_TYPE_DESC
        }else{
            POST_TYPE_IMAGE
        }
    }

}