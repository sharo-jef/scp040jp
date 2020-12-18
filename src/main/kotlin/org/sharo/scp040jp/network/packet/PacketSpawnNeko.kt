package org.sharo.scp040jp.network.packet

import net.minecraft.entity.SpawnReason
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.fml.network.NetworkEvent
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.entity.EntityTypes
import java.util.function.Supplier

class PacketSpawnNeko {
    private val pos: BlockPos

    constructor(buf: PacketBuffer) {
        pos = buf.readBlockPos()
    }
    constructor(pos: BlockPos) {
        this.pos = pos
    }

    fun encode(buf: PacketBuffer) {
        buf.writeBlockPos(pos)
    }

    // Server Side Only
    fun handle(ctx: Supplier<NetworkEvent.Context>) {
        ctx.get().enqueueWork {
            Core.logger.info("ENQUEUE WORK")
            val world = ctx.get().sender?.world
            if (world != null) {
                EntityTypes.SCP040JP.get().spawn(world as ServerWorld, null, null, pos, SpawnReason.EVENT, false, true)
            }
        }
        ctx.get().packetHandled = true
    }
}
