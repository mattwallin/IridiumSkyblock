package com.iridium.iridiumskyblock.listeners;

import java.net.http.WebSocket.Listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlockPlaceListener implements Listener, org.bukkit.event.Listener {
    
    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(event.getPlayer());
        XMaterial material = XMaterial.matchXMaterial(event.getBlock().getType());
        
        try {
            Island island = user.getCurrentIsland().get();
            if (island.isRedstone(material)) {
                island.incrementRedstone();
            }
        } catch (Exception e) {
            Bukkit.broadcastMessage("No Island");
            return;
        }
    }

}
