package com.example.yangming.recyclerviewtest;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewPropertyAnimator;

/**
 * Created by ivy on 2017/3/22.
 * Description：
 */

public class ScaleItemAnimator extends BaseItemAnimator {

    @Override
    public void setRemoveAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimator animator) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setPivotX(listHolder.layout, listHolder.layout.getWidth() / 2);
        ViewCompat.setPivotY(listHolder.layout, listHolder.layout.getHeight() / 2);
        animator.scaleX(0).scaleY(0);
    }

    @Override
    public void removeAnimationEnd(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 1);
        ViewCompat.setScaleY(listHolder.layout, 1);
    }

    @Override
    public void addAnimationInit(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 0);
        ViewCompat.setScaleY(listHolder.layout, 0);
    }

    @Override
    public void setAddAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimator animator) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setPivotX(listHolder.layout, listHolder.layout.getWidth() / 2);
        ViewCompat.setPivotY(listHolder.layout, listHolder.layout.getHeight() / 2);
        animator.scaleX(1).scaleY(1);
    }

    @Override
    public void addAnimationCancel(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 1);
        ViewCompat.setScaleY(listHolder.layout, 1);
    }

    @Override
    public void setOldChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimator animator) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setPivotX(listHolder.layout, listHolder.layout.getWidth() / 2);
        ViewCompat.setPivotY(listHolder.layout, listHolder.layout.getHeight() / 2);
        animator.scaleX(0).scaleY(0);
    }

    @Override
    public void oldChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 1);
        ViewCompat.setScaleY(listHolder.layout, 1);
    }

    @Override
    public void newChangeAnimationInit(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 0);
        ViewCompat.setScaleY(listHolder.layout, 0);
    }

    @Override
    public void setNewChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimator animator) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setPivotX(listHolder.layout, listHolder.layout.getWidth() / 2);
        ViewCompat.setPivotY(listHolder.layout, listHolder.layout.getHeight() / 2);
        animator.scaleX(1).scaleY(1);
    }

    @Override
    public void newChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        AAdapter.ListHolder listHolder = (AAdapter.ListHolder) holder;
        ViewCompat.setScaleX(listHolder.layout, 1);
        ViewCompat.setScaleY(listHolder.layout, 1);
    }
}
