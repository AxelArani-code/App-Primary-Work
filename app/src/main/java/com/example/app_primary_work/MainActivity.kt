package com.example.app_primary_work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.app_primary_work.Adapter.PostListAdapter
import com.example.app_primary_work.Fragment.*
import com.example.app_primary_work.Model.fireBaseRepo
import com.example.app_primary_work.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

private const val  TAG: String = "HOMEPAGE_LOG"
class MainActivity : AppCompatActivity() {


    private var postList: List<PostModel> = ArrayList()
    private val firebaseRepo: fireBaseRepo = fireBaseRepo()
    private val postListAdapter: PostListAdapter = PostListAdapter(postList)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Check User
        if (firebaseRepo.getUser() == null){
            //Create new user
            firebaseRepo.creareUser().addOnCompleteListener{
                if(it.isSuccessful){
                    loadPostData()
                }else{
                    Log.d(TAG, "Error: ${it.exception!!.message}")
                }
            }
        }else{
            //user Logged in
            loadPostData()
        }
        //Init RecyclerView

        //firestore_list.layoutManager = LinearLayoutManager(this)
       // firestore_list.adapter = postListAdapter



//ButtomNavigation
        addFragment(HomeFragment.newInstance())
        binding.bottomNavigation.show(0)

        binding.bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_explore))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_identity))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_notifications))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_chat))

        binding.bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 ->{
                    replaceFragment(HomeFragment.newInstance())
                }
                1 ->{
                    replaceFragment(ExploreFragment.newInstance())
                }
                2 ->{
                    replaceFragment(UserFragment.newInstance())
                }
                3 ->{
                    replaceFragment(NotificationFragment.newInstance())
                }
                4 ->{
                    replaceFragment(ChatFragment.newInstance())
                }
                else->{
                    replaceFragment(HomeFragment.newInstance())
                }

            }
        }
    }

    private fun loadPostData() {
        firebaseRepo.getPostList().addOnCompleteListener{
            if(it.isSuccessful){
                postList = it.result!!.toObjects(PostModel::class.java)
                postListAdapter.postListItems = postList
                postListAdapter.notifyDataSetChanged()
            }else{
                Log.d(TAG, "Error: ${it.exception!!.message}")
            }
        }

    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}