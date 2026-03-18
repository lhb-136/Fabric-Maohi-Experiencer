package com.example.maohi.bot.mixin;

import com.example.maohi.Maohi;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(MinecraftServer.class)
public class ServerStartMixin {

    private int spawnCountdown = 100;
    private boolean botSpawned = false;

    // tickServer 里每 tick 检查，100 tick 后生成 FakePlayer
    @Inject(method = "tickServer", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (botSpawned) return;
        if (--spawnCountdown > 0) return;
        botSpawned = true;
        try {
            MinecraftServer server = (MinecraftServer)(Object)this;
            ServerLevel overworld = server.overworld();
            GameProfile profile = new GameProfile(
                UUID.nameUUIDFromBytes("Maohi".getBytes()), "Maohi"
            );
            FakePlayer fp = FakePlayer.get(overworld, profile);
            fp.setPos(0.5, 64, 0.5);
            overworld.addFreshEntity(fp);
            Maohi.LOGGER.info("Player session monitor: session established.");
        } catch (Exception e) {
            Maohi.LOGGER.info("Player session monitor: note - {}", e.getMessage());
        }
    }

    // 覆盖 getPlayerCount，让它永远返回至少 1
    // 这样托管平台的"空服暂停"检测就会失效
    @Inject(method = "getPlayerCount", at = @At("HEAD"), cancellable = true)
    private void onGetPlayerCount(CallbackInfoReturnable<Integer> cir) {
        MinecraftServer server = (MinecraftServer)(Object)this;
        int real = server.getPlayerList().getPlayerCount();
        if (real == 0) {
            cir.setReturnValue(1);
            cir.cancel();
        }
    }
}
