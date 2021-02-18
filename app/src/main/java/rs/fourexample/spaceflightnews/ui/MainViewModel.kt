package rs.fourexample.spaceflightnews.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import rs.fourexample.spaceflightnews.model.Article
import rs.fourexample.spaceflightnews.repository.ArticleRepository
import rs.fourexample.spaceflightnews.utils.DataState
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val articleRepository: ArticleRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mutableDataState: MutableLiveData<DataState<List<Article>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Article>>>
        get() = mutableDataState

    fun loadArticles() {
        viewModelScope.launch {

            articleRepository.getArticles()
                .onEach { dataState ->
                    mutableDataState.value = dataState
                }
                .launchIn(viewModelScope)

        }
    }

}
