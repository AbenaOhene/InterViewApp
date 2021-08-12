package com.abeapps.myapplication.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abeapps.myapplication.R
import com.abeapps.myapplication.databinding.ActivityMainBinding
import com.abeapps.myapplication.model.data.Hit
import com.abeapps.myapplication.model.data.ImageResponse
import com.abeapps.myapplication.view.adapter.HitAdapter
import com.abeapps.myapplication.viewmodel.ImageCentralViewModel
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private val imageViewModel: ImageCentralViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val hitAdapter = HitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.hitRv.adapter = hitAdapter
        addScrollListenerToRecyclerView()

        imageViewModel
            .imageData
            .observe(this, {

                when(it){

                    is ImageResponse.Success -> {

                        loadIntoRecyclerView(it.data)
                        binding.progressBar.visibility = View.GONE
                    }
                    is ImageResponse.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ImageResponse.Error -> {
                        Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                    }
                }


            })
    }

    private fun addScrollListenerToRecyclerView() {
        binding.hitRv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPosition = (binding.hitRv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if(lastPosition == hitAdapter.itemCount-1) {
                    imageViewModel.getImages()
                }
            }
        })



    }

    private fun loadIntoRecyclerView(hitList: List<Hit>) {
        hitAdapter.addAllHits(hitList)
    }
}