package com.zinu.account_manager.configurations;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

   private static final ThreadLocal<Integer> CONTEXT = new ThreadLocal<>();

    

    public static void setCurrentTenant(int tenantId) {
        CONTEXT.set(tenantId);
    }

    public static Integer getCurrentTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

