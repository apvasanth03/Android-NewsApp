package com.vasanth.newsapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vasanth.newsapp.R
import com.vasanth.presentation.model.NewsArticleUIModel

/**
 * Fragment responsible to provide UI for NewsList screen.
 *
 * @author Vasanth
 */
class NewsDetailFragment : Fragment() {

    // Properties.
    val newsDetailArgs: NewsDetailFragmentArgs by navArgs()
    lateinit var newsArticle: NewsArticleUIModel

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeExtraData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        webView = view.findViewById(R.id.webview)

        initializeView()
        populateViews()

        return view
    }

    // Private Methods.
    private fun initializeExtraData() {
        newsArticle = newsDetailArgs.newsArticle
    }

    private fun initializeView() {
        val webSettings = webView.settings
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.displayZoomControls = false
    }

    private fun populateViews() {
        webView.loadUrl(newsArticle.url)
    }


}
