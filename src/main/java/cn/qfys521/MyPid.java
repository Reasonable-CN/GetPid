package cn.qfys521;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.management.ManagementFactory;

@SuppressWarnings("all")
public final class MyPid extends JavaPlugin {
    String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    Long pid = Long.valueOf(jvmName.split("@")[0]);

    FileConfiguration config;
    @Override
    public void onEnable() {
        FileConfiguration config = this.getConfig();
        this.config = config;
        saveDefaultConfig();
        config.set("pid", pid);
        saveConfig();
        // Plugin startup logic
        getLogger().info("插件启动中...");
        getLogger().info("作者: qfys521");
        getLogger().info("your pid is: " + pid);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件卸载中...");
        getLogger().info("感谢使用");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("getPid") && sender.isOp() && sender.hasPermission("getid")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("your pid is: " + pid);
                config.set("pid", pid);
                saveConfig();
            } else {
                sender.sendMessage("your pid is: " + pid);
                config.set("pid", pid);
                saveConfig();
            }
            return true;
        }
        return false;
    }
}
