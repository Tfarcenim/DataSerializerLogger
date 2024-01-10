package tfar.dataserializerlogger;

import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.ForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = DataSerializerLogger.MOD_ID)
public class DataSerializerLogger {
    public static final String MOD_ID = "dataserializerlogger";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean REMAP = true;

    public static boolean logVanilla = false;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
    }

    public static void logSend(DataParameter<?> dataparameter, int i) {
        boolean modded = i >= 14;
        DataSerializer<?> dataSerializer = dataparameter.getSerializer();
        if (!modded) {
            if (logVanilla)
                DataSerializerLogger.LOGGER.info("writing dataparameter id " + dataparameter.getId() + " with serializer " + dataSerializer + " and id " + i);
        } else {
            DataSerializerEntry dataSerializerEntry = ((ForgeRegistry<DataSerializerEntry>) ForgeRegistries.DATA_SERIALIZERS).getValue(i);
            DataSerializerLogger.LOGGER.info("dataserializerentry is " + dataSerializerEntry.getRegistryName() + ", writing dataparameter id "
                    + dataparameter.getId() + " with serializer " + dataSerializer + " and id " + i);
        }
    }
}
