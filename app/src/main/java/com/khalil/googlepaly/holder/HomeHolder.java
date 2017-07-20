package com.khalil.googlepaly.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.khalil.googlepaly.R;
import com.khalil.googlepaly.base.BaseHolder;
import com.khalil.googlepaly.bean.ItemBean;
import com.khalil.googlepaly.config.Constants;
import com.khalil.googlepaly.utils.StringUtils;
import com.khalil.googlepaly.utils.UIUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页的ViewHolder：
 * 1.提供视图
 * 2.接收/加载数据
 * 3.数据和视图的绑定
 */
public class HomeHolder extends BaseHolder<ItemBean> {

    @BindView(R.id.item_appinfo_iv_icon)
    ImageView mItemAppinfoIvIcon;
    @BindView(R.id.item_appinfo_tv_title)
    TextView mItemAppinfoTvTitle;
    @BindView(R.id.item_appinfo_rb_stars)
    RatingBar mItemAppinfoRbStars;
    @BindView(R.id.item_appinfo_tv_size)
    TextView mItemAppinfoTvSize;
    @BindView(R.id.item_appinfo_tv_des)
    TextView mItemAppinfoTvDes;

    /**
     * 决定所能提供的视图长什么样子
     */
    @Override
    public View initHolderView() {
        View itemView = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
        //找出孩子,并转换成成员变量
        ButterKnife.bind(this, itemView);
        return itemView;
    }

    /**
     * 视图和数据具体绑定
     */
    @Override
    public void refreshHolderView(ItemBean data) {
        //view-->成员变量
        //data-->局部变量,基类
        //data+view
        mItemAppinfoTvTitle.setText(data.name);
        mItemAppinfoTvSize.setText(StringUtils.formatFileSize(data.size));
        mItemAppinfoTvDes.setText(data.des);
        //ratingbar
        mItemAppinfoRbStars.setRating(data.stars);

        //图片加载
        //http://localhost:8080/GooglePlayServer/image?name=
        String url = Constants.URLS.IMGBASEURL + data.iconUrl;
        Picasso.with(UIUtils.getContext()).load(url).into(mItemAppinfoIvIcon);
    }
}
