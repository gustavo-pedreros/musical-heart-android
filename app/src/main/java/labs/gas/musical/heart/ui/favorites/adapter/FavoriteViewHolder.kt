package labs.gas.musical.heart.ui.favorites.adapter

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import labs.gas.musical.heart.databinding.ItemFavoritesBinding

class FavoriteViewHolder(viewBinding: ItemFavoritesBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    val photo: ImageView = viewBinding.idPhoto
    val songOrArtist: TextView = viewBinding.idSongOrArtist
    val album: TextView = viewBinding.idAlbum
    val time: TextView = viewBinding.idSongTime
    val buttonDislike: ImageButton = viewBinding.buttonDislike
}
