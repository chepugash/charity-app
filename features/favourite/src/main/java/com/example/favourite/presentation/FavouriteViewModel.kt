package com.example.favourite.presentation

import com.example.common.base.BaseViewModel
import com.example.favourite.FavouriteRouter
import com.example.favourite.domain.usecase.CreateUserDocumentUseCase
import com.example.favourite.domain.usecase.GetFavouriteUseCase
import com.example.favourite.domain.usecase.GetUserUseCase

class FavouriteViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val createUserDocumentUseCase: CreateUserDocumentUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val router: FavouriteRouter
) : BaseViewModel() {
}