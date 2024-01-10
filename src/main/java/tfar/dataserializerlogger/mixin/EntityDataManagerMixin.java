package tfar.dataserializerlogger.mixin;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import tfar.dataserializerlogger.DataSerializerLogger;

@Mixin(EntityDataManager.class)
public abstract class EntityDataManagerMixin {

    /*@Inject(method = "readEntries",at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/network/datasync/DataSerializers;getSerializer(I)Lnet/minecraft/network/datasync/DataSerializer;",remap = DataSerializerLogger.REMAP),
            locals = LocalCapture.CAPTURE_FAILHARD,remap = DataSerializerLogger.REMAP)
    private static void logSerializer(PacketBuffer buf, CallbackInfoReturnable<List<EntityDataManager.DataEntry<?>>> cir, List<EntityDataManager.DataEntry<?>> list,
                                      int i, int j, DataSerializer dataserializer) {
        if (dataserializer == null) {
        }
    }*/

    @Inject(method = "writeEntry",at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/network/datasync/DataSerializers;getSerializerId(Lnet/minecraft/network/datasync/DataSerializer;)I",remap = DataSerializerLogger.REMAP),
            locals = LocalCapture.CAPTURE_FAILHARD,remap = DataSerializerLogger.REMAP)
    private static void logSerializer(PacketBuffer buf, EntityDataManager.DataEntry<?> entry, CallbackInfo ci, DataParameter<?> dataparameter,int i) {
        DataSerializerLogger.logSend(dataparameter,i);
    }
}
