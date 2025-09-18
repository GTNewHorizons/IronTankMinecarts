package secondderivative.irontankminecarts;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.indemnity83.irontank.reference.Reference;
import com.indemnity83.irontank.reference.TankType;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import secondderivative.irontankminecarts.minecarts.EntityMinecartTankAbstract;
import secondderivative.irontankminecarts.minecarts.ItemMinecartIronTank;

@Mod(modid = IronTankMinecarts.MODID, dependencies = IronTankMinecarts.DEPENDENCIES, name = IronTankMinecarts.MOD_NAME, version = IronTankMinecarts.VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class IronTankMinecarts {

    public static final String MODID = "irontankminecarts";
    public static final String MOD_NAME = "Iron Tank Minecarts";
    public static final String VERSION = ITMCTags.VERSION;
    public static final String DEPENDENCIES = "required-after:Forge@[10.10,);required-after:irontank";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Instance(MODID)
    public static IronTankMinecarts instance;

    public static Map<TankType, Item> carts = new HashMap<TankType, Item>();

    @SidedProxy(clientSide = "secondderivative.irontankminecarts.ClientProxy", serverSide = "secondderivative.irontankminecarts.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        for (TankType type : TankType.values()) {
            if (type == TankType.GLASS) {
                continue;
            }
            String name = tankTypeName(type);
            Item minecart = new ItemMinecartIronTank(type).setUnlocalizedName(MODID + ".minecart_tank_" + name)
                    .setTextureName(MODID + ":minecart_tank_" + name);
            GameRegistry.registerItem(minecart, "minecart_tank_" + name);
            carts.put(type, minecart);

            GameRegistry.addShapedRecipe(
                    new ItemStack(minecart),
                    "T",
                    "M",
                    'M',
                    new ItemStack(Items.minecart),
                    'T',
                    GameRegistry.findBlock(Reference.MODID, type.name));
        }
        EntityMinecartTankAbstract.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static String tankTypeName(TankType type) {
        return switch (type) {
            case EMERALD -> "aluminium";
            case SILVER -> "steel";
            default -> type.name()
                    .toLowerCase();
        };
    }
}
