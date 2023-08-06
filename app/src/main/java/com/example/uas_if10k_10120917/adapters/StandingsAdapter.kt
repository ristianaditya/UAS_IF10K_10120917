import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.uas_if10k_10120917.DataFavouriteClub
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.models.StandingsResponse

class StandingsAdapter(private val mList: List<StandingsResponse>) : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_standings, parent, false)

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
        private val imageHome: ImageView = itemView.findViewById(R.id.imageClub)
        private val imageStatus: ImageView = itemView.findViewById(R.id.status)
        private val textnameClub: TextView = itemView.findViewById(R.id.nameClub)
        private val textWin: TextView = itemView.findViewById(R.id.textWin)
        private val textLose: TextView = itemView.findViewById(R.id.textLose)
        private val textDraw: TextView = itemView.findViewById(R.id.textDraw)
        private val textPoint: TextView = itemView.findViewById(R.id.textPoint)
        private val imageConstraint: ConstraintLayout = itemView.findViewById(R.id.cardMaterial)
        private val imageview: ImageView = itemView.findViewById(R.id.imageview)

        private fun ImageView.loadImageFromUrl(url: String) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun bind(StandingsResponse: StandingsResponse){
            val dataImage: String = "${ StandingsResponse.team_badge }"
            val dataStatus: String = "${ StandingsResponse.overall_league_position }"
            val dataName: String = "${ StandingsResponse.team_name }"
            val dataWin: String = "${ StandingsResponse.overall_league_W }"
            val dataDraw: String = "${ StandingsResponse.overall_league_D }"
            val dataLose: String = "${ StandingsResponse.overall_league_L }"
            val dataPoint: String = "${ StandingsResponse.overall_league_PTS }"
            val teamId: String = "${ StandingsResponse.team_id }"
            val position: Int = Integer.parseInt(dataStatus)

            if(position <= 4){
                imageStatus.setBackgroundResource(R.drawable.img_dot_blue)
            }else if(position <= 7){
                imageStatus.setBackgroundResource(R.drawable.img_dot_orng)
            }else if(position == 8){
                imageStatus.setBackgroundResource(R.drawable.img_dot_green)
            }
            val formData = DataFavouriteClub.getFormData()
            if (formData != null) {
                if(teamId == formData.id){
                    imageConstraint.setBackgroundColor(R.color.line.toInt())
                    imageview.setBackgroundResource(R.drawable.bg_favourite)
                }
            }

            imageHome.loadImageFromUrl(dataImage)
            textnameClub.text = dataName
            textWin.text = dataWin
            textLose.text = dataLose
            textDraw.text = dataDraw
            textPoint.text = dataPoint

        }
    }
}