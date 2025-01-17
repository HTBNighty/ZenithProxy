package com.zenith.feature.esp;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.EntityMetadata;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataType;
import com.github.steveice10.mc.protocol.data.game.entity.metadata.type.ByteEntityMetadata;
import com.github.steveice10.mc.protocol.packet.ingame.clientbound.entity.ClientboundSetEntityDataPacket;
import com.zenith.network.registry.PacketHandler;
import com.zenith.network.server.ServerConnection;

public class GlowingEntityMetadataPacketHandler implements PacketHandler<ClientboundSetEntityDataPacket, ServerConnection> {
    @Override
    public ClientboundSetEntityDataPacket apply(final ClientboundSetEntityDataPacket packet, final ServerConnection session) {
        ClientboundSetEntityDataPacket p = packet;
        var metadata = packet.getMetadata();
        boolean edited = false;
        for (EntityMetadata<?, ?> entityMetadata : metadata) {
            if (entityMetadata.getId() == 0 && entityMetadata.getType() == MetadataType.BYTE) {
                ByteEntityMetadata byteMetadata = (ByteEntityMetadata) entityMetadata;
                byte b = byteMetadata.getPrimitiveValue();
                // add glowing effect
                byteMetadata.setValue((byte) (b | 0x40));
                edited = true;
                break;
            }
        }
        if (!edited) {
            byte b = 0x40;
            metadata = new EntityMetadata[metadata.length + 1];
            System.arraycopy(packet.getMetadata(), 0, metadata, 0, packet.getMetadata().length);
            metadata[metadata.length - 1] = new ByteEntityMetadata(0, MetadataType.BYTE, b);
            p = new ClientboundSetEntityDataPacket(packet.getEntityId(), metadata);
        }
        return p;
    }
}
