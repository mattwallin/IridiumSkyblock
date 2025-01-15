package com.iridium.iridiumskyblock.enhancements;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumcore.utils.Placeholder;
import com.iridium.iridiumteams.enhancements.EnhancementData;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class RedstoneEnhancementData extends EnhancementData {
    public int redstone;
    public static List<XMaterial> redstoneList = Arrays.asList(
        XMaterial.REPEATER,
        XMaterial.COMPARATOR,
        XMaterial.PISTON,
        XMaterial.STICKY_PISTON,
        XMaterial.HOPPER,
        XMaterial.DROPPER,
        XMaterial.DISPENSER,
        XMaterial.OBSERVER,
        XMaterial.CRAFTER  
    );
    
    public RedstoneEnhancementData(int minLevel, int money, Map<String, Double> bankCosts, int redstone) {
        super(minLevel, money, bankCosts);
        this.redstone = redstone;
    }

    @Override
    public List<Placeholder> getPlaceholders() {
        return Arrays.asList(
            new Placeholder("redstone", String.valueOf(redstone))
        );
    }

    public static List<XMaterial> getRedstoneList() {
        return redstoneList;
    }
}
