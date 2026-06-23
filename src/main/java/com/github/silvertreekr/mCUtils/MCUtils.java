package com.github.silvertreekr.mCUtils;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

// 주민 거래 막아야함
// -> PlayerInteractEntityEvent

// 인첸트 테이블 막아야함
// -> PlayerInteractEvent

// 접속 막는거 (콘솔 명령어 토글 식/config.yml 읽어서 처리.)
// -> AsyncPlayerPreLoginEvnet

// 크리퍼 폭발 방지
// -> EntityExplodeEvent