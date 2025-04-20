package secondderivative.irontankminecarts;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import mods.railcraft.client.render.carts.CartModelManager;
import mods.railcraft.client.render.carts.RenderCartItemFiltered;
import mods.railcraft.client.render.models.ModelSimpleCube;
import mods.railcraft.client.render.models.ModelTextured;
import secondderivative.irontankminecarts.minecarts.EntityMinecartTankAbstract;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartAluminiumTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartCopperTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartDiamondTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartGoldTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartIronTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartObsidianTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartStainlessSteelTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartSteelTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartTitaniumTank;
import secondderivative.irontankminecarts.minecarts.types.EntityMinecartTungstensteelTank;

public class ClientProxy extends CommonProxy {

    private static String CARTS_FOLDER = "irontankminecarts:textures/entities/carts/";

    public void postInit(FMLPostInitializationEvent event) {
        for (Item cart : IronTankMinecarts.carts.values()) {
            MinecraftForgeClient
                .registerItemRenderer(cart, new RenderCartItemFiltered(RenderCartItemFiltered.RendererType.Tank));
        }
        addMinecartClassModel(EntityMinecartCopperTank.class, "copper");
        addMinecartClassModel(EntityMinecartIronTank.class, "iron");
        addMinecartClassModel(EntityMinecartGoldTank.class, "gold");
        addMinecartClassModel(EntityMinecartAluminiumTank.class, "aluminium");
        addMinecartClassModel(EntityMinecartDiamondTank.class, "diamond");
        addMinecartClassModel(EntityMinecartObsidianTank.class, "obsidian");
        addMinecartClassModel(EntityMinecartTitaniumTank.class, "titanium");
        addMinecartClassModel(EntityMinecartTungstensteelTank.class, "tungstensteel");
        addMinecartClassModel(EntityMinecartSteelTank.class, "steel");
        addMinecartClassModel(EntityMinecartStainlessSteelTank.class, "stainlesssteel");
    };

    private <T extends EntityMinecartTankAbstract> void addMinecartClassModel(Class<T> cart, String textureName) {
        ModelTextured tank = new ModelSimpleCube();
        tank.setTexture(CARTS_FOLDER + textureName + ".png");
        tank.doBackFaceCulling(false);
        CartModelManager.modelsContents.put(cart, tank);
    }
}
