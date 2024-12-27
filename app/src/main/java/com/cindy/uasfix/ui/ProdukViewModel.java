package com.cindy.uasfix.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> mProducts;

    public ProdukViewModel() {
        mProducts = new MutableLiveData<>();
    }

    public LiveData<List<Product>> getProducts() {
        return mProducts;
    }

    public void fetchProducts() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Product>> call = apiService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mProducts.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Tangani error jika gagal
            }
        });
    }
}