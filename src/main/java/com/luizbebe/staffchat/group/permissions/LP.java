package com.luizbebe.staffchat.group.permissions;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.data.Settings;
import com.luizbebe.staffchat.group.Group;
import lombok.val;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LP implements Group {

    private final Settings settings;

    public LP(Main main) {
        settings = main.getSettings();

        Bukkit.getConsoleSender().sendMessage("§b[" + main.getName() + "] §fHooked §bLuckPerms");
    }

    @Override
    public String getGroup(Player player) {
        val user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        val metaData = user.getCachedData().getMetaData();
        val prefix = metaData.getPrefix();

        return prefix == null ? settings.getPrefix() : prefix.replace("&", "§");
    }

}
