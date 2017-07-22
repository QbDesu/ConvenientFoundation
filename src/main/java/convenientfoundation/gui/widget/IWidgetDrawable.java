package convenientfoundation.gui.widget;

import convenientfoundation.gui.IGui;
import net.minecraft.client.gui.GuiScreen;

public interface IWidgetDrawable extends IWidget {
    <T extends GuiScreen & IGui> boolean isDrawable(T guiScreen);
    <T extends GuiScreen & IGui> void draw(T guiScreen, float partialTicks, int mouseX, int mouseY);
}
