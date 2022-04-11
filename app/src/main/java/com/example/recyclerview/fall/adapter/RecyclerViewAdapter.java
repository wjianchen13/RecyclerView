package com.example.recyclerview.fall.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.recyclerview.R;


//让我们的适配器继承自RecyclerView.Adapter<>，并指定泛型为我们适配器的类名.ViewHolder，
// ViewHolder继承自RecyclerView.ViewHolder，并实现每个继承要实现的方法
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private int[] image;
    //声明一个这个接口的变量
    private onRecyclerViewItemClickListener mOnRecyclerViewItemClickListener=null;

    //构造函数，主要用于接受数据，方便我们在适配器中对数据操作
    public RecyclerViewAdapter(Context context, int[] image) {
        this.context = context;
        this.image = image;
    }

    //创建ViewHolder，我们需要在这个方法中给新建一个view对象，再初始化一个ViewHolder对象，将view对象传入
    //然后返回一个ViewHolder对象
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建一个view对象（通过布局填充器将布局文件转化为view对象）
        View view = View.inflate(context, R.layout.waterfall_item, null);
        //初始化一个ViewHolder对象，传入view对象
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        //将ViewHolder对象返回出去
        return viewHolder;
    }

    //绑定ViewHolder，我们需要在这个方法中给控件设置数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //给ImageView控件设置数据
        holder.mImageView.setImageResource(image[position]);

        //给每个itemview添加一个Tag,传递数据
        holder.itemView.setTag(image[position]);

    }

    //获取item的条目总数
    @Override
    public int getItemCount() {
        //直接返回图片数组的长度即可
        return image.length;
    }

    //将点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            //通过v.getTag()接受数据
            mOnRecyclerViewItemClickListener.onItemClick(v, (Integer) v.getTag());
        }

    }

    //我们自定义的ViewHolder类，继承自RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            //通过传过来的view对象，我们来实例化控件
            mImageView = (ImageView) itemView.findViewById(R.id.show_img);
        }
    }

    //自定义一个监听的接口，里面包含itemclick的监听方法，主要用于拿数据，方便外部调用拿数据
    public interface onRecyclerViewItemClickListener{
        void onItemClick(View view, int img);
    }

    //定义一个设置Listener的方法（），作用是暴露给外面的调用者，方便调用
    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
