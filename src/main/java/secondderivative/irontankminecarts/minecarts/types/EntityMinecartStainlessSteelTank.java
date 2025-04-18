package secondderivative.irontankminecarts.minecarts.types;

import net.minecraft.world.World;

import com.indemnity83.irontank.reference.TankType;

import secondderivative.irontankminecarts.minecarts.EntityMinecartTankAbstract;

public class EntityMinecartStainlessSteelTank extends EntityMinecartTankAbstract {

    private static TankType type = TankType.STAINLESSSTEEL;

    public EntityMinecartStainlessSteelTank(World world) {
        super(world, type);
    }

    public EntityMinecartStainlessSteelTank(World world, double x, double y, double z) {
        super(world, x, y, z, type);
    }

    @Override
    public TankType tankType() {
        return type;
    }

    @Override
    public int getCapacity() {
        return EntityMinecartTankAbstract.getCapacity(type);
    }
}
