/*
 * MIT License
 *
 * Copyright (c) 2018 Stamina Development
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pw.stamina.minecraftapi.mixin.event.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.INetHandler;
import net.minecraft.network.ThreadQuickExitException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import pw.stamina.minecraftapi.MinecraftApi;
import pw.stamina.minecraftapi.event.network.IncomingPacketEvent;
import pw.stamina.minecraftapi.network.NetworkManager;
import pw.stamina.minecraftapi.network.Packet;

@Mixin(net.minecraft.network.NetworkManager.class)
public abstract class MixinIncomingPacketEvent implements NetworkManager {

    @Shadow private Channel channel;
    @Shadow private INetHandler packetListener;

    /**
     * @reason We have to override this method, because
     * splitting it into two different injection points,
     * seems to cause data races.
     * @author Abstraction
     */
    @Overwrite
    protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, net.minecraft.network.Packet<?> p_channelRead0_2_) throws Exception {
        if (this.channel.isOpen()) {
            try {
                IncomingPacketEvent event = new IncomingPacketEvent((Packet) p_channelRead0_2_, this);
                MinecraftApi.emitEvent(event);

                if (event.isCancelled()) {
                    return;
                }

                ((net.minecraft.network.Packet<INetHandler>) p_channelRead0_2_).processPacket(this.packetListener);

                event.sendPackets(this::sendPacket);
            } catch (ThreadQuickExitException ignored) { }
        }
    }
}
