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

package pw.stamina.minecraftapi.mixin.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw.stamina.minecraftapi.MinecraftApi;
import pw.stamina.minecraftapi.client.Minecraft;
import pw.stamina.minecraftapi.client.PlayerController;
import pw.stamina.minecraftapi.client.ScaledResolution;
import pw.stamina.minecraftapi.entity.living.ClientPlayer;
import pw.stamina.minecraftapi.impl.MinecraftApiAdapterImpl;
import pw.stamina.minecraftapi.render.EntityRenderer;
import pw.stamina.minecraftapi.render.FontRenderer;
import pw.stamina.minecraftapi.render.RenderManager;
import pw.stamina.minecraftapi.world.World;

@Mixin(net.minecraft.client.Minecraft.class)
public class MixinMinecraft implements Minecraft {

    @Shadow public PlayerControllerMP playerController;
    @Shadow public EntityPlayerSP thePlayer;
    @Shadow public WorldClient theWorld;
    @Shadow private int rightClickDelayTimer;

    @Shadow private net.minecraft.client.renderer.entity.RenderManager renderManager;

    @Shadow public net.minecraft.client.renderer.EntityRenderer entityRenderer;

    @Shadow public net.minecraft.client.gui.FontRenderer fontRendererObj;

    @Shadow public GuiIngame ingameGUI;

    @Shadow private static net.minecraft.client.Minecraft theMinecraft;

    @Shadow public int displayWidth;

    @Shadow public int displayHeight;

    @Override
    public FontRenderer getFontRenderer() {
        return (FontRenderer) fontRendererObj;
    }

    @Override
    public PlayerController getPlayerController() {
        return (PlayerController) playerController;
    }

    @Override
    public ClientPlayer getPlayer() {
        return (ClientPlayer) thePlayer;
    }

    @Override
    public World getWorld() {
        return (World) theWorld;
    }

    @Override
    public RenderManager getRenderManager() {
        return (RenderManager) renderManager;
    }

    @Override
    public EntityRenderer getEntityRenderer() {
        return (EntityRenderer) entityRenderer;
    }

    @Override
    public void setRightClickDelay(int delay) {
        rightClickDelayTimer = delay;
    }

    @Override
    public int getRightClickDelay() {
        return rightClickDelayTimer;
    }

    @Override
    public void printChatMessage(String message) {
        ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
    }

    @Override
    public ScaledResolution getScaledResolution() {
        return (ScaledResolution) new net.minecraft.client.gui.ScaledResolution(theMinecraft);
    }

    @Override
    public int getDisplayWidth() {
        return displayWidth;
    }

    @Override
    public int getDisplayHeight() {
        return displayHeight;
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    private void startGame(CallbackInfo cbi) {
        MinecraftApi.bootstrap(new MinecraftApiAdapterImpl());
    }
}
