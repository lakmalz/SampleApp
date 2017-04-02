package lakmalz.git.sampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lakmalz.git.sampleapp.R;

/**
 * Created by Lakmal Weerasekara on 2/4/17.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_template)
    public void onClickTemplateList(View view) {
        startActivity(new Intent(this, TemplateListActivity.class));
    }

    @OnClick(R.id.btn_multiple_layout)
    public void onClickMultipleLayouts(View view) {
        startActivity(new Intent(this, MultipleListActivity.class));
    }
}
