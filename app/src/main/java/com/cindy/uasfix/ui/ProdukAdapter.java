package com.cindy.uasfix.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.cindy.uasfix.R;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    private List<Product> productList;

    public ProdukAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProdukViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProdukViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.price.setText("Price: $" + product.getPrice());

        // Menggunakan Glide untuk memuat gambar
        Glide.with(holder.image.getContext()).load(product.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateProducts(List<Product> newProducts) {
        productList.clear();
        productList.addAll(newProducts);
        notifyDataSetChanged();
    }

    public static class ProdukViewHolder extends RecyclerView.ViewHolder {

        public TextView title, price;
        public ImageView image;

        public ProdukViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.product_title);
            price = view.findViewById(R.id.product_price);
            image = view.findViewById(R.id.product_image);
        }
    }
}