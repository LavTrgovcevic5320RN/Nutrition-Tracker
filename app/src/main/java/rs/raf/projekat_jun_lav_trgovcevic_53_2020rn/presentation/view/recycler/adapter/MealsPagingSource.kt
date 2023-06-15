package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.recycler.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.MealsState

class MealPagingSource(private val mealsState: MutableLiveData<MealsState>)
    : PagingSource<Int, Meal>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meal> {
        val pageNumber = params.key ?: 1
        return try {
//            val response = repository.getMeals(pageNumber)
            val meals = null// Assuming the response has a list of meals
            val nextPageNumber = 0
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meal>): Int? {
        // We don't need a refresh key as we're not supporting refresh in this example
        return null
    }
}
