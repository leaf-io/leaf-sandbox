package io.leaf.core.manager;

import io.vertx.core.Handler;

/**
 * Created by Gabo on 2015.07.16..
 */
public class LeafManagerStartServicesTimerHandler implements Handler<Long> {

    protected LeafManagerServiceImpl leafManager;

    public LeafManagerStartServicesTimerHandler(LeafManagerServiceImpl leafManager) {
        this.leafManager = leafManager;
    }

    @Override
    public void handle(Long aLong) {
        leafManager.doStartNodes();
    }
}
