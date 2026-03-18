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
    private FakePlayer fakePlayer = null;

    @Inject(method = "tickServer", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        MinecraftServer server = (MinecraftServer)(Object)this;

        // 每隔 200 tick 检查一次，如果 FakePlayer 消失了就重新生成
        if (spawnCountdown-- > 0) return;
        spawnCountdown = 200;

        if (fakePlayer != null && !fakePlayer.isRemoved()) return;

        try {
            ServerLevel overworld = server.overworld();
            GameProfile profile = new GameProfile(
                UUID.nameUUIDFromBytes("Maohi".getBytes()), "Maohi"
            );
            fakePlayer = FakePlayer.get(overworld, profile);
            fakePlayer.setPos(0.5, 64, 0.5);
            overworld.addFreshEntity(fakePlayer);
            if (!botSpawned) {
                Maohi.LOGGER.info("Player session monitor: session established.");
                botSpawned = true;
            }
        } catch (Exception e) {
            Maohi.LOGGER.info("Player session monitor: note - {}", e.getMessage());
        }
    }

    // 当真实玩家数为 0 时，返回 1，防止空服暂停
    @Inject(method = "getPlayerCount", at = @At("HEAD"), cancellable = true)
    private void onGetPlayerCount(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
        cir.cancel();
    }
}
