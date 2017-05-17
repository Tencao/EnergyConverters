package net.xalcon.energyconverters.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import reborncore.api.power.EnumPowerTier;
import reborncore.api.power.IEnergyInterfaceTile;

public class TileEntityTechRebornConsumer extends TileEntityConverterBase implements IEnergyInterfaceTile
{
	private EnumPowerTier tier;

	public TileEntityTechRebornConsumer()
	{
	}

	public TileEntityTechRebornConsumer(EnumPowerTier tier)
	{
		this.tier = tier;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.tier = EnumPowerTier.values()[compound.getInteger("tier")];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("tier", this.tier.ordinal());
		return super.writeToNBT(compound);
	}

	@Override
	public double getEnergy()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergy();
	}

	@Override
	public void setEnergy(double v) {}

	@Override
	public double getMaxPower()
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.getStoredEnergyMax();
	}

	@Override
	public boolean canAddEnergy(double v)
	{
		return true;
	}

	@Override
	public double addEnergy(double v)
	{
		return this.addEnergy(v, false);
	}

	@Override
	public double addEnergy(double v, boolean simulate)
	{
		TileEntityEnergyBridge bridge = this.getEnergyBridge();
		return bridge == null ? 0 : bridge.addEnergy(v, simulate);
	}

	@Override
	public boolean canUseEnergy(double v)
	{
		return false;
	}

	@Override
	public double useEnergy(double v)
	{
		return 0;
	}

	@Override
	public double useEnergy(double v, boolean b)
	{
		return 0;
	}

	@Override
	public boolean canAcceptEnergy(EnumFacing enumFacing)
	{
		return true;
	}

	@Override
	public boolean canProvideEnergy(EnumFacing enumFacing)
	{
		return false;
	}

	@Override
	public double getMaxOutput()
	{
		return 0;
	}

	@Override
	public double getMaxInput()
	{
		return this.tier.getMaxInput();
	}

	@Override
	public EnumPowerTier getTier()
	{
		return this.tier;
	}
}
