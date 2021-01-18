package com.jesil.projectmvi.mviapplication.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jesil.projectmvi.mviapplication.model.Blog
import com.jesil.projectmvi.mviapplication.repository.MainRepository
import com.jesil.projectmvi.mviapplication.utils.DataState
import com.jesil.projectmvi.mviapplication.utils.MainStateEvent
import com.jesil.projectmvi.mviapplication.utils.exhaustive
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent -> {
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // no use
                }
            }
        }
    }


}

