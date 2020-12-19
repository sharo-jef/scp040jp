package org.sharo.scp040jp.common

import net.minecraft.client.Minecraft
import net.minecraft.client.entity.player.ClientPlayerEntity
import net.minecraft.client.gui.toasts.SystemToast
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.potion.Effect
import net.minecraft.potion.EffectInstance
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.vector.Vector3d
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.event.ServerChatEvent
import net.minecraftforge.event.entity.living.LivingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import org.sharo.scp040jp.Core
import org.sharo.scp040jp.block.Blocks
import org.sharo.scp040jp.entity.EntityTypes
import org.sharo.scp040jp.entity.SCP040JPEntity
import org.sharo.scp040jp.network.PacketHandler
import org.sharo.scp040jp.network.packet.PacketSpawnNeko
import org.sharo.scp040jp.potion.Effects
import org.sharo.scp040jp.util.RayTraceHelper

@Mod.EventBusSubscriber(modid = Core.MODID)
class CommonEventBusSubscriber {
    companion object {
        @JvmStatic
        @SubscribeEvent
        fun livingUpdate(event: LivingEvent.LivingUpdateEvent) {
            if (event.entity is PlayerEntity) {
                val player = event.entity as PlayerEntity
                if (
                    player.getActivePotionEffect(Effects.SCP040JP_EFFECT) != null
                    && !player.world.isRemote
                ) {
                    val ocelots = player.world.getEntitiesWithinAABB(
                        EntityType.OCELOT,
                        AxisAlignedBB(
                            player.positionVec.add(Vector3d(-16.0, -16.0, -16.0)),
                            player.positionVec.add(Vector3d(16.0, 16.0, 16.0))
                        )
                    ) { true }
                    ocelots.forEach {
                        val neko = SCP040JPEntity(EntityTypes.SCP040JP.get(), it.world)
                        neko.copyLocationAndAnglesFrom(it)
                        EntityTypes.SCP040JP.get().spawn(it.world as ServerWorld, null, null, neko.position, SpawnReason.EVENT, true, false)
                        it.setPosition(it.posX, -100.0, it.posZ)
                        it.health = 0f
                    }
                    val cats = player.world.getEntitiesWithinAABB(
                        EntityType.CAT,
                        AxisAlignedBB(
                            player.positionVec.add(Vector3d(-16.0, -16.0, -16.0)),
                            player.positionVec.add(Vector3d(16.0, 16.0, 16.0))
                        )
                    ) { true }
                    cats.forEach {
                        val neko = SCP040JPEntity(EntityTypes.SCP040JP.get(), it.world)
                        neko.copyLocationAndAnglesFrom(it)
                        EntityTypes.SCP040JP.get().spawn(it.world as ServerWorld, null, null, neko.position, SpawnReason.EVENT, true, false)
                        it.setPosition(it.posX, -100.0, it.posZ)
                        it.health = 0f
                    }
                }
                if (player.world.isRemote) {
                    val rayTraceResult = RayTraceHelper.rayTrace(player)
                    if (
                        rayTraceResult != null
                        && rayTraceResult.type == RayTraceResult.Type.BLOCK
                    ) {
                        if (
                            rayTraceResult.block?.block == Blocks.SCP040JP_BLOCK
                            && player.getActivePotionEffect(Effects.SCP040JP_EFFECT) == null
                        ) {
                            player.addPotionEffect(
                                EffectInstance(
                                    Effects.SCP040JP_EFFECT,
                                    Int.MAX_VALUE,
                                    0,
                                    false,
                                    false
                                )
                            )
                            val effect = Effect.get(9)
                            if (effect != null) {
                                player.addPotionEffect(EffectInstance(effect, 3 * 20, 0, false, false))
                            }
                            PacketHandler.sendToServer(PacketSpawnNeko(player.position), player as ClientPlayerEntity)
                            player.sendMessage(StringTextComponent("ねこです。よろしくおねがいします。"), player.uniqueID)
                            player.sendChatMessage("ねこが居た！")
                        }
                    }
                    if (
                        player.getActivePotionEffect(Effects.SCP040JP_EFFECT) != null
                        && player.world.gameTime and 2047L == 1024L
                    ) {
                        Minecraft.getInstance().toastGui.add(
                            SystemToast(
                                SystemToast.Type.NARRATOR_TOGGLE, StringTextComponent("ねこです。"),
                                StringTextComponent(
                                    if (player.rng.nextBoolean()) "よろしくおねがいします。" else "ねこはいます。"
                                )
                            )
                        )
                    }
                }
            }
        }

        @JvmStatic
        @SubscribeEvent
        fun chat(event: ServerChatEvent) {
            if (
                event.message.contains("ねこです")
                || event.message.contains("ねこはいます")
                || event.message.contains("よろしくおねがいします")
                || event.message.contains("ねこが居た")
            ) {
                event.player.world.players.forEach {
                    it.addPotionEffect(EffectInstance(Effects.SCP040JP_EFFECT, Int.MAX_VALUE, 0, false, false))
                }
            }
        }
    }
}
