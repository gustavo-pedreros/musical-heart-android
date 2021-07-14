package labs.gas.musical.heart.ui.media.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import labs.gas.musical.heart.R
import labs.gas.musical.heart.databinding.ItemMediaBinding
import labs.gas.musical.heart.ui.media.model.Media
import labs.gas.musical.heart.ui.media.model.durationFormat

class MediaAdapter(private val mediaList: List<Media>) : RecyclerView.Adapter<MediaViewHolder>() {

    var onSaveFavoriteClick: (Media) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val viewBinding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mediaList[position].photo).into(holder.photo)
        holder.album.text = mediaList[position].album
        holder.name.text = mediaList[position].name
        holder.time.text = mediaList[position].durationFormat()

        holder.buttonLike.setOnClickListener {
            mediaList[position].isFavorite = true
            onSaveFavoriteClick(mediaList[position])
        }
        if (mediaList[position].isFavorite) {
            holder.buttonLike.setImageResource(R.drawable.ic_heart_purple_48dp)
        } else {
            holder.buttonLike.setImageResource(R.drawable.ic_heart_gray_48dp)
        }
    }

    override fun getItemCount(): Int = mediaList.size
}
