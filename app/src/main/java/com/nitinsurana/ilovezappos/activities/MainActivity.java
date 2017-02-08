package com.nitinsurana.ilovezappos.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nitinsurana.ilovezappos.R;
import com.nitinsurana.ilovezappos.beans.ApiResult;
import com.nitinsurana.ilovezappos.beans.ProductBean;
import com.nitinsurana.ilovezappos.services.ZapposApi;
import com.nitinsurana.ilovezappos.services.ZapposApiFactory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MainActivity activityRef = this;

        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            String data = intent.getData() != null ? intent.getData().toString() : "";
            System.out.println("Share Intent data : " + action + "  " + data);
            if (action == Intent.ACTION_VIEW) {
                String term = data.split("/term/")[1];
                findViewById(R.id.search_form).setVisibility(View.INVISIBLE);
                showProgressDialog(this, "Loading...");
                search(term);
                return;
            }
        }

        final EditText searchInput = (EditText) findViewById(R.id.search_input);
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("info", "Enter pressed (action down)");
                    showProgressDialog(activityRef, "Searching...");
                    search(searchInput.getText().toString());
                }
                return false;
            }

        });
    }

    private void showProgressDialog(MainActivity activityRef, String title) {
        progress = new ProgressDialog(activityRef);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setTitle(title);
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.show();
    }

    public void loadProductActivity(ProductBean productBean, String term) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("bean", productBean);
        intent.putExtra("term", term);
        startActivity(intent);
    }

    public void search(final String term) {
        ZapposApi service = ZapposApiFactory.getApiInstance();
        try {
            Call<ApiResult> call = service.listRepos(term);
            call.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                    if (response.isSuccessful()) {
                        ApiResult resultBean = response.body();
                        if (resultBean.getResults().isEmpty()) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), "No results found", Toast.LENGTH_SHORT).show();
                        } else {
                            loadProductActivity(resultBean.getResults().get(9), term);
                            progress.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.d("error", "Api search call threw error : " + ex.getLocalizedMessage());
        }
    }
}
