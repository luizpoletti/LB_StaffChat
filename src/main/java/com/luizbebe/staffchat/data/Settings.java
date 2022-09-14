package com.luizbebe.staffchat.data;

import lombok.Data;
import org.bukkit.Sound;

@Data
public class Settings {

    private final Messages messages;
    private final String prefix;
    private final String format;
    private final Sound sound;
    private final boolean highlightWithPerm;
    private final boolean removeOnExit;
    private final boolean sendSound;

}
