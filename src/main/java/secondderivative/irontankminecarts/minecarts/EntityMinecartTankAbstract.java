package secondderivative.irontankminecarts.minecarts;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.indemnity83.irontank.reference.TankType;

import cpw.mods.fml.common.registry.EntityRegistry;
import mods.railcraft.common.carts.EntityCartTank;
import mods.railcraft.common.core.RailcraftConfig;
import secondderivative.irontankminecarts.IronTankMinecarts;
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

public abstract class EntityMinecartTankAbstract extends EntityCartTank {

    public static final Map<TankType, Class<? extends EntityMinecartTankAbstract>> map = new HashMap<TankType, Class<? extends EntityMinecartTankAbstract>>();

    public EntityMinecartTankAbstract(World world, TankType type) {
        super(world);
    }

    public EntityMinecartTankAbstract(World world, double x, double y, double z, TankType type) {
        super(world, x, y, z);
    }

    public static int getCapacity(TankType type) {
        return RailcraftConfig.getTankCartCapacity() / (FluidContainerRegistry.BUCKET_VOLUME * 16)
            * 1000
            * type.capacity;
    }

    static {
        try {
            Class<?>[] carts = { EntityMinecartAluminiumTank.class, EntityMinecartCopperTank.class,
                EntityMinecartDiamondTank.class, EntityMinecartGoldTank.class, EntityMinecartIronTank.class,
                EntityMinecartObsidianTank.class, EntityMinecartStainlessSteelTank.class, EntityMinecartSteelTank.class,
                EntityMinecartTitaniumTank.class, EntityMinecartTungstensteelTank.class };
            for (Class<?> clazz : carts) {
                Class<? extends EntityMinecartTankAbstract> cartClass = (Class<? extends EntityMinecartTankAbstract>) clazz;
                TankType type = cartClass.getConstructor(World.class)
                    .newInstance((World) null)
                    .tankType();
                String rawname = IronTankMinecarts.tankTypeName(type);
                EntityRegistry.registerModEntity(
                    cartClass,
                    "minecart_tank_" + rawname,
                    type.ordinal(),
                    IronTankMinecarts.instance,
                    80,
                    3,
                    true);
                map.put(type, cartClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {}

    public ItemStack getCartItem() {
        Item minecart = IronTankMinecarts.carts.get(tankType());
        return new ItemStack(minecart != null ? minecart : Items.minecart);
    }

    public static EntityMinecartTankAbstract makeMinecart(World world, double x, double y, double z, TankType type) {
        try {
            Class<? extends EntityMinecartTankAbstract> cls = map.get(type);
            return cls.getConstructor(World.class, double.class, double.class, double.class)
                .newInstance(world, x, y, z);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
