package com.example.appforportfolio.ui.feed

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.appforportfolio.databinding.FragmentFeedBinding
import com.example.appforportfolio.ui.BaseFragment
import javax.inject.Inject

class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    @Inject
    lateinit var feedViewModelFactory: dagger.Lazy<FeedViewModel.Factory>

    private val feedViewModel: FeedViewModel by viewModels {
        feedViewModelFactory.get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}