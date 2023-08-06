package com.example.uas_if10k_10120917.services

import com.example.uas_if10k_10120917.DataFavouriteClub
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.models.ClubResponse
import com.example.uas_if10k_10120917.models.FixturesResponse
import com.example.uas_if10k_10120917.models.ScheduleResponse
import com.example.uas_if10k_10120917.models.StandingsResponse
import com.example.uas_if10k_10120917.models.TeamPlayerResponse
import com.example.uas_if10k_10120917.models.TopScoredResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    companion object {
        const val API_KEY = "628a1ccbde955634d6e7b2a5581679136e5009add315cfa5e21d73e7fcf5317a"
        const val league_id = "152"
    }

    @GET("?action=get_events&from=2023-05-01&to=2023-08-01&league_id=$league_id&APIkey=$API_KEY")
    fun getFixtures(): Call<ArrayList<FixturesResponse>>

    @GET("?action=get_events&from=2023-08-01&to=2024-08-01&league_id=$league_id&APIkey=$API_KEY")
    fun getSchedule(): Call<ArrayList<ScheduleResponse>>

    @GET("?action=get_standings&league_id=$league_id&APIkey=$API_KEY")
    fun getStandings(): Call<ArrayList<StandingsResponse>>

    @GET("?action=get_topscorers&league_id=$league_id&APIkey=$API_KEY")
    fun getTopScored(): Call<ArrayList<TopScoredResponse>>

    @GET("?action=get_teams&league_id=$league_id&APIkey=$API_KEY")
    fun getClub(): Call<ArrayList<ClubResponse>>

    @GET("?action=get_teams&league_id=$league_id&APIkey=$API_KEY")
    fun getPlayer(@Query("team_id") teamId: String): Call<ArrayList<TeamPlayerResponse>>
}