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

package pw.stamina.minecraftapi.mixin.entity.living;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import pw.stamina.minecraftapi.entity.living.Player;
import pw.stamina.minecraftapi.item.Item;
import pw.stamina.minecraftapi.util.Hand;

@Mixin(EntityPlayer.class)
public abstract class MixinPlayer extends EntityLivingBase implements Player {

    @Shadow
    public abstract ItemStack shadow$getItemStackFromSlot(EntityEquipmentSlot slotIn);

    public MixinPlayer(World worldIn) {
        super(worldIn);
    }

    @Override
    public Item getHeldItem() {
        return (Item) getHeldItemMainhand().getItem();
    }

    @Override
    public Item getHeldItem(Hand hand) {
        if (hand == Hand.main()) {
            return getHeldItem();
        } else {
            return (Item) getHeldItemOffhand().getItem();
        }
    }
}
