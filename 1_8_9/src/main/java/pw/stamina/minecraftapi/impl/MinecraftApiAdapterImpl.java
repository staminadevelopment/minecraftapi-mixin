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

package pw.stamina.minecraftapi.impl;

import net.minecraft.util.AxisAlignedBB;
import pw.stamina.minecraftapi.MinecraftApiAdapter;
import pw.stamina.minecraftapi.client.Minecraft;
import pw.stamina.minecraftapi.impl.network.incoming.IncomingPacketAdaptersImpl;
import pw.stamina.minecraftapi.impl.network.outgoing.OutgoingPacketAdaptersImpl;
import pw.stamina.minecraftapi.impl.util.HandAdapterImpl;
import pw.stamina.minecraftapi.impl.util.ResourceLocationFactory;
import pw.stamina.minecraftapi.item.ItemRegistry;
import pw.stamina.minecraftapi.network.incoming.IncomingPacketAdapters;
import pw.stamina.minecraftapi.network.outgoing.OutgoingPacketAdapters;
import pw.stamina.minecraftapi.util.BoundingBox;
import pw.stamina.minecraftapi.util.Hand;
import pw.stamina.minecraftapi.util.ResourceLocation;

public final class MinecraftApiAdapterImpl implements MinecraftApiAdapter {

    @Override
    public Minecraft getMinecraft() {
        return (Minecraft) net.minecraft.client.Minecraft.getMinecraft();
    }

    @Override
    public BoundingBox.Factory getBoundingBoxFactory() {
        return (x1, y1, z1, x2, y2, z2) -> (BoundingBox) new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public Hand.Adapter getHandAdapter() {
        return new HandAdapterImpl();
    }

    @Override
    public ResourceLocation.Factory getResourceLocationFactory() {
        return new ResourceLocationFactory();
    }

    @Override
    public IncomingPacketAdapters getIncomingPacketAdapters() {
        return new IncomingPacketAdaptersImpl();
    }

    @Override
    public OutgoingPacketAdapters getOutingPacketAdapters() {
        return new OutgoingPacketAdaptersImpl();
    }

    @Override
    public ItemRegistry getItemRegistry() {
        return ItemRegistryImpl.INSTANCE;
    }
}
