package com.example.uas_if10k_10120917

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MatchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchDetailFragment : Fragment() {
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

    private fun ImageView.loadImageFromUrl(url: String?) {
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_match_detail, container, false)
        val imageClubHome = arguments?.getString("imageClubHome")
        val imageClubAway = arguments?.getString("imageClubAway")
        val dataScored = arguments?.getString("dataScore")
        val dataGoalHome = arguments?.getString("goalHome")
        val dataGoalAway = arguments?.getString("goalAway")
        val shotHome = arguments?.getString("shotHome")
        val shotAway = arguments?.getString("shotAway")
        val shotTargetHome = arguments?.getString("shotTargetHome")
        val shotTargetAway = arguments?.getString("shotTargetAway")
        val ballPossessionHome = arguments?.getString("ballPossessionHome")
        val ballPossessionAway = arguments?.getString("ballPossessionAway")
        val passAkurasiHome = arguments?.getString("passAkurasiHome")
        val passAkurasiAway = arguments?.getString("passAkurasiAway")
        val foulsHome = arguments?.getString("foulsHome")
        val foulsAway = arguments?.getString("foulsAway")
        val yellowHome = arguments?.getString("yellowHome")
        val yellowAway = arguments?.getString("yellowAway")
        val cornersHome = arguments?.getString("cornersHome")
        val cornersAway = arguments?.getString("cornersAway")
        val offsidesHome = arguments?.getString("offsidesHome")
        val offsidesAway = arguments?.getString("offsidesAway")

        val imageHome = rootView.findViewById<ImageView>(R.id.imageHome)
        val imageAway = rootView.findViewById<ImageView>(R.id.imageAway)
        val textScored = rootView.findViewById<TextView>(R.id.textScored)
        val textGoalHome = rootView.findViewById<TextView>(R.id.textGoalHome)
        val textGoalAway = rootView.findViewById<TextView>(R.id.textGoalAway)
        val textShotHome = rootView.findViewById<TextView>(R.id.textShotHome)
        val textShotAway = rootView.findViewById<TextView>(R.id.textShotAway)
        val textShotTargetHome = rootView.findViewById<TextView>(R.id.textShotTargetHome)
        val textShotTargetAway = rootView.findViewById<TextView>(R.id.textShotTargetAway)
        val textPossessionHome = rootView.findViewById<TextView>(R.id.textPossessionHome)
        val textPossessionAway = rootView.findViewById<TextView>(R.id.textPossessionAway)
        val textAkurasiHome = rootView.findViewById<TextView>(R.id.textAkurasiHome)
        val textAkurasiAway = rootView.findViewById<TextView>(R.id.textAkurasiAway)
        val textFoulHome = rootView.findViewById<TextView>(R.id.textFoulHome)
        val textFoulAway = rootView.findViewById<TextView>(R.id.textFoulAway)
        val textYellowHome = rootView.findViewById<TextView>(R.id.textYellowHome)
        val textYellowAway = rootView.findViewById<TextView>(R.id.textYellowAway)
        val textCornerHome = rootView.findViewById<TextView>(R.id.textCornerHome)
        val textCornerAway = rootView.findViewById<TextView>(R.id.textCornerAway)
        val textOffsideHome = rootView.findViewById<TextView>(R.id.textOffsideHome)
        val textOffsideAway = rootView.findViewById<TextView>(R.id.textOffsideAway)


        imageHome.loadImageFromUrl(imageClubHome)
        imageAway.loadImageFromUrl(imageClubAway)
        textScored.text = dataScored
        textGoalHome.text = dataGoalHome
        textGoalAway.text = dataGoalAway
        textShotHome.text = shotHome
        textShotAway.text = shotAway
        textShotTargetHome.text = shotTargetHome
        textShotTargetAway.text = shotTargetAway
        textPossessionHome.text = ballPossessionHome
        textPossessionAway.text = ballPossessionAway
        textAkurasiHome.text = passAkurasiHome
        textAkurasiAway.text = passAkurasiAway
        textFoulHome.text = foulsHome
        textFoulAway.text = foulsAway
        textYellowHome.text = yellowHome
        textYellowAway.text = yellowAway
        textCornerHome.text = cornersHome
        textCornerAway.text = cornersAway
        textOffsideHome.text = offsidesHome
        textOffsideAway.text = offsidesAway

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MatchDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MatchDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}