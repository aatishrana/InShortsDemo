package com.aatishrana.inshortsdemo.presenter;

import com.aatishrana.inshortsdemo.data.DataRepository;
import com.aatishrana.inshortsdemo.data.DataRepositoryImpl;
import com.aatishrana.inshortsdemo.base.PresenterFactory;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MainPresenterFactory implements PresenterFactory<MainPresenter>
{
    private DataRepository repository;

    public MainPresenterFactory(DataRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public MainPresenter create()
    {
        return new MainPresenter(this.repository);
    }
}
