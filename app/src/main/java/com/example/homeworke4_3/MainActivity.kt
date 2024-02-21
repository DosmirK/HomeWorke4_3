package com.example.homeworke4_3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworke4_3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = PixabayAdapter(arrayListOf())
    private var page = 1
    private var oldWord = ""
    private var newWord = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickers()
        binding.rvPixabay.adapter = adapter
    }

    private fun initClickers() {
        with(binding) {
            rvPixabay.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (oldWord != newWord) {
                            Toast.makeText(
                                this@MainActivity,
                                "Обновите поиск",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            ++page
                            requestImage()
                        }
                    }
                }
            })
            btnSearch.setOnClickListener {
                newWord = etSearch.text.toString()
                if (oldWord != newWord) {
                    adapter.list.clear()
                    page = 1
                }
                requestImage()
            }
        }
    }

    private fun requestImage() {
        RetrofitService().api.getImages(searchWord = binding.etSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixabayModel> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                    if (response.isSuccessful) {
                        oldWord = binding.etSearch.text.toString()
                        adapter.list.addAll(response.body()!!.hits)
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }
            })
    }
}
