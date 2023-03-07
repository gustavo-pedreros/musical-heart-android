package labs.gas.musical.heart.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import dagger.hilt.android.AndroidEntryPoint
import labs.gas.musical.core.extensions.gone
import labs.gas.musical.core.extensions.liveDataObserve
import labs.gas.musical.core.extensions.visible
import labs.gas.musical.core.view.DataStatus
import labs.gas.musical.heart.databinding.FragmentFavoritesBinding
import labs.gas.musical.heart.ui.favorites.adapter.FavoriteAdapter
import labs.gas.musical.media.search.domain.model.MediaDomainModel
import timber.log.Timber

private const val SPAN_COUNT = 2

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val favoritesViewModel: FavoritesViewModel by viewModels<FavoritesViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: LottieAnimationView
    private lateinit var emptyText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.idRecyclerview
        emptyView = binding.empty
        emptyText = binding.emptyText
        recyclerView.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        favoritesViewModel.favoriteList()
    }

    private fun initObservers() {
        liveDataObserve(favoritesViewModel.favoriteListDataStatus, ::handleFavoritesListDataStatus)
        liveDataObserve(favoritesViewModel.deleteFavoriteDataStatus, ::handleDeleteFavoriteDataStatus)
    }

    private fun handleFavoritesListDataStatus(dataStatus: DataStatus?) {
        when (dataStatus) {
            is DataStatus.Loading -> Timber.d("Loading")
            is DataStatus.Success -> handleFavoriteSuccess(dataStatus.responseTo())
            is DataStatus.Error -> Timber.d(dataStatus.error)
            is DataStatus.Empty -> {
                emptyViewVisibility(true)
                recyclerView.adapter = null
            }
            else -> Timber.d(dataStatus.toString())
        }
    }

    private fun handleFavoriteSuccess(list: List<MediaDomainModel>) {
        emptyViewVisibility(false)
        recyclerView.adapter = FavoriteAdapter(list).apply {
            onDislikeClick = {
                favoritesViewModel.deleteFavorite(it)
            }
        }
    }

    private fun handleDeleteFavoriteDataStatus(dataStatus: DataStatus?) {
        when (dataStatus) {
            is DataStatus.Loading -> Timber.d("Loading")
            is DataStatus.Complete -> handleFavoriteDeleted()
            is DataStatus.Error -> Timber.d(dataStatus.error)
            else -> Timber.d(dataStatus.toString())
        }
    }

    private fun handleFavoriteDeleted() {
        favoritesViewModel.favoriteList()
        recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun emptyViewVisibility(visible: Boolean) {
        if (visible) {
            emptyView.visible()
            emptyText.visible()
        } else {
            emptyView.gone()
            emptyText.gone()
        }
    }
}
