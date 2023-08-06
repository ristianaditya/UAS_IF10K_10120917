import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.models.TopScoredResponse

class TopScoredAdapter(private val mList: List<TopScoredResponse>) : RecyclerView.Adapter<TopScoredAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_goal, parent, false)

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
        private val urutan: TextView = itemView.findViewById(R.id.urutan)
        private val namePlayer: TextView = itemView.findViewById(R.id.namePlayer)
        private val goal: TextView = itemView.findViewById(R.id.goal)

        fun bind(TopScoredResponse: TopScoredResponse){
            val dataUrutan: String = "${ TopScoredResponse.player_place }"
            val dataNama: String = "${ TopScoredResponse.player_name }"
            val dataGoal: String = "${ TopScoredResponse.goals }"

            urutan.text = dataUrutan
            namePlayer.text = dataNama
            goal.text = dataGoal

        }
    }
}