package convenientfoundation.gui.widget.energy;

import convenientfoundation.capabilities.energy.EnergyRegistry;
import convenientfoundation.capabilities.energy.EnergyStack;
import convenientfoundation.gui.widget.IWidgetDrawable;
import convenientfoundation.gui.widget.IWidgetTooltip;
import convenientfoundation.libs.LibRegistries;
import convenientfoundation.util.GuiHelper;
import convenientfoundation.util.Helper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by Necro on 8/28/2017.
 */
public class EnergyStackView implements IWidgetDrawable, IWidgetTooltip {

    public EnergyStack energyStack;
    int posX,posY;

    public EnergyStackView(EnergyStack stack, int posX, int posY){
        this.energyStack=stack;
        this.posX=posX;
        this.posY=posY;
    }

    @Override
    public boolean hasTooltip(GuiScreen guiScreen) {
        return energyStack!=null;
    }

    @Override
    public List<String> getTooltip(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        return energyStack.getTooltip(Helper.getClientWorld());
    }

    @Override
    public boolean isDrawable(GuiScreen guiScreen) {
        return energyStack!=null;
    }

    @Override
    public void draw(GuiScreen guiScreen, float partialTicks, int mouseX, int mouseY) {
        drawEnergy(guiScreen, posX, posY, energyStack);
    }

    public void drawEnergy(GuiScreen guiScreen, int x, int y, EnergyStack stack){
        if (stack == null) {
            return;
        }
        TextureAtlasSprite energySprite = EnergyRegistry.getEnergySprite(stack.getType());
        guiScreen.mc.renderEngine.bindTexture(LibRegistries.ENERGY_TEXTURE_LOCATION);
        GuiHelper.renderTAS(energySprite,x,y,300);

        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.disableBlend();
        FontRenderer fr=GuiHelper.getFontRenderer();
        int amount=stack.getAmount();
        String s;
        if(amount>=1000000)
            s=Integer.toString(amount/1000000)+"m";
        else if(amount>=1000)
            s=Integer.toString(amount/1000)+"k";
        else
            s=(stack.isEmpty()?TextFormatting.YELLOW:"")+Integer.toString(amount);
        fr.drawStringWithShadow(s, (float)(x + 19 - 2 - fr.getStringWidth(s)), (float)(y + 6 + 3), 16777215);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public boolean isVisible() {
        return energyStack!=null;
    }
}
