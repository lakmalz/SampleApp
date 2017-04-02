package lakmalz.git.sampleapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lakmalz.git.sampleapp.R;
import lakmalz.git.sampleapp.adapters.MultiPleListAdapter;
import lakmalz.git.sampleapp.models.Item;

/**
 * Created by Lakmal Weerasekara on 2/4/17.
 */
public class MultipleListActivity extends AppCompatActivity {

    @BindView(R.id.rv_multiple_layouts)
    RecyclerView mRvList;

    private MultiPleListAdapter mAdapter;
    private List<Item> mDataSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_list);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.cab_ib_back)
    public void onClickBack(View view) {
        onBackPressed();
    }

    private void init() {
        mDataSet = new ArrayList<>();
        mAdapter = new MultiPleListAdapter(mDataSet, this);
        makeList();
        initListView();
    }

    private void initListView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvList.setLayoutManager(linearLayoutManager);
        mRvList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRvList.setHasFixedSize(true);
        mRvList.setAdapter(mAdapter);
    }

    private void makeList() {

        for (int i = 1; i < 33; i++) {
            Item item = new Item();
            if (i % 3 != 0) {
                item.setLayout_type(1);
                item.setDescription("Abc type 1");
                item.setImage1("http://kingofwallpapers.com/ground/ground-014.jpg");
                item.setImage2("https://cdn.pixabay.com/photo/2015/12/28/15/18/ground-1111511_960_720.jpg");
            } else {
                item.setLayout_type(2);
                item.setDescription("Abc type 1");
                item.setImage1("https://storage.googleapis.com/think/images/1-in-4-app-users-discovers-through-search.jpg");
            }
            mDataSet.add(item);
        }
    }
}
