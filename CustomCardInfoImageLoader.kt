package com.sarang.torang.di.restaurant_list_bottom_sheet_di

import com.sarang.torang.RestaurantItemImageLoader
import com.sarang.torang.di.image.provideTorangAsyncImage

val CustomRestaurantItemImageLoader : RestaurantItemImageLoader get() = provideTorangAsyncImage()