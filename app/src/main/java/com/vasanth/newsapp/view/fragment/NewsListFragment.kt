package com.vasanth.newsapp.view.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasanth.newsapp.R
import com.vasanth.newsapp.view.adapter.NewsListAdapter
import com.vasanth.newsapp.view.listener.NewsArticleListener
import com.vasanth.newsapp.view.view.ErrorView
import com.vasanth.presentation.model.NewsArticleUIModel
import com.vasanth.presentation.viewmodel.NewsListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Fragment responsible to provide UI for NewsList screen.
 *
 * @author Vasanth
 */
class NewsListFragment : Fragment() {

    // Properties.
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: NewsListAdapter

    lateinit var rvNewsArticles: RecyclerView
    lateinit var vgEmptyViewContainer: ViewGroup
    lateinit var pbProgress: ProgressBar
    lateinit var errorView: ErrorView

    lateinit var viewModel: NewsListViewModel

    // Fragment Methods.
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        rvNewsArticles = view.findViewById(R.id.news_articles)
        vgEmptyViewContainer = view.findViewById(R.id.error_view_container)
        pbProgress = view.findViewById(R.id.progress)

        initializeViews()
        addListeners()
        setUpViewModelBindings()

        return view
    }

    // Private Methods.
    private fun initializeViews() {
        val layoutManager = LinearLayoutManager(context)
        rvNewsArticles.layoutManager = layoutManager
        rvNewsArticles.adapter = adapter

        errorView = ErrorView(context!!, R.drawable.ic_error_outline, null, null)
        vgEmptyViewContainer.addView(errorView)
        vgEmptyViewContainer.visibility = View.GONE

        pbProgress.visibility = View.GONE
    }

    private fun addListeners() {
        adapter.newsArticleListener = newsArticleListener
    }

    private val newsArticleListener = object : NewsArticleListener {
        override fun onNewsArticleClicked(newsArticle: NewsArticleUIModel) {
            viewModel.onNewsArticleItemClicked(newsArticle)
        }
    }

    private fun setUpViewModelBindings() {
        bindViewState()
        bindNewsArticles()
        bindGoToNewsArticleScreen()
    }

    private fun bindViewState() {
        viewModel.viewStateObservable.observe(this, Observer { state ->
            when (state) {
                NewsListViewModel.NewsListViewState.LOADING -> updateViewForLoadingState()
                NewsListViewModel.NewsListViewState.DATA -> updateViewForDataState()
                NewsListViewModel.NewsListViewState.NO_INTERNET -> updateViewForNoInternetState()
                NewsListViewModel.NewsListViewState.ERROR -> updateViewForErrorState()
            }
        })
    }

    private fun bindNewsArticles() {
        viewModel.newsArticlesObservable.observe(this, Observer { newsArticles ->
            adapter.newsArticles = newsArticles
            adapter.notifyDataSetChanged()
        })
    }

    private fun bindGoToNewsArticleScreen() {
        viewModel.goToNewsDetailScreenObservable.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { newsArticle ->
                val startDetailAction = NewsListFragmentDirections.startDetailFragment(newsArticle.source, newsArticle)
                val navController = findNavController()
                navController.navigate(startDetailAction)
            }
        })
    }

    private fun updateViewForLoadingState() {
        rvNewsArticles.visibility = View.GONE
        vgEmptyViewContainer.visibility = View.GONE
        pbProgress.visibility = View.VISIBLE
    }

    private fun updateViewForDataState() {
        rvNewsArticles.visibility = View.VISIBLE
        vgEmptyViewContainer.visibility = View.GONE
        pbProgress.visibility = View.GONE
    }

    private fun updateViewForNoInternetState() {
        rvNewsArticles.visibility = View.GONE
        errorView.updateView(
            R.drawable.ic_error_outline,
            getString(R.string.no_network_error_title),
            getString(R.string.no_network_error_message)
        )
        vgEmptyViewContainer.visibility = View.VISIBLE
        pbProgress.visibility = View.GONE
    }

    private fun updateViewForErrorState() {
        rvNewsArticles.visibility = View.GONE
        errorView.updateView(
            R.drawable.ic_error_outline,
            getString(R.string.unknown_error_title),
            getString(R.string.unknown_error_message)
        )
        vgEmptyViewContainer.visibility = View.VISIBLE
        pbProgress.visibility = View.GONE
    }
}
