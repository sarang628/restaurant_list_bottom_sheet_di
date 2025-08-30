package com.sarang.torang.di.restaurant_list_bottom_sheet_di

import com.sarang.torang.BuildConfig
import com.sarang.torang.GetSearchedRestaurantUseCase
import com.sarang.torang.RestaurantItemUiState
import com.sarang.torang.data.RestaurantWithFiveImages
import com.sarang.torang.repository.FindRepository
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
class GetSearchedRestaurantUseCaseImpl {
    @Provides
    fun provideGetSearchedRestaurantUseCase(
        findRepository: FindRepository
    ): GetSearchedRestaurantUseCase {
        return object : GetSearchedRestaurantUseCase {
            override fun invoke(coroutineScope: CoroutineScope): StateFlow<List<RestaurantItemUiState>> {
                return findRepository.restaurants.map { restaurantList ->
                    restaurantList.map { restaurant ->
                        restaurant.restaurantItemUiState
                    }
                }.stateIn(
                    scope = coroutineScope,
                    started = SharingStarted.Companion.Eagerly,
                    initialValue = emptyList()
                )
            }
        }
    }

    val RestaurantWithFiveImages.restaurantItemUiState: RestaurantItemUiState
        get() = RestaurantItemUiState(
            restaurantName = this.restaurant.restaurantName,
            rating = this.restaurant.rating,
            ratingCount = 100,
            price = this.restaurant.prices,
            foodType = this.restaurant.restaurantType,
            open = "open",
            closes = "9PM",
            imageList = if(this.images.isEmpty())listOf((BuildConfig.RESTAURANT_IMAGE_SERVER_URL + this.restaurant.imgUrl1))
            else this.images.map{ BuildConfig.REVIEW_IMAGE_SERVER_URL + it }
        )
}