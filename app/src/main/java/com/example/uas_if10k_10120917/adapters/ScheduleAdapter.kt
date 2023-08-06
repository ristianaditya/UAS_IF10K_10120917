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
import com.example.uas_if10k_10120917.models.FixturesResponse
import com.example.uas_if10k_10120917.models.ScheduleResponse

class ScheduleAdapter(private val mList: List<ScheduleResponse>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_schedule, parent, false)

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
        private val textDate: TextView = itemView.findViewById(R.id.textDate)
        private val textHome: TextView = itemView.findViewById(R.id.textHome)
        private val textAway: TextView = itemView.findViewById(R.id.textAway)
        private val textTime: TextView = itemView.findViewById(R.id.textTime)
        private val ImageView: ImageView = itemView.findViewById(R.id.imageview)

        private fun ImageView.loadImageFromUrl(url: String) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun bind(ScheduleResponse: ScheduleResponse){
            val dataImageClubHome: String = "${ ScheduleResponse.team_home_badge }"
            val dataImageClubAway: String = "${ ScheduleResponse.team_away_badge }"
            val dataDate: String = "${ ScheduleResponse.match_date }"
            val dataHome: String = "${ ScheduleResponse.match_hometeam_name }"
            val dataAway: String = "${ ScheduleResponse.match_awayteam_name }"
            val dataTime: String = "${ ScheduleResponse.match_time }"
            val matchHometeamId: String = "${ ScheduleResponse.match_hometeam_id }"
            val matchAwayteamId: String = "${ ScheduleResponse.match_awayteam_id }"

            val formData = DataFavouriteClub.getFormData()
            if (formData != null) {
                if(matchHometeamId == formData.id){
                    ImageView.setBackgroundResource(R.drawable.bg_schedule_selected)
                }else if(matchAwayteamId == formData.id){
                    ImageView.setBackgroundResource(R.drawable.bg_schedule_selected)
                }else{
                    ImageView.setBackgroundResource(R.drawable.bg_schedule)
                }
            }

            imageHome.loadImageFromUrl(dataImageClubHome)
            imageAway.loadImageFromUrl(dataImageClubAway)
            textDate.text = dataDate
            textHome.text = dataHome
            textAway.text = dataAway
            textTime.text = dataTime

        }
    }
}