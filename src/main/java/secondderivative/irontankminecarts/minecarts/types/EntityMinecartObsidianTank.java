package secondderivative.irontankminecarts.minecarts.types;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.indemnity83.irontank.reference.TankType;

import secondderivative.irontankminecarts.minecarts.EntityMinecartTankAbstract;

public class EntityMinecartObsidianTank extends EntityMinecartTankAbstract {

    private static TankType type = TankType.OBSIDIAN;

    public EntityMinecartObsidianTank(World world) {
        super(world, type);
    }

    public EntityMinecartObsidianTank(World world, double x, double y, double z) {
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

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return !source.isExplosion() && super.attackEntityFrom(source, amount);
    }
}
