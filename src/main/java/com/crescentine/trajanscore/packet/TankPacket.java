package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TankPacket {
    public int key;

    public TankPacket(int key) {
        this.key = key;
    }
    public TankPacket(FriendlyByteBuf buffer) {
        key = buffer.readInt();
    }
    public static TankPacket decode(FriendlyByteBuf buffer) {
        return new TankPacket(buffer.readInt());
    }
    public void writePacketData(FriendlyByteBuf buf) {
        buf.writeInt(this.key);
    }
    public static void handle(TankPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
                    Player player = context.getSender();
                    if (player == null || !player.isAlive()) return;
                    if (player.getVehicle() instanceof BaseTankEntity) {
                        BaseTankEntity Tank = (BaseTankEntity) player.getVehicle();
                        Tank.shoot(player, Tank, player.level);
                    }
                }
        );
        context.setPacketHandled(true);
    }
}