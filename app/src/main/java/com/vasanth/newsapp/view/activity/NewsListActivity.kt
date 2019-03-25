package com.vasanth.newsapp.view.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasanth.newsapp.R
import com.vasanth.newsapp.view.adapter.NewsListAdapter
import com.vasanth.newsapp.view.listener.NewsArticleListener
import com.vasanth.newsapp.view.view.ErrorView
import com.vasanth.presentation.model.NewsArticleUIModel
import com.vasanth.presentation.viewmodel.NewsListViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Activity responsible to provide UI for NewsList screen.
 *
 * @author Vasanth
 */
class NewsListActivity : AppCompatActivity() {

    // Properties.
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: NewsListAdapter

    lateinit var toolbar: Toolbar
    lateinit var rvNewsArticles: RecyclerView
    lateinit var vgEmptyViewContainer: ViewGroup
    lateinit var pbProgress: ProgressBar
    lateinit var errorView: ErrorView

    lateinit var viewModel: NewsListViewModel

    // Activity Methods.
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)

        toolbar = findViewById(R.id.toolbar)
        rvNewsArticles = findViewById(R.id.news_articles)
        vgEmptyViewContainer = findViewById(R.id.error_view_container)
        pbProgress = findViewById(R.id.progress)

        initializeViews()
        addListeners()
        setUpViewModelBindings()
    }

    // Private Methods.
    private fun initializeViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.news_article_list_title)

        val layoutManager = LinearLayoutManager(this)
        rvNewsArticles.layoutManager = layoutManager
        rvNewsArticles.adapter = adapter

        errorView = ErrorView(this, R.drawable.ic_error_outline, null, null)
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
        viewModel.goToNewsDetailScreenObservable.observe(this, Observer { newsArticle ->
            val intent = NewsDetailActivity.getIntent(this, newsArticle)
            startActivity(intent)
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
