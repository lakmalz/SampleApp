package lakmalz.git.sampleapp.activities;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridBottomOffsetItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridTopOffsetItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lakmalz.git.sampleapp.R;
import lakmalz.git.sampleapp.adapters.TemplateListAdapter;
import lakmalz.git.sampleapp.models.Template;
import lakmalz.git.sampleapp.services.requestmodels.GetTemplateRequest;
import lakmalz.git.sampleapp.services.responsemodels.GetTemplateResponse;
import lakmalz.git.sampleapp.services.sync.TemplateSync;
import lakmalz.git.sampleapp.utilities.Utilities;
import lakmalz.git.sampleapp.utilities.customview.GridSpaceItemDecoration;

import static android.R.attr.numColumns;

public class MainActivity extends AppCompatActivity implements TemplateSync.GetTemplateCallback{

    @BindView(R.id.rv_template_list)
    RecyclerView rvTemplateList;

    @BindView(R.id.swipe_template_list)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String TAG = "MainActivity";
    private TemplateSync mTemplateSync;
    private TemplateListAdapter mAdapter;
    private int columnCount = 2;
    private int offsetPx = Utilities.dpToPx(16);
    private int spacePx = Utilities.dpToPx(0);
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                getTemplates();
            }
        });
    }

    public void init() {
        initListView();
        mTemplateSync = new TemplateSync();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("Please wait...");
        getTemplates();
    }

    private void getTemplates() {
        mProgressDialog.show();
        mTemplateSync.getTemplateList(createGetTemplateRequest(), this);
    }

    private void initListView() {
        List<Template> templates = new ArrayList<>();
        mAdapter = new TemplateListAdapter(this, templates);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columnCount);
        rvTemplateList.setHasFixedSize(true);
        rvTemplateList.setLayoutManager(gridLayoutManager);
        rvTemplateList.addItemDecoration(new GridSpaceItemDecoration(offsetPx, spacePx, numColumns));
        rvTemplateList.addItemDecoration(new GridBottomOffsetItemDecoration(offsetPx, numColumns));
        rvTemplateList.addItemDecoration(new GridTopOffsetItemDecoration(offsetPx, numColumns));
        rvTemplateList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rvTemplateList.setAdapter(mAdapter);
    }

    private GetTemplateRequest createGetTemplateRequest() {
        GetTemplateRequest getTemplateRequest = new GetTemplateRequest();
        getTemplateRequest.category_id = 1;
        getTemplateRequest.token = "0KN4gxvuOyd7CRclLl6jzkPzv1qayBi0";//after few times this should be change
        return getTemplateRequest;
    }

    @Override
    public void getTemplateListSuccess(GetTemplateResponse response) {
        Log.e(TAG, "getTemplateListSuccess: "+response.status);
        if (response.templateList.size() >0) {
            mAdapter.addAll(response.templateList);
        }
        mProgressDialog.dismiss();
    }

    @Override
    public void getTemplateListFailed(String message) {
        Log.e(TAG, "getTemplateListFailed: "+message );
        mProgressDialog.dismiss();
    }
}
