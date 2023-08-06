package com.example.uas_if10k_10120917

import ClubAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_if10k_10120917.adapters.ListMatchLiveAdapter
import com.example.uas_if10k_10120917.models.ClubResponse
import com.example.uas_if10k_10120917.models.FixturesResponse
import com.example.uas_if10k_10120917.services.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class SelectTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)

        val courseRV = findViewById<RecyclerView>(R.id.gridFavorite)
        val layoutManager = GridLayoutManager(this, 3)
        val list = ArrayList<ClubResponse>()

        courseRV.setHasFixedSize(true)
        courseRV.layoutManager = layoutManager

        val apiService = RetrofitClient.instance
        apiService.getClub().enqueue(object : retrofit2.Callback<ArrayList<ClubResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ClubResponse>>,
                response: Response<ArrayList<ClubResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { list.addAll(it) }
                    val adapter = ClubAdapter(list)
                    courseRV.adapter = adapter
                } else {
                    // Tampilkan pesan kesalahan jika ada masalah dengan permintaan API
                    Toast.makeText(this@SelectTeamActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ClubResponse>>, t: Throwable) {
                // Tangani kesalahan saat permintaan API gagal
                Toast.makeText(this@SelectTeamActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}