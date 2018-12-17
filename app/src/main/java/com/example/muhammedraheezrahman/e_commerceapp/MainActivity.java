package com.example.muhammedraheezrahman.e_commerceapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.muhammedraheezrahman.e_commerceapp.Adapter.RVAdapter;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> list;
    GridLayoutManager gm;
    RVAdapter adapter;
    int visibleThreashold=1,totalCount,lastItem;
    boolean loading = false;
    PublishProcessor<Integer> paginator = PublishProcessor.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int pageNumber =0;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView  = (RecyclerView) findViewById(R.id.rv);
        list = new ArrayList<String>();
        adapter = new RVAdapter();
        gm = new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gm);

        recyclerView.setAdapter(adapter);
        dowork();

    }

    private void dowork() {

//        Disposable disposable = paginator
//                .onBackpressureDrop()
//                .concatMap(new Function<Integer, Publisher<List<String>>>() {
//                    @Override
//                    public Publisher<List<String>> apply(@NonNull Integer page) {
//                        loading = true;
//                        return getData(page);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<String>>() {
//                    @Override
//                    public void accept(@NonNull List<String> items) {
//                        adapter.addToList(items);
//                        adapter.notifyDataSetChanged();
//                        loading = false;
////                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                });
//
//        compositeDisposable.add(disposable);
//
//        paginator.onNext(pageNumber);

        Disposable disposable =  paginator
                .onBackpressureDrop()
                .concatMap(new Function<Integer, Publisher<List<String>>>() {
            @Override
            public Publisher<List<String>> apply(@NonNull Integer integer) throws Exception {
                loading =true;
                return getData(pageNumber);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
              adapter.addToList(strings);
              loading = false;

            }
        });

        compositeDisposable.add(disposable);
        paginator.onNext(pageNumber);


     }

    public Flowable<List<String>> getData(int pageNumber){
       return Flowable.just(true)
               .delay(2,TimeUnit.SECONDS)
               .map(new Function<Boolean, List<String>>() {
           @Override
           public List<String> apply(Boolean aBoolean) throws Exception {
               List<String> list = new ArrayList<String>();

               for (int i =1;i<=10;i++)
               list.add("items "+ (pageNumber*10+i));
               return list;
           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    totalCount = gm.getItemCount();
                    lastItem = gm.findLastVisibleItemPosition();

                    if(!loading  && totalCount <= (lastItem + visibleThreashold) ){

                        pageNumber++;
                        paginator.onNext(pageNumber);

                    }

                }
            });
        }
    }


}
