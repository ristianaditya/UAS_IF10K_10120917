package com.example.uas_if10k_10120917.models

data class FixturesResponse(
    val match_date:String?,
    val match_time:String?,
    val match_hometeam_name:String?,
    val match_hometeam_score:String?,
    val match_awayteam_name:String?,
    val match_awayteam_score:String?,
    val team_home_badge:String?,
    val team_away_badge:String?,
    val match_stadium:String?,
    val match_referee:String?,
    val match_round:String?,
    val match_status:String?,
    val goalscorer:List<GoalScorer>?,
    val statistics:List<Statistics>?,
)

data class GoalScorer(
    val time: String?,
    val home_scorer: String?,
    val away_scorer: String?,
    val info: String?,
    val score_info_time: String?
)

data class Statistics(
    val type: String?,
    val home: String?,
    val away: String?,
)