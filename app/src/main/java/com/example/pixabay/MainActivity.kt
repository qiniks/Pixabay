package com.example.pixabay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pixabay.databinding.ActivityMainBinding
import com.example.pixabay.network.ImageModel
import com.example.pixabay.network.PixabayModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageAdapter: ImageAdapter
    private lateinit var binding: ActivityMainBinding
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()

    }

    private fun initClickers() {
        with(binding) {
            searchBtn.setOnClickListener {
                makeRequest(1, 10)
            }
            swiper.setOnRefreshListener {
                makeRequest(++page, 10)
                swiper.isRefreshing = false
            }
        }
    }

    private fun ActivityMainBinding.makeRequest(page: Int, perPage: Int) {
        val keyWord = keyWord.text.toString()
        App.api.getImageByWord(keyword = keyWord, page = page, per_page = perPage)
            .enqueue(object : Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    if (response.isSuccessful) {
                        imageAdapter = ImageAdapter()
                        imageAdapter.array(response.body()?.hits as ArrayList<ImageModel>)
                        recyclerView.adapter = imageAdapter
                        Log.e("ololo", "onResponse:" + response.body())
                    }
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("ololo", t.message.toString())
                }

            })
    }
}