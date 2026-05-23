package com.baha.sushigarden.features.catalog

import androidx.lifecycle.ViewModel
import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.models.Product
import com.baha.sushigarden.data.services.catalog.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

data class CatalogUiState(
    val categories: List<Category> = Category.entries,
    val selectedCategory: Category = Category.rolls,
    val products: List<Product> = emptyList(),
)

@HiltViewModel
class CatalogViewModel
    @Inject
    constructor(
        private val menuRepository: MenuRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CatalogUiState())
        val state: StateFlow<CatalogUiState> = _state.asStateFlow()

        init {
            Timber.i("CatalogViewModel initialized")
            selectCategory(Category.rolls)
        }

        fun selectCategory(category: Category) {
            Timber.d("Category selected: ${category.displayName}")
            _state.value =
                _state.value.copy(
                    selectedCategory = category,
                    products = menuRepository.getProductsByCategory(category),
                )
        }
    }
