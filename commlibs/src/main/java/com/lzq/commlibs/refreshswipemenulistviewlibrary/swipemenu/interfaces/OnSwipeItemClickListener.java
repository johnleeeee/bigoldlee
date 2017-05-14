package com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.interfaces;


import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.bean.SwipeMenu;
import com.lzq.commlibs.refreshswipemenulistviewlibrary.swipemenu.view.SwipeMenuView;

public interface OnSwipeItemClickListener {
    void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
}