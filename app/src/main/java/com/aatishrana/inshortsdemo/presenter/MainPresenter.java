package com.aatishrana.inshortsdemo.presenter;

import com.aatishrana.inshortsdemo.data.DataRepository;
import com.aatishrana.inshortsdemo.base.Presenter;
import com.aatishrana.inshortsdemo.model.CardItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MainPresenter implements Presenter<MainView>
{
    private MainView view;
    private List<CardItem> cache;
    //todo create view state cache
    private DataRepository repository;

    public MainPresenter(DataRepository repository)
    {
        cache = new ArrayList<>();
        this.repository = repository;
    }

    @Override
    public void onViewAttached(MainView view)
    {
        this.view = view;
    }

    @Override
    public void onViewDetached()
    {
        this.view = null;
    }

    @Override
    public void onDestroyed()
    {

    }

    public void getData()
    {
        if (!cache.isEmpty())
        {
            view.render(false, false, cache);
        } else
        {
            view.render(true, false, null);
            this.repository.getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<CardItem>>()
                    {
                        @Override
                        public void onSubscribe(@NonNull Disposable d)
                        {

                        }

                        @Override
                        public void onNext(@NonNull List<CardItem> cardItems)
                        {
                            if (cardItems != null && !cardItems.isEmpty())
                            {
                                view.render(false, false, cardItems);

                                //deep makeCopy data to cache;
                                makeCopy(cardItems);
                            } else
                                view.render(false, false, null);
                        }

                        @Override
                        public void onError(@NonNull Throwable e)
                        {
                            view.render(false, true, null);
                        }

                        @Override
                        public void onComplete()
                        {

                        }
                    });
        }
    }

    private void makeCopy(List<CardItem> cardItems)
    {
        cache = new ArrayList<>();
        for (CardItem cardItem : cardItems)
        {
            //todo shallow
            cache.add(cardItem);
        }
    }
}
