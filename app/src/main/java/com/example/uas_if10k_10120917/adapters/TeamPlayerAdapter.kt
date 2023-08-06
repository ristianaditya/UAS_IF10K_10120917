package com.example.uas_if10k_10120917.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.uas_if10k_10120917.DataFavouriteClub
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.models.Player
import com.example.uas_if10k_10120917.models.TeamPlayerResponse

class TeamPlayerAdapter(private val mList: List<Player>) : RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_player, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]
        holder.bind(itemsViewModel)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textType: TextView = itemView.findViewById(R.id.textType)
        private val textNumber: TextView = itemView.findViewById(R.id.textNumber)
        private val imagePlayer: ImageView = itemView.findViewById(R.id.imagePlayer)
        private val imageClub: ImageView = itemView.findViewById(R.id.imageClub)
        private val textClub: TextView = itemView.findViewById(R.id.textClub)

        private fun ImageView.loadImageFromUrl(url: String) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun bind(Player: Player){
            val playerName: String = "${ Player.player_name }"
            val playerType: String = "${ Player.player_type }"
            val playerNumber: String = "${ Player.player_number }"
            val playerImage: String = "${ Player.player_image }"

            val formData = DataFavouriteClub.getFormData()
            formData?.image?.let { imageClub.loadImageFromUrl(it) }

            textName.text = playerName
            textType.text = playerType
            textNumber.text = playerNumber
            textClub.text = formData?.name
            imagePlayer.loadImageFromUrl(playerImage)
        }
    }
}