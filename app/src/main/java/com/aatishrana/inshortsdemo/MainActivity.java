package com.aatishrana.inshortsdemo;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aatishrana.inshortsdemo.adapter.MyAdapter;
import com.aatishrana.inshortsdemo.adapter.MyLinearLayout;
import com.aatishrana.inshortsdemo.adapter.StartSnapHelper;
import com.aatishrana.inshortsdemo.base.BasePresenterActivity;
import com.aatishrana.inshortsdemo.base.PresenterFactory;
import com.aatishrana.inshortsdemo.data.DataRepository;
import com.aatishrana.inshortsdemo.data.DataRepositoryImpl;
import com.aatishrana.inshortsdemo.data.DataRepositoryMock;
import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.network.ApiClient;
import com.aatishrana.inshortsdemo.network.ApiInterface;
import com.aatishrana.inshortsdemo.presenter.MainPresenter;
import com.aatishrana.inshortsdemo.presenter.MainPresenterFactory;
import com.aatishrana.inshortsdemo.presenter.MainView;
import com.aatishrana.inshortsdemo.utils.Help;

import java.util.List;

public class MainActivity extends BasePresenterActivity<MainPresenter, MainView> implements MainView, MyAdapter.MyClickListener
{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout errorView;
    private Button btnRetry;

    private MyAdapter adapter;
    private BottomSheetBehavior mBottomSheetBehavior1;
    private WebView webView;
    private TextView tvClose, tvUrl;

    private MainPresenterFactory presenterFactory;
    private MainPresenter presenter;
    private MyLinearLayout layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        //boilerplate
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        DataRepository dataRepositoryImpl = new DataRepositoryImpl(apiInterface);
        presenterFactory = new MainPresenterFactory(dataRepositoryImpl);

        //init views
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progressBar);
        errorView = (LinearLayout) findViewById(R.id.activity_main_ll_error_view);
        btnRetry = (Button) findViewById(R.id.activity_main_btn_retry);

        tvClose = (TextView) findViewById(R.id.sheet_more_close);
        tvUrl = (TextView) findViewById(R.id.sheet_more_url);
        View bottomSheet = findViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);
        webView = (WebView) findViewById(R.id.sheet_more_webview);
//        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
//        webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setAppCacheEnabled(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        tvClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        //recycler view
        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        layoutManager = new MyLinearLayout(this, LinearLayoutManager.VERTICAL, false);
        adapter = new MyAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior1.setPeekHeight(0);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (isPresenterAvailable())
            presenter.getData();
    }

    private boolean isPresenterAvailable()
    {
        return this.presenter != null;
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory()
    {
        return this.presenterFactory;
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull MainPresenter presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void render(boolean isLoading, boolean hasError, List<CardItem> data)
    {
        hideAllViews();
        if (isLoading && !hasError && data == null)
        {
            //loading
            Help.L("loading");
            progressBar.setVisibility(View.VISIBLE);
            return;
        }

        if (!isLoading && hasError && data == null)
        {
            //show error
            Help.L("error");
            errorView.setVisibility(View.VISIBLE);
            btnRetry.setVisibility(View.VISIBLE);
            return;
        }

        if (!isLoading && !hasError && data != null)
        {
            //have valid data
            Help.L("show data");
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setData(data);
            adapter.notifyDataSetChanged();
            return;
        }

        if (!isLoading && !hasError && data == null)
        {
            // empty list returned from server, show nothing
            Help.L("show no data");
            return;
        }

        Help.L("some thing is wrong");
    }

    private void hideAllViews()
    {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
    }


    @Override
    public void onClick(String url, int position, boolean fromMultiImages)
    {
        if (!fromMultiImages && position != layoutManager.findFirstVisibleItemPosition())
            return;

        if (mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED
                && url != null
                && url.length() > 0)
        {
            Help.L("url=" + url);
            mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            tvUrl.setText(url);
        }
    }
}
