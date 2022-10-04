package com.example.kodetrainee.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.Character
import com.example.domain.usecase.GetCharactersRemoteUseCase
import com.example.kodetrainee.model.LceState

class CharacterPagingSource(
    private val getCharactersRemoteUseCase: GetCharactersRemoteUseCase,
    private val species: String,
    private val searchBy: String
) : PagingSource<Int, Character>() {

    private var _pageCount: Int? = null
    private val pageCount get() = requireNotNull(_pageCount)

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageIndex = params.key ?: 1


        return when (val characters = getCharacters(pageIndex, species, searchBy)) {
            is LceState.Content -> {
                LoadResult.Page(
                    data = characters.value,
                    prevKey = if (pageIndex > 1) pageIndex - 1 else null,
                    nextKey = if (pageIndex < pageCount) pageIndex + 1 else null
                )
            }
            is LceState.Error -> {
                LoadResult.Error(characters.throwable)
            }
        }
    }

    private suspend fun getCharacters(
        page: Int,
        species: String,
        searchBy: String
    ): LceState<List<Character>> {
        val characters = getCharactersRemoteUseCase(page, species, searchBy)
        characters
            .onSuccess {
                if (_pageCount == null) _pageCount = it.info.pages
                return LceState.Content(it.results)
            }
            .onFailure {
                return LceState.Error(it)
            }
        return LceState.Content(emptyList())
    }
}
