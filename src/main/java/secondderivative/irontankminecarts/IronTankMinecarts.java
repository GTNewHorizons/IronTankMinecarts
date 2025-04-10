package secondderivative.irontankminecarts;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.indemnity83.irontank.reference.TankType;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import secondderivative.irontankminecarts.minecarts.ItemMinecartIronTank;

@Mod(modid = IronTankMinecarts.MODID, dependencies = IronTankMinecarts.DEPENDENCIES, name = IronTankMinecarts.MOD_NAME, acceptedMinecraftVersions = "[1.7.10]", version = IronTankMinecarts.VERSION)
public class IronTankMinecarts {

    public static final String MODID = "irontankminecarts";
    public static final String MOD_NAME = "Iron Tank Minecarts";
    public static final String DEPENDENCIES = "required-after:Forge@[10.10,);required-after:irontank";
    public static final String VERSION = "0.1.0";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @Instance(MODID)
    public static IronTankMinecarts instance;

    public static Map<TankType, Item> carts = new HashMap<TankType, Item>();

    @SidedProxy(clientSide = "secondderivative.irontankminecarts.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        for (TankType type: TankType.values()) {
            if (type == TankType.GLASS || type == TankType.OBSIDIAN) {
                continue;
            }
            String name = tankTypeName(type);
            Item minecart = new ItemMinecartIronTank(type)
                .setUnlocalizedName(MODID + ".minecart_tank_" + name)
                .setTextureName(MODID + ":minecart_tank_" + name);
            GameRegistry.registerItem(minecart, "minecart_tank_" + name);
            carts.put(type, minecart);
        }
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static String tankTypeName(TankType type) {
        return switch (type) {
            case EMERALD -> "aluminium";
            case SILVER -> "steel";
            default -> type.name().toLowerCase();
        };
    }
}
