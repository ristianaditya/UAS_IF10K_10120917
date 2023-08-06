package com.example.uas_if10k_10120917.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.uas_if10k_10120917.MatchDetailFragment
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.WalkTroughMainActivity
import com.example.uas_if10k_10120917.models.FixturesResponse

class ListMatchLiveAdapter(private val mList: List<FixturesResponse>) : RecyclerView.Adapter<ListMatchLiveAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_match, parent, false)

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
        private val imageHome: ImageView = itemView.findViewById(R.id.imageClub1)
        private val imageAway: ImageView = itemView.findViewById(R.id.imageClub2)
        private val textScore: TextView = itemView.findViewById(R.id.textScore)
        private val textDate: TextView = itemView.findViewById(R.id.textDate)
        private val textStatus: TextView = itemView.findViewById(R.id.textStatus)
        private val textGoal: TextView = itemView.findViewById(R.id.goalHome)
        private val textGoalAway: TextView = itemView.findViewById(R.id.goalAway)

        private fun ImageView.loadImageFromUrl(url: String) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun bind(FixturesResponse: FixturesResponse){
            val dataScore:String = "${FixturesResponse.match_hometeam_score} - ${FixturesResponse.match_awayteam_score}"
            val dataScoreDetail:String = "${FixturesResponse.match_hometeam_score}  -  ${FixturesResponse.match_awayteam_score}"
            val dataDate:String = "${ FixturesResponse.match_date } ${ FixturesResponse.match_time }"
            val dataImageClubHome: String = "${ FixturesResponse.team_home_badge }"
            val dataImageClubAway: String = "${ FixturesResponse.team_away_badge }"
            val dataStatus: String = "${ FixturesResponse.match_status }"

            val goalScorersHome = mutableListOf<String>()
            val goalScorersAway = mutableListOf<String>()
            var shotHome:String = "0"
            var shotAway:String = "0"
            var shotTargetHome:String = "0"
            var shotTargetAway:String = "0"
            var ballPossessionHome:String = "0"
            var ballPossessionAway:String = "0"
            var passAkurasiHome:String = "0"
            var passAkurasiAway:String = "0"
            var foulsHome:String = "0"
            var foulsAway:String = "0"
            var yellowHome:String = "0"
            var yellowAway:String = "0"
            var cornersHome:String = "0"
            var cornersAway:String = "0"
            var offsidesHome:String = "0"
            var offsidesAway:String = "0"

            FixturesResponse.goalscorer?.forEach { goalScorer ->
                if (!goalScorer.home_scorer.isNullOrEmpty()) {
                    goalScorersHome.add("${goalScorer.home_scorer} (${goalScorer.time}') \n")
                }

                if (!goalScorer.away_scorer.isNullOrEmpty()) {
                    goalScorersAway.add("${goalScorer.away_scorer} (${goalScorer.time}') \n")
                }
            }

            FixturesResponse.statistics?.forEach { statistics ->
                if(statistics.type == "Shots Total"){
                    shotHome = statistics.home.toString()
                    shotAway = statistics.away.toString()
                }
                if(statistics.type == "Shots On Goal"){
                    shotTargetHome = statistics.home.toString()
                    shotTargetAway = statistics.away.toString()
                }
                if(statistics.type == "Ball Possession"){
                    ballPossessionHome = statistics.home.toString()
                    ballPossessionAway = statistics.away.toString()
                }
                if(statistics.type == "Passes Accurate"){
                    passAkurasiHome = statistics.home.toString()
                    passAkurasiAway = statistics.away.toString()
                }
                if(statistics.type == "Fouls"){
                    foulsHome = statistics.home.toString()
                    foulsAway = statistics.away.toString()
                }
                if(statistics.type == "Yellow Cards"){
                    yellowHome = statistics.home.toString()
                    yellowAway = statistics.away.toString()
                }
                if(statistics.type == "Corners"){
                    cornersHome = statistics.home.toString()
                    cornersAway = statistics.away.toString()
                }
                if(statistics.type == "Offsides"){
                    offsidesHome = statistics.home.toString()
                    offsidesAway = statistics.away.toString()
                }
            }

            val goalScorersHomeText = goalScorersHome.joinToString(", ")
            val goalScorersAwayText = goalScorersAway.joinToString(", ")

            textScore.text = dataScore
            textDate.text = dataDate
            imageHome.loadImageFromUrl(dataImageClubHome)
            imageAway.loadImageFromUrl(dataImageClubAway)
            textStatus.text = dataStatus
            textGoal.text = goalScorersHomeText
            textGoalAway.text = goalScorersAwayText

            itemView.setOnClickListener { v ->
                val activity = v!!.context as AppCompatActivity
                val transaction = activity.supportFragmentManager.beginTransaction()
                val matchDetailFragment = MatchDetailFragment()
                val bundle = Bundle()
                bundle.putString("imageClubHome", dataImageClubHome)
                bundle.putString("imageClubAway", dataImageClubAway)
                bundle.putString("dataScore", dataScoreDetail)
                bundle.putString("goalHome", goalScorersHomeText)
                bundle.putString("goalAway", goalScorersAwayText)
                bundle.putString("shotHome", shotHome)
                bundle.putString("shotAway", shotAway)
                bundle.putString("shotTargetHome", shotTargetHome)
                bundle.putString("shotTargetAway", shotTargetAway)
                bundle.putString("ballPossessionHome", ballPossessionHome)
                bundle.putString("ballPossessionAway", ballPossessionAway)
                bundle.putString("passAkurasiHome", passAkurasiHome)
                bundle.putString("passAkurasiAway", passAkurasiAway)
                bundle.putString("foulsHome", foulsHome)
                bundle.putString("foulsAway", foulsAway)
                bundle.putString("yellowHome", yellowHome)
                bundle.putString("yellowAway", yellowAway)
                bundle.putString("cornersHome", cornersHome)
                bundle.putString("cornersAway", cornersAway)
                bundle.putString("offsidesHome", offsidesHome)
                bundle.putString("offsidesAway", offsidesAway)

                matchDetailFragment.arguments = bundle

                transaction.replace(R.id.homeMenu, matchDetailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }

    }
}