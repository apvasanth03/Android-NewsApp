package com.vasanth.newsapp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vasanth.newsapp.R
import com.vasanth.presentation.model.NewsArticleUIModel

/**
 * Activity responsible to provide UI for NewsList screen.
 *
 * @author Vasanth
 */
class NewsDetailActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_KEY_NEWS_ARTICLE = "EXTRA_KEY_NEWS_ARTICLE"

        fun getIntent(context: Context, newsArticle: NewsArticleUIModel): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_KEY_NEWS_ARTICLE, newsArticle)
            return intent
        }
    }

    // Properties.
    lateinit var newsArticle: NewsArticleUIModel

    lateinit var toolbar: Toolbar
    lateinit var webView: WebView

    // Activity Methods.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        toolbar = findViewById(R.id.toolbar)
        webView = findViewById(R.id.webview)

        initializeExtraData()
        initializeView()
        populateViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    // Private Methods.
    private fun initializeExtraData() {
        newsArticle = intent.extras?.getParcelable(EXTRA_KEY_NEWS_ARTICLE)
            ?: throw IllegalArgumentException("NewsArticleUIModel is not available in intent extras.")
    }

    private fun initializeView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = newsArticle.source
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
