package labs.gas.musical.heart.ui.media.adapter

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import labs.gas.musical.heart.databinding.ItemMediaBinding

class MediaViewHolder(viewBinding: ItemMediaBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    val photo: ImageView = viewBinding.idPhoto
    val name: TextView = viewBinding.idName
    val album: TextView = viewBinding.idAlbum
    val time: TextView = viewBinding.idSongTime
    val buttonLike: ImageButton = viewBinding.buttonLike
}
