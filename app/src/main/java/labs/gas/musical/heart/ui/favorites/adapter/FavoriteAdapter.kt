package labs.gas.musical.heart.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import labs.gas.musical.heart.databinding.ItemFavoritesBinding
import labs.gas.musical.media.search.domain.model.MediaDomainModel
import labs.gas.musical.media.search.domain.model.durationFormat

class FavoriteAdapter(private val mediaList: List<MediaDomainModel>) : RecyclerView.Adapter<FavoriteViewHolder>() {

    var onDislikeClick: (MediaDomainModel) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val viewBing = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(viewBing)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.buttonDislike.setOnClickListener { onDislikeClick(mediaList[position]) }
        Glide.with(holder.itemView).load(mediaList[position].photo).into(holder.photo)
        holder.songOrArtist.text = mediaList[position].name ?: mediaList[position].artist
        holder.album.text = mediaList[position].album
        holder.time.text = mediaList[position].durationFormat()
    }

    override fun getItemCount(): Int = mediaList.size
}
