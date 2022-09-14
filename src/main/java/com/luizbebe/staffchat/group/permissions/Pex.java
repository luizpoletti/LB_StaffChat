package com.luizbebe.staffchat.group.permissions;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.data.Settings;
import com.luizbebe.staffchat.group.Group;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Pex implements Group {

    private final Settings settings;

    public Pex(Main main) {
        settings = main.getSettings();

        Bukkit.getConsoleSender().sendMessage("§b[" + main.getName() + "] §fHooked §bPermissionsEx");
    }

    @Override
    public String getGroup(Player player) {
        val user = PermissionsEx.getUser(player);
        val prefix = user.getPrefix();

        return prefix == null || prefix.equals("") ? settings.getPrefix() : prefix.replace("&", "§");
    }

}
