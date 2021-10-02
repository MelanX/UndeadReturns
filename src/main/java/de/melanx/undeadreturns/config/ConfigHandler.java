package de.melanx.undeadreturns.config;

import io.github.noeppi_noeppi.libx.annotation.config.RegisterConfig;
import io.github.noeppi_noeppi.libx.config.Config;

@RegisterConfig
public class ConfigHandler {

    @Config("The radius where death mobs will check for an existing block")
    public static int extractionRange = 10;
}
