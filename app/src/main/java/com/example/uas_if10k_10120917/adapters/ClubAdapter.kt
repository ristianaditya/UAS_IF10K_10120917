import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.uas_if10k_10120917.DataFavouriteClub
import com.example.uas_if10k_10120917.MainActivity
import com.example.uas_if10k_10120917.R
import com.example.uas_if10k_10120917.models.ClubFavouriteModel
import com.example.uas_if10k_10120917.models.ClubResponse
import com.example.uas_if10k_10120917.models.TopScoredResponse

class ClubAdapter(private val mList: List<ClubResponse>) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_club, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]
        holder.bind(itemsViewModel, holder.itemView.context)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val nama: TextView = itemView.findViewById(R.id.textClub)
        private val club: ImageView = itemView.findViewById(R.id.imageClub)

        private fun ImageView.loadImageFromUrl(url: String) {
            Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }

        fun bind(ClubResponse: ClubResponse, context: Context){
            val dataNama: String = "${ ClubResponse.team_name }"
            val dataClub: String = "${ ClubResponse.team_badge }"
            val dataId: String = "${ ClubResponse.team_key }"

            nama.text = dataNama
            club.loadImageFromUrl(dataClub)

            itemView.setOnClickListener {
                val formData = ClubFavouriteModel(dataId, dataNama, dataClub)
                DataFavouriteClub.saveFormData(formData)

                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }

        }
    }
}