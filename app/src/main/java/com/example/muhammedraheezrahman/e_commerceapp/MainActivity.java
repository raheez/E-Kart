package com.example.muhammedraheezrahman.e_commerceapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.muhammedraheezrahman.e_commerceapp.Adapter.RVAdapter;
import com.example.muhammedraheezrahman.e_commerceapp.Model.ProductModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Collections;
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

public class MainActivity extends RootActivity {

    RecyclerView recyclerView;
    List<String> list;
    GridLayoutManager gm;
    RVAdapter adapter;
    int visibleThreashold=1,totalCount,lastItem;
    boolean loading = false;
    PublishProcessor<Integer> paginator = PublishProcessor.create();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int pageNumber =0;
    ImageView im;
    List<ProductModel> productList = new ArrayList<>();
    ShimmerFrameLayout shimmerFrameLayout;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView  = (RecyclerView) findViewById(R.id.rv);
        list = new ArrayList<String>();
        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_layout);
        adapter = new RVAdapter(getApplicationContext());
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
                .concatMap(new Function<Integer, Publisher<List<ProductModel>>>() {
            @Override
            public Publisher<List<ProductModel>> apply(@NonNull Integer integer) throws Exception {
                loading =true;
                return getData(pageNumber);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ProductModel>>() {
            @Override
            public void accept(List<ProductModel> productList) throws Exception {
              shimmerFrameLayout.stopShimmer();
              shimmerFrameLayout.setVisibility(View.GONE);
              adapter.addToProductList(productList);
              loading = false;

            }
        });



        compositeDisposable.add(disposable);
        paginator.onNext(pageNumber);


     }

    public Flowable<List<ProductModel>> getData(int pageNumber){
       return Flowable.just(true)
               .delay(2,TimeUnit.SECONDS)
               .map(new Function<Boolean, List<ProductModel>>() {
           @Override
           public List<ProductModel> apply(Boolean aBoolean) throws Exception {
               List<ProductModel> list = new ArrayList<ProductModel>();

//               for (int i =1;i<=10;i++)
//               list.add("items "+ (pageNumber*10+i));
               return getProductList();
           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();

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

    @Override
    protected void onPause() {
        super.onPause();
        shimmerFrameLayout.stopShimmer();
     }

    public List<ProductModel> getProductList(){

        productList.add( new  ProductModel("Black Head Phones",300,200,R.drawable.headphones_black));
        productList.add(  new ProductModel("Orange Head Phones",200,100,R.drawable.headphones_orange));
        productList.add(  new ProductModel("Black Air Conditioner",600,500,R.drawable.ac_black));
        productList.add(  new ProductModel("Red Air Conditioner",450,200,R.drawable.ac_red));
        productList.add(  new ProductModel("Black Camera",350,200,R.drawable.camera_black));
        productList.add(  new ProductModel("Red Camera",350,200,R.drawable.camera_black));
        productList.add(  new ProductModel("Lenovo Laptop",430,410,R.drawable.laptop_black));
        productList.add(  new ProductModel("Asus Laptop",340,310,R.drawable.laptop_green));
        productList.add(  new ProductModel("Xbox1",650,500,R.drawable.xbox_1));
        productList.add(  new ProductModel("Xbox2",850,700,R.drawable.xbox_2));

        Collections.shuffle(productList);
        return productList;


    }


}
