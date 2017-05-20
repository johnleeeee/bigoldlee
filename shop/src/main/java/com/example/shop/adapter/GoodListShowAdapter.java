package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.bean.GoodsInfoDataVO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lizhiqiang on 2017/5/16.
 */

public class GoodListShowAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsInfoDataVO> goodListData;
    private GoodsInfoDataVO goodsInfoDataVO;

    public GoodListShowAdapter(Context context, List<GoodsInfoDataVO> goodListData){
        this.context = context;
        this.goodListData = goodListData;
    }

    public void notifyUpdata(List<GoodsInfoDataVO> goodListData){
        this.goodListData = goodListData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return goodListData.size();
    }

    @Override
    public GoodsInfoDataVO getItem(int position) {
        return goodListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        goodsInfoDataVO = goodListData.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.goods_list_shop, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(goodsInfoDataVO.getImage()).into(viewHolder.goodsImage);
        viewHolder.goodsName.setText(goodsInfoDataVO.getName());
        viewHolder.goodsBrief.setText(goodsInfoDataVO.getBrief());
        viewHolder.goodsPrice.setText(goodsInfoDataVO.getPrice()+"/"+ goodsInfoDataVO.getUnit());
        viewHolder.goodsMarketPrice.setText(goodsInfoDataVO.getMarket_price()+"/"+ goodsInfoDataVO.getUnit());
        if (goodsInfoDataVO.getStatus().equals("1")){
            viewHolder.goodsStatus.setText("已上架");
        }else{
            viewHolder.goodsStatus.setText("已下架");
        }
        return convertView;
    }

    class ViewHolder {


        ImageView goodsImage;
        TextView goodsName;
        TextView goodsBrief;
        TextView goodsPrice;
        TextView goodsMarketPrice;
        TextView goodsStatus;

        public ViewHolder(View itemView) {

            goodsImage = (ImageView) itemView.findViewById(R.id.goods_image);
            goodsName = (TextView) itemView.findViewById(R.id.goods_name);
            goodsBrief = (TextView) itemView.findViewById(R.id.goods_brief);
            goodsPrice = (TextView) itemView.findViewById(R.id.goods_price);
            goodsMarketPrice = (TextView) itemView.findViewById(R.id.goods_market_price);
            goodsStatus = (TextView) itemView.findViewById(R.id.goods_status);
        }

    }
}
