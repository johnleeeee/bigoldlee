package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.bean.ShopInfoDataVO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lizhiqiang on 2017/5/16.
 */

public class ShopListShowAdapter extends BaseAdapter{

    private Context context;
    private List<ShopInfoDataVO> shopInfoVOList;
    private ShopInfoDataVO shopInfoVO;

    public ShopListShowAdapter(Context context, List<ShopInfoDataVO> shopInfoVOList){
        this.context = context;
        this.shopInfoVOList = shopInfoVOList;
    }

    public void notifyUpdata(List<ShopInfoDataVO> shopInfoVOList){
        this.shopInfoVOList = shopInfoVOList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return shopInfoVOList.size();
    }

    @Override
    public ShopInfoDataVO getItem(int position) {
        return shopInfoVOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        shopInfoVO = shopInfoVOList.get(position);
        ShopListShowAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shop_list_shop, null);
            viewHolder = new ShopListShowAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShopListShowAdapter.ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(shopInfoVO.getLogo()).into(viewHolder.shopImage);
        viewHolder.shopName.setText(shopInfoVO.getName());
        viewHolder.shopInstruction.setText(shopInfoVO.getInstruction());
        viewHolder.shopOpentime.setText(shopInfoVO.getStart_time()+"-"+shopInfoVO.getEnd_time());
        viewHolder.shopAddress.setText(shopInfoVO.getAddress());
        return convertView;
    }

    class ViewHolder {


        ImageView shopImage;
        TextView shopName;
        TextView shopInstruction;
        TextView shopOpentime;
        TextView shopAddress;

        public ViewHolder(View itemView) {

            shopImage = (ImageView) itemView.findViewById(R.id.shop_image);
            shopName = (TextView) itemView.findViewById(R.id.shop_name);
            shopInstruction = (TextView) itemView.findViewById(R.id.shop_instruction);
            shopOpentime = (TextView) itemView.findViewById(R.id.shop_opentime);
            shopAddress = (TextView) itemView.findViewById(R.id.shop_address);

        }

    }
    
}
