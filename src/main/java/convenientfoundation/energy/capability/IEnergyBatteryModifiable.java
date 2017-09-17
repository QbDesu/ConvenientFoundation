package convenientfoundation.energy.capability;

import convenientfoundation.energy.EnergyStack;

public interface IEnergyBatteryModifiable extends IEnergyBattery
{
    void setEnergy(EnergyStack stack);
    void setEnergyAmount(int amount);
    void setCapacity(int capacity);
}