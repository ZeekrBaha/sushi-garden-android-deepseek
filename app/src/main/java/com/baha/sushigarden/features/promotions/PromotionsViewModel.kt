package com.baha.sushigarden.features.promotions

import androidx.lifecycle.ViewModel
import com.baha.sushigarden.data.models.Banner
import com.baha.sushigarden.data.services.catalog.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class PromotionsUiState(
    val banners: List<Banner> = emptyList(),
)

@HiltViewModel
class PromotionsViewModel
    @Inject
    constructor(
        private val menuRepository: MenuRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(PromotionsUiState())
        val state: StateFlow<PromotionsUiState> = _state.asStateFlow()

        init {
            _state.value = PromotionsUiState(banners = menuRepository.getBanners())
        }
    }
