package com.luizbebe.staffchat.setup;

import com.luizbebe.staffchat.Main;
import com.luizbebe.staffchat.data.Messages;
import com.luizbebe.staffchat.data.Settings;
import lombok.val;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class SettingsSetup {

    private final FileConfiguration config;

    public SettingsSetup(Main main) {
        config = main.getConfig();
    }

    public Settings setup() {
        val noArgs = config.getString("Messages.No-Args").replace("&", "§");
        val join = config.getString("Messages.Join").replace("&", "§");
        val quit = config.getString("Messages.Quit").replace("&", "§");

        val format = config.getString("Format").replace("&", "§");
        val prefix = config.getString("Prefix").replace("&", "§");
        val sound = Sound.valueOf(config.getString("Send-Sound.Sound").toUpperCase());

        val highlightWithPerm = config.getBoolean("Highlight-With-Perm");
        val removeOnExit = config.getBoolean("Remove-On-Exit");
        val sendSound = config.getBoolean("Send-Sound.Enable");

        val messages = new Messages(noArgs, join, quit);

        return new Settings(messages, prefix, format, sound, highlightWithPerm, removeOnExit, sendSound);
    }

}
