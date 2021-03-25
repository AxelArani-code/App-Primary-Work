package com.example.app_primary_work.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_primary_work.Adapter.PostListAdapter
import com.example.app_primary_work.PostModel
import com.example.app_primary_work.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private var postList: List<PostModel> = ArrayList()
    private val postListAdapter: PostListAdapter = PostListAdapter(postList)

    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_home, container, false)


    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        postListAdapter.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            firestore_list.layoutManager = LinearLayoutManager(activity)
            firestore_list.adapter = postListAdapter

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }


    }
}