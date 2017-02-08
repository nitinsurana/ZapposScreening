package com.nitinsurana.ilovezappos.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
//import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.nitinsurana.ilovezappos.R;
import com.nitinsurana.ilovezappos.beans.ProductBean;
import com.nitinsurana.ilovezappos.databinding.ContentProductBinding;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {//implements ShareActionProvider.OnShareTargetSelectedListener {
    //    private ShareActionProvider mShareActionProvider;
    private String searchTerm;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("Options item selected click");
        switch (item.getItemId()) {
            case R.id.mShare:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
//                String shareBody = "zappos://product?term=" + searchTerm;
                String shareBody = "http://zappos.com/term/" + searchTerm;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Zappos");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ProductBean bean = (ProductBean) getIntent().getSerializableExtra("bean");
        searchTerm = getIntent().getSerializableExtra("term").toString();
        ContentProductBinding binding = DataBindingUtil.setContentView(this, R.layout.content_product);
        binding.setProduct(bean);

        setTitle("ILoveZappos");

        ImageView img = (ImageView) findViewById(R.id.product_img);
        Picasso.with(img.getContext())
                .load(bean.getThumbnailImageUrl())
                .into(img);

        TextView txtOriginalPrice = (TextView) findViewById(R.id.f_original_price);
        TextView txtPercentOff = (TextView) findViewById(R.id.percent_off);
        if (bean.getF_price() == bean.getF_originalPrice()) {
            txtOriginalPrice.setVisibility(View.INVISIBLE);
            txtPercentOff.setVisibility(View.INVISIBLE);
        } else {
            txtOriginalPrice.setPaintFlags(txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            txtPercentOff.setText(txtPercentOff.getText() + " Off");
        }
    }

    public void addToCart(View v) {
        Toast.makeText(getApplicationContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        final Animation an = new RotateAnimation(0, 90, v.getWidth() / 2, v.getHeight() / 2);
        an.setDuration(200);
        an.setFillAfter(true);
        v.clearAnimation();
        v.startAnimation(an);
    }
}
