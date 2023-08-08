package com.example.uas_if10k_10120917

import com.example.uas_if10k_10120917.adapters.ListMatchLiveAdapter
import ScheduleAdapter
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_if10k_10120917.models.FixturesResponse
import com.example.uas_if10k_10120917.models.ScheduleResponse
import com.example.uas_if10k_10120917.services.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerview = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        val recyclerviewSchedule = rootView.findViewById<RecyclerView>(R.id.recyclerviewSchedule)
        val filter = rootView.findViewById<Button>(R.id.buttonFilter)
        val list = ArrayList<FixturesResponse>()
        val listSchedule = ArrayList<ScheduleResponse>()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().format(formatter)
        val currentDate = LocalDate.now()
        val oneYearLater = currentDate.plusYears(1)
        val oneYearAgo = currentDate.minusYears(1)
        val formattedOneYearLater = oneYearLater.format(formatter)
        val formattedOneYearAgo = oneYearAgo.format(formatter)
        val formData = DataFavouriteClub.getFormData()
        var statusData = "Favourite"

        recyclerview.setHasFixedSize(true)
        recyclerviewSchedule.setHasFixedSize(true)
        recyclerviewSchedule.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val apiService = RetrofitClient.instance
        apiService.getFixtures(formattedOneYearAgo.toString(), current.toString()).enqueue(object : retrofit2.Callback<ArrayList<FixturesResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FixturesResponse>>,
                response: Response<ArrayList<FixturesResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { list.addAll(it) }
                    val adapter = ListMatchLiveAdapter(list)
                    recyclerview.adapter = adapter
                } else {
                    // Tampilkan pesan kesalahan jika ada masalah dengan permintaan API
                    Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<FixturesResponse>>, t: Throwable) {
                // Tangani kesalahan saat permintaan API gagal
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        apiService.getSchedule(current.toString(), formattedOneYearLater.toString()).enqueue(object : retrofit2.Callback<ArrayList<ScheduleResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ScheduleResponse>>,
                response: Response<ArrayList<ScheduleResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listSchedule.addAll(it) }
                    val adapter = ScheduleAdapter(listSchedule)
                    recyclerviewSchedule.adapter = adapter
                } else {
                    // Tampilkan pesan kesalahan jika ada masalah dengan permintaan API
                    Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<ScheduleResponse>>, t: Throwable) {
                // Tangani kesalahan saat permintaan API gagal
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        filter.setOnClickListener {
            if(statusData == "Favourite"){
                apiService.getSchedule(formattedOneYearAgo.toString(), current.toString()).enqueue(object : retrofit2.Callback<ArrayList<ScheduleResponse>> {
                    override fun onResponse(
                        call: Call<ArrayList<ScheduleResponse>>,
                        response: Response<ArrayList<ScheduleResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val filteredList = response.body()?.filter {
                                it.match_hometeam_name?.contains("${formData?.name}", ignoreCase = true) == true
                            } ?: emptyList()
                            val adapter = ScheduleAdapter(ArrayList(filteredList))
                            recyclerviewSchedule.adapter = adapter
                        } else {
                            // Tampilkan pesan kesalahan jika ada masalah dengan permintaan API
                            Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<ScheduleResponse>>, t: Throwable) {
                        // Tangani kesalahan saat permintaan API gagal
                        Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
                statusData = "Semua"
                filter.text = "TampiKan Semua"
            }else {
                apiService.getSchedule(current.toString(), formattedOneYearLater.toString()).enqueue(object : retrofit2.Callback<ArrayList<ScheduleResponse>> {
                    override fun onResponse(
                        call: Call<ArrayList<ScheduleResponse>>,
                        response: Response<ArrayList<ScheduleResponse>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { listSchedule.addAll(it) }
                            val adapter = ScheduleAdapter(listSchedule)
                            recyclerviewSchedule.adapter = adapter
                        } else {
                            // Tampilkan pesan kesalahan jika ada masalah dengan permintaan API
                            Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<ScheduleResponse>>, t: Throwable) {
                        // Tangani kesalahan saat permintaan API gagal
                        Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
                statusData = "Favourite"
                filter.text = "Filter Favourite"
            }


        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}