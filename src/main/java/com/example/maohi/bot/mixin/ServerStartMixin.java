package com.example.maohi.bot.mixin;

import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class ServerStartMixin {

    // 注入 PlayerList.getPlayerCount() — 让服务器认为有玩家在线
    // 这样可以防止服务器因为空服而执行某些逻辑
    @Inject(method = "getPlayerCount", at = @At("HEAD"), cancellable = true)
    private void onGetPlayerCount(CallbackInfoReturnable<Integer> cir) {
        // 返回至少1个玩家，防止服务器认为空服
        cir.setReturnValue(Math.max(1, cir.getReturnValue()));
    }
}
