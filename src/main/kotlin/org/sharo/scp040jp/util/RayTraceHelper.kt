package org.sharo.scp040jp.util

import net.minecraft.block.BlockState
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.vector.Vector3d
import kotlin.math.floor

class RayTraceHelper {
    companion object {
        @JvmStatic
        private val mc: Minecraft = Minecraft.getInstance()
        @JvmStatic
        fun rayTrace(player: PlayerEntity): AdvancedRayTraceResult? {
            if (mc.world?.isRemote == false) {
                return null
            }
            val result = mc.objectMouseOver ?: return null
            return AdvancedRayTraceResult(result.hitVec, result.type, player)
        }
    }

    class AdvancedRayTraceResult(
        val vec: Vector3d,
        val type: RayTraceResult.Type,
        private val player: PlayerEntity
    ) {
        var blockPos: BlockPos? = null
        var block: BlockState? = null

        init {
            when (type) {
                RayTraceResult.Type.BLOCK -> {
                    val vec = vec.add(
                        Vector3d(
                            if (vec.x == floor(vec.x) && vec.x <= player.posX) -1.0 else .0,
                            if (vec.y == floor(vec.y) && vec.y <= player.posYEye) -1.0 else .0,
                            if (vec.z == floor(vec.z) && vec.z <= player.posZ) -1.0 else .0
                        )
                    )
                    blockPos = BlockPos(
                        vec.x.toInt(),
                        vec.y.toInt(),
                        vec.z.toInt()
                    )
                    block = mc.world?.getBlockState(blockPos!!)
                }
                RayTraceResult.Type.ENTITY -> {
                    // Do nothing
                }
                RayTraceResult.Type.MISS -> {
                    // Do nothing
                }
            }
        }
    }
}
