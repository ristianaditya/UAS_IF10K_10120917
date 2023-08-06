package com.example.uas_if10k_10120917

import com.example.uas_if10k_10120917.models.ClubFavouriteModel

object DataFavouriteClub {
    private var formData: ClubFavouriteModel? = null

    fun saveFormData(data: ClubFavouriteModel) {
        formData = data
    }

    fun getFormData(): ClubFavouriteModel? {
        return formData
    }
}