package com.example.uas_if10k_10120917.models

data class TeamPlayerResponse(
    val team_key:String?,
    val team_name:String?,
    val team_badge:String?,
    val players:List<Player>?,
)

data class Player(
    val player_name: String?,
    val player_number: String?,
    val player_type: String?,
    val player_image: String?,
    val player_match_played: String?,
    val player_goals: String?,
    val player_rating: String?,
    val player_birthdate: String?,
    val player_yellow_cards: String?,
    val player_red_cards: String?,
    val player_age: String?,
    val player_passes: String?,
)