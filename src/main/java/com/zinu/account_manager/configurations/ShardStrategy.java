package com.zinu.account_manager.configurations;

public class ShardStrategy {

    private static final int NUM_SHARD = 3;
    public static int calculateShard(String driverId) {
        int hash = 0;
        for (int i = 0; i < driverId.length(); i++) {
            hash = 31 * hash + driverId.charAt(i);
        }
        hash = Math.abs(hash);
        return hash % NUM_SHARD;
    }
    
}

