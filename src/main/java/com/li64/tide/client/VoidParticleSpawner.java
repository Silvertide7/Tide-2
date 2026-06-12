package com.li64.tide.client;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.registries.TideParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class VoidParticleSpawner {
    public static void spawnParticles(Player player, int amount) {
        if (!Tide.CLIENT_CONFIG.general.ambientVoidParticles) return;
        BlockPos playerPos = player.getOnPos();
        Random random = new Random();
        Level level = player.level();
        int surfaceY = FishingMedium.VOID.getVoidSurface(level);
        if (playerPos.getY() <= surfaceY + 24) {
            for (int i = 0; i < amount; i++) {
                BlockPos spawnPos = randomize(playerPos, 10, surfaceY, random);
                if (level.isEmptyBlock(spawnPos)) {
                    level.addParticle(TideParticleTypes.VOID_RIPPLE_SMALL,
                            spawnPos.getX(), spawnPos.getY() - 0.5, spawnPos.getZ(),
                            0, 0, 0
                    );
                }
            }
        }
    }

    private static BlockPos randomize(BlockPos pos, int distanceXZ, int y, Random random) {
        int x = pos.getX() + randomizedInt(distanceXZ, random);
        int z = pos.getZ() + randomizedInt(distanceXZ, random);
        return new BlockPos(x, y, z);
    }

    private static int randomizedInt(int amount, Random rand) {
        return rand.nextInt(-amount, amount + 1);
    }
}
