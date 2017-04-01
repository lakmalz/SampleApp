package lakmalz.git.sampleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lakmalz.git.sampleapp.R;
import lakmalz.git.sampleapp.models.Template;
import lakmalz.git.sampleapp.utilities.Utilities;

/**
 * Created by Lakmal Weerasekara on 1/4/17.
 */

public class TemplateListAdapter extends RecyclerView.Adapter<TemplateListAdapter.Holder> {

    private List<Template> mDataSet;
    private Context mContext;
    private int width = 0;

    public TemplateListAdapter(Context context, List<Template> dataSet) {
        mDataSet = dataSet;
        mContext = context;
        width = Utilities.getScreenWidth(mContext);
    }

    public void addItem(Template item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<Template> list) {
        mDataSet.clear();
        mDataSet.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.widget_list_item, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Template template = mDataSet.get(position);
        holder.txtTemplateName.setText(template.image_name);
        Picasso.with(mContext).load(template.image_png_url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .resize(width, width).into(holder.imgvwTemplate);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_vw_template)
        ImageView imgvwTemplate;
        @BindView(R.id.txt_template_name)
        TextView txtTemplateName;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

