package com.example.uas_if10k_10120917.models

data class ScheduleResponse (
    val match_date:String?,
    val match_time:String?,
    val match_hometeam_name:String?,
    val match_hometeam_id:String?,
    val match_awayteam_id:String?,
    val match_hometeam_score:String?,
    val match_awayteam_name:String?,
    val match_awayteam_score:String?,
    val team_home_badge:String?,
    val team_away_badge:String?,
    val match_stadium:String?,
    val match_referee:String?,
    val match_round:String?,
    val match_status:String?
)