package com.sarang.torang.di.restaurant_list_bottom_sheet_di

import com.sarang.torang.BuildConfig
import com.sarang.torang.GetSearchedRestaurantUseCase
import com.sarang.torang.RestaurantItemUiState
import com.sarang.torang.SelectRestaurantUseCase
import com.sarang.torang.data.RestaurantWithFiveImages
import com.sarang.torang.di.repository.FindRepositoryImpl
import com.sarang.torang.repository.FindRepository
import com.sarang.torang.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@InstallIn(SingletonComponent::class)
@Module
class SelectRestaurantUseCaseImpl {
    @Provides
    fun provideGetSearchedRestaurantUseCase(
        findRepository: FindRepositoryImpl
    ): SelectRestaurantUseCase {
        return object : SelectRestaurantUseCase {
            override suspend fun invoke(restaurantId: Int) {
                findRepository.selectRestaurant(restaurantId = restaurantId)
            }
        }
    }
}