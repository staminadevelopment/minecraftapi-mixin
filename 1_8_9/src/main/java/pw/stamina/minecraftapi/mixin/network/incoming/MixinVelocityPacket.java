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

package pw.stamina.minecraftapi.mixin.network.incoming;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import pw.stamina.minecraftapi.network.incoming.VelocityPacket;

@Mixin(S12PacketEntityVelocity.class)
public class MixinVelocityPacket implements VelocityPacket {

    @Shadow private int entityID;

    @Shadow private int motionX;
    @Shadow private int motionY;
    @Shadow private int motionZ;

    @Override
    public int getEntityId() {
        return entityID;
    }

    @Override
    public int getMotionX() {
        return motionX;
    }

    @Override
    public void setMotionX(int motionX) {
        this.motionX = motionX;
    }

    @Override
    public int getMotionY() {
        return motionY;
    }

    @Override
    public void setMotionY(int motionY) {
        this.motionY = motionY;
    }

    @Override
    public int getMotionZ() {
        return motionZ;
    }

    @Override
    public void setMotionZ(int motionZ) {
        this.motionZ = motionZ;
    }

    @Override
    public void multiplyMotion(double multiplier) {
        motionX *= multiplier;
        motionY *= multiplier;
        motionZ *= multiplier;
    }
}
