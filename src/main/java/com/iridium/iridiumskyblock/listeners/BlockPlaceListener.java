package com.iridium.iridiumskyblock.listeners;

import org.bukkit.event.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumcore.utils.StringUtils;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumskyblock.enhancements.RedstoneEnhancementData;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlockPlaceListener implements Listener {
    
    @EventHandler(ignoreCancelled = false)
    public void onBlockPlace(BlockPlaceEvent event) {
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(event.getPlayer());
        XMaterial material = XMaterial.matchXMaterial(event.getBlock().getType());
        try {
            Island island = user.getCurrentIsland().get();
            int maxRedstone = IridiumSkyblock.getInstance().getTeamManager().getTeamEnhancement(island, "redstone").getLevel();
            RedstoneEnhancementData redstoneEnhancementData = IridiumSkyblock.getInstance().getEnhancements().redstoneEnhancement.levels.get(maxRedstone);
            if (island.isRedstone(material)) {
                island.incrementRedstone();
                if (island.getRedStone() > redstoneEnhancementData.getRedstone()) {
                    event.getPlayer().sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().hitRedstoneLimit
                            .replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)
                    ));
                    island.decrementRedstone();
                    event.setCancelled(true);                    
                }
            }
        } catch (Exception e) {
            return;
        }
    }

}
