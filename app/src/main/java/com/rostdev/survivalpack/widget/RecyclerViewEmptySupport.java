package com.rostdev.survivalpack.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Rosty on 7/7/2016.
 */
public class RecyclerViewEmptySupport extends RecyclerView {

    private View emptyView;

    private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    public RecyclerViewEmptySupport(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void checkIfEmpty() {

        if (emptyView != null && getAdapter() != null) {

            emptyView.setVisibility(getAdapter().getItemCount() == 0
                    ? VISIBLE : GONE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {

        RecyclerView.Adapter oldAdapter = getAdapter();

        if (oldAdapter != null)
            oldAdapter.unregisterAdapterDataObserver(observer);

        super.setAdapter(adapter);

        if (adapter != null)
            adapter.registerAdapterDataObserver(observer);

        checkIfEmpty();
    }

    public void setEmptyView(View emptyView) {

        this.emptyView = emptyView;
        checkIfEmpty();
    }
}