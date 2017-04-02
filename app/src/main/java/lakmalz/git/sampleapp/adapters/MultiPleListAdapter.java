package lakmalz.git.sampleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lakmalz.git.sampleapp.R;
import lakmalz.git.sampleapp.models.Item;

/**
 * Created by Lakmal Weerasekara on 2/4/17.
 */
public class MultiPleListAdapter extends RecyclerView.Adapter {

    private List<Item> mDataSet;
    private Context mContext;
    private final int VIEW1 = 1;
    private final int VIEW2 = 2;

    public MultiPleListAdapter(List<Item> dataSet, Context context) {
        mDataSet = dataSet;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == VIEW1) {
            view = inflater.inflate(R.layout.widget_item_view1, parent, false);
            viewHolder = new Holder1(view);
        } else {
            view = inflater.inflate(R.layout.widget_item_view2, parent, false);
            viewHolder = new Holder2(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder genHolder, int position) {
        Item item = mDataSet.get(position);

        if (genHolder instanceof Holder1) {
            Holder1 holder = (Holder1) genHolder;
            Picasso.with(mContext).load(item.getImage1()).placeholder(R.drawable.placeholder).into(holder.img1);
            Picasso.with(mContext).load(item.getImage2()).placeholder(R.drawable.placeholder).into(holder.img2);
        } else if(genHolder instanceof Holder2){
            Holder2 holder = (Holder2) genHolder;
            Picasso.with(mContext).load(item.getImage1()).placeholder(R.drawable.placeholder).into(holder.img1);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet.get(position).getLayout_type() == VIEW1) {
            return VIEW1;
        } else {
            return VIEW2;
        }
    }

    public class Holder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.img1)
        ImageView img1;

        @BindView(R.id.img2)
        ImageView img2;

        public Holder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Holder2 extends RecyclerView.ViewHolder {

        @BindView(R.id.img1)
        ImageView img1;

        public Holder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

