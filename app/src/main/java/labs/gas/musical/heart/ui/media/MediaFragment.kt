package labs.gas.musical.heart.ui.media

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import dagger.android.support.AndroidSupportInjection
import labs.gas.musical.core.extensions.gone
import labs.gas.musical.core.extensions.hideKeyboard
import labs.gas.musical.core.extensions.liveDataObserve
import labs.gas.musical.core.extensions.visible
import labs.gas.musical.core.view.DataStatus
import labs.gas.musical.heart.databinding.FragmentMediaBinding
import labs.gas.musical.heart.ui.favorites.FavoritesViewModel
import labs.gas.musical.heart.ui.media.adapter.MediaAdapter
import labs.gas.musical.heart.ui.media.model.toDomainModel
import labs.gas.musical.heart.ui.media.model.toPresentationModel
import labs.gas.musical.media.search.domain.model.MediaDomainModel
import timber.log.Timber
import javax.inject.Inject

class MediaFragment : Fragment() {
    @Inject
    lateinit var mediaViewModel: MediaViewModel

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonSearch: ImageButton
    private lateinit var tieSearch: TextInputEditText
    private lateinit var loadingView: LottieAnimationView
    private lateinit var loadingText: TextView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMediaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recyclerView = binding.idRecyclerview
        buttonSearch = binding.buttonSearch
        tieSearch = binding.tieSearch
        loadingView = binding.loading
        loadingText = binding.loadingText
        recyclerView.layoutManager = LinearLayoutManager(context)

        initObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSearch.setOnClickListener {
            mediaViewModel.search(tieSearch.text.toString())
            activity?.hideKeyboard()
        }
    }

    private fun initObservers() {
        liveDataObserve(mediaViewModel.mediaDataStatus, ::handleMediaDataStatus)
        liveDataObserve(favoritesViewModel.saveFavoriteDataStatus, ::handleSaveFavoriteDataStatus)
    }

    private fun handleMediaDataStatus(dataStatus: DataStatus?) {
        when (dataStatus) {
            is DataStatus.Loading -> loadingVisibility(true)
            is DataStatus.Error -> Timber.e(dataStatus.error)
            is DataStatus.Success -> handleMediaData(dataStatus.responseTo())
            else -> Timber.d(dataStatus.toString())
        }
    }

    private fun handleMediaData(mediaList: List<MediaDomainModel>) {
        loadingVisibility(false)
        recyclerView.adapter = MediaAdapter(mediaList.map { it.toPresentationModel() }).apply {
            onSaveFavoriteClick = {
                it.isFavorite = true
                favoritesViewModel.saveFavorite(it.toDomainModel())
            }
        }
    }

    private fun handleSaveFavoriteDataStatus(dataStatus: DataStatus?) {
        when (dataStatus) {
            is DataStatus.Loading -> Timber.d("Loading")
            is DataStatus.Error -> Timber.e(dataStatus.error)
            is DataStatus.Complete -> handleAddFavoriteSuccess()
            else -> Timber.d(dataStatus.toString())
        }
    }

    private fun handleAddFavoriteSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun loadingVisibility(visible: Boolean) {
        if (visible) {
            loadingView.visible()
        } else {
            loadingView.gone()
            loadingText.gone()
        }
    }
}
