package secondderivative.irontankminecarts.minecarts.types;

import net.minecraft.world.World;

import com.indemnity83.irontank.reference.TankType;

import secondderivative.irontankminecarts.minecarts.EntityMinecartTankAbstract;

public class EntityMinecartDiamondTank extends EntityMinecartTankAbstract {

    private static TankType type = TankType.DIAMOND;

    public EntityMinecartDiamondTank(World world) {
        super(world, type);
    }

    public EntityMinecartDiamondTank(World world, double x, double y, double z) {
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
