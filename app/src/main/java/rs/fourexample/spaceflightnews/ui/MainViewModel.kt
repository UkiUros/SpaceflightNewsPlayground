package rs.fourexample.spaceflightnews.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import rs.fourexample.spaceflightnews.model.Article
import rs.fourexample.spaceflightnews.repository.ArticleRepository
import rs.fourexample.spaceflightnews.utils.DataState

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val articleRepository: ArticleRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
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
