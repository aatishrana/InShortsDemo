package com.aatishrana.inshortsdemo;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.aatishrana.inshortsdemo.base.BasePresenterActivity;
import com.aatishrana.inshortsdemo.base.PresenterFactory;
import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.network.ApiClient;
import com.aatishrana.inshortsdemo.network.ApiInterface;
import com.aatishrana.inshortsdemo.presenter.MainPresenter;
import com.aatishrana.inshortsdemo.presenter.MainPresenterFactory;
import com.aatishrana.inshortsdemo.presenter.MainView;
import com.aatishrana.inshortsdemo.utils.Help;

import java.util.List;

public class MainActivity extends BasePresenterActivity<MainPresenter, MainView> implements MainView
{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayout errorView;
    private Button btnRetry;

    private MyAdapter adapter;

    private MainPresenterFactory presenterFactory;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_main);
        //boilerplate
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        DataRepository dataRepository = new DataRepository(apiInterface);
        presenterFactory = new MainPresenterFactory(dataRepository);
        Log.e("aatish onCreate", "" + this.presenterFactory);

        //init views
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progressBar);
        errorView = (LinearLayout) findViewById(R.id.activity_main_ll_error_view);
        btnRetry = (Button) findViewById(R.id.activity_main_btn_retry);

        //recycler view
        SnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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
        Log.e("aatish getPresenterFac", "" + this.presenterFactory);
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
//        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
    }
}
