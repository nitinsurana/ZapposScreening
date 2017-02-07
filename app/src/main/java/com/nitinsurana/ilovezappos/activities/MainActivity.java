package com.nitinsurana.ilovezappos.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

        final EditText searchInput = (EditText) findViewById(R.id.search_input);
        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                performSearch();
//                    return true;
//                } else if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    Log.d("info", "Done fired");
//                    return true;
//                } else
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("info", "Enter pressed (action down)");
                    progress = new ProgressDialog(activityRef);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setTitle("Searching...");
//                    progress.setMessage("Wait while loading...");
                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                    progress.setIndeterminate(true);
                    progress.show();
                    search(searchInput.getText().toString());
                }
                return false;
            }

        });
    }

    public void loadProductActivity(ProductBean productBean) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("bean", productBean);
        startActivity(intent);
    }

    public void search(String term) {
        ZapposApi service = ZapposApiFactory.getApiInstance();
        final MainActivity activityRef = this;
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
                            loadProductActivity(resultBean.getResults().get(9));
                            progress.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {

                }
            });
//            System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception ex) {
            Log.d("error", "Api search call threw error : " + ex.getLocalizedMessage());
        }
    }
}
