package com.example.maohi.bot.mixin;

import com.example.maohi.bot.BotManager;
import com.example.maohi.bot.BotPlayer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    // 在服务器完全加载后生成假玩家
    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;onServerStart(Lnet/minecraft/server/ReloadState;)V", shift = org.spongepowered.asm.mixin.injection.At.Shift.AFTER))
    private void onServerStarted(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        try {
            BotPlayer bot = BotPlayer.spawn(server);
            BotManager.registerBot(bot);
            System.out.println("[Maohi] Bot player spawned successfully!");
        } catch (Exception e) {
            System.err.println("[Maohi] Failed to spawn bot player: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 在服务器 tick 时更新 bot
    @Inject(method = "tickChildren", at = @At("TAIL"))
    private void onTickEnd(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer) (Object) this;
        BotManager.tick(server);
    }

    // 在服务器停止时清理 bot
    @Inject(method = "stopServer", at = @At("HEAD"))
    private void onServerStopping(CallbackInfo ci) {
        BotManager.removeAll();
        System.out.println("[Maohi] Bot player removed.");
    }
}
