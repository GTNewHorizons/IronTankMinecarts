package secondderivative.irontankminecarts;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import mods.railcraft.client.render.carts.CartModelManager;
import mods.railcraft.client.render.carts.RenderCartItemFiltered;
import mods.railcraft.client.render.models.ModelSimpleCube;
import mods.railcraft.client.render.models.ModelTextured;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartCopperTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartDiamondTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartGoldTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartIronTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartStainlessSteelTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartSteelTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartTitaniumTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartTungstensteelTank;

public class ClientProxy extends CommonProxy {
    private static String CARTS_FOLDER = "irontankminecarts:textures/entities/carts/";
    public void postInit(FMLPostInitializationEvent event) {
        for (Item cart: IronTankMinecarts.carts.values()) {
            MinecraftForgeClient.registerItemRenderer(cart, new RenderCartItemFiltered(RenderCartItemFiltered.RendererType.Tank));
        }
        addMinecartClassModel(EntityMinecartCopperTank.class, "copper");
        addMinecartClassModel(EntityMinecartIronTank.class, "iron");
        addMinecartClassModel(EntityMinecartGoldTank.class, "gold");
        addMinecartClassModel(EntityMinecartDiamondTank.class, "diamond");
        addMinecartClassModel(EntityMinecartTitaniumTank.class, "titanium");
        addMinecartClassModel(EntityMinecartTungstensteelTank.class, "tungstensteel");
        addMinecartClassModel(EntityMinecartSteelTank.class, "steel");
        addMinecartClassModel(EntityMinecartStainlessSteelTank.class, "stainlesssteel");
    };

    private void addMinecartClassModel(Class cart, String textureName) {
        ModelTextured tank = new ModelSimpleCube();
        tank.setTexture(CARTS_FOLDER + textureName + ".png");
        tank.doBackFaceCulling(false);
        CartModelManager.modelsContents.put(cart, tank);
    }
}
