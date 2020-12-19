package org.sharo.scp040jp.network

import net.minecraft.client.entity.player.ClientPlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.network.NetworkDirection
import net.minecraftforge.fml.network.NetworkRegistry
import net.minecraftforge.fml.network.simple.SimpleChannel
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.network.packet.PacketSpawnNeko

class PacketHandler {
    companion object {
        private const val PROTOCOL_VERSION = "1"
        @JvmStatic
        var disc = 0

        @JvmStatic
        val INSTANCE: SimpleChannel = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation(Core.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion { PROTOCOL_VERSION }
            .simpleChannel()

        @JvmStatic
        fun register() {
            INSTANCE.registerMessage(disc++, PacketSpawnNeko::class.java, PacketSpawnNeko::encode, ::PacketSpawnNeko, PacketSpawnNeko::handle)
        }

        @JvmStatic
        fun sendToClient(packet: Any, player: ServerPlayerEntity) {
            INSTANCE.sendTo(packet, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT)
        }

        @JvmStatic
        fun sendToServer(packet: Any, player: ClientPlayerEntity) {
            INSTANCE.sendTo(packet, player.connection.networkManager, NetworkDirection.PLAY_TO_SERVER)
        }
    }
}
