package com.cindy.uasfix.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cindy.uasfix.databinding.FragmentProdukBinding;

import java.util.ArrayList;

public class ProdukFragment extends Fragment {

    private FragmentProdukBinding binding;
    private ProdukViewModel produkViewModel;
    private RecyclerView recyclerView;
    private ProdukAdapter produkAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        produkViewModel = new ViewModelProvider(this).get(ProdukViewModel.class);

        binding = FragmentProdukBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        produkAdapter = new ProdukAdapter(new ArrayList<>());
        recyclerView.setAdapter(produkAdapter);

        produkViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            produkAdapter.updateProducts(products);
        });

        // Ambil data produk dari API
        produkViewModel.fetchProducts();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}