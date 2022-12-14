package com.virus;

import com.virus.Listener.*;
import com.virus.Runnable.DropItem;
import com.virus.SQL.teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public final class Main extends JavaPlugin {

    BukkitTask task = null;
    BukkitTask task2 = null;
    BukkitTask task3 = null;
    BukkitTask task4 = null;
    public static Main instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        // Plugin startup logic
        getLogger().info("\n _    ___                 ____  __            _     \n" +
                "| |  / (_)______  _______/ __ \\/ /_  ______ _(_)___ \n" +
                "| | / / / ___/ / / / ___/ /_/ / / / / / __ `/ / __ \\\n" +
                "| |/ / / /  / /_/ (__  ) ____/ / /_/ / /_/ / / / / /\n" +
                "|___/_/_/   \\__,_/____/_/   /_/\\__,_/\\__, /_/_/ /_/ \n" +
                "                                    /____/          \n");
        getServer().getPluginManager().registerEvents(new playerKillListener(), this);
//        getServer().getPluginManager().registerEvents(new playerGamemode(), this);
        getServer().getPluginManager().registerEvents(new playerLeft(), this);
        getServer().getPluginManager().registerEvents(new PlayerInfo(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
//        getServer().getPluginManager().registerEvents(new WorldWeather(), this);
        getServer().getPluginManager().registerEvents(new PlayerLogin(),this);

        new BukkitRunnable() {
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                String timeM = "2022-05-08 00:00:00";

            }
        };
    }
    /**
     * ???????????????
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("show")) {
            try {
                Player p = (Player) sender;
                String PlayerName = p.getName();
                int lv = p.getLevel();
                UUID UUID = p.getUniqueId();
                //ShowPlayer
                sender.sendMessage(ChatColor.GREEN + "[<==============[ShowPlayerInfo]==============>]");
                sender.sendMessage(ChatColor.GREEN + "|??????: " + PlayerName);
                sender.sendMessage(ChatColor.GREEN + "|??????: " + lv);
                sender.sendMessage(ChatColor.GREEN + "|UUID: " + UUID);
                sender.sendMessage(ChatColor.GREEN + "[<==========================================>]");
                //getLogger
                getLogger().info(ChatColor.GREEN + "[===============[ShowPlayerInfo]===============]");
                getLogger().info(ChatColor.GREEN + "|??????: " + PlayerName);
                getLogger().info(ChatColor.GREEN + "|??????: " + lv);
                getLogger().info(ChatColor.GREEN + "|UUID: " + UUID);
                getLogger().info(ChatColor.GREEN + "[============================================]");
            } catch (Exception e) {
                getLogger().info("??????????????????????????????");
            }

        }
        if (command.getName().equalsIgnoreCase("ClearChat")) {
            try {
                Player player = (Player) sender;
                for (int i = 0; i < 20; i++) {
                    sender.sendMessage("\n");
                }
            } catch (Exception e) {
                getLogger().info("??????????????????????????????");
            }
        }
        if (command.getName().equalsIgnoreCase("bk")) {
            Player p = (Player) sender;
            teleport impl = new teleport();
            try {
                impl.tel(p);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                getLogger().info("??????????????????????????????");
            }
        }
        if (command.getName().equalsIgnoreCase("clearItemOn")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                task2 = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.AQUA + "[??????]" + ChatColor.WHITE + "??????" + ChatColor.DARK_GREEN + "1??????" + ChatColor.WHITE + "????????????");
                    }
                }.runTaskTimer(this, 34800L, 36000L);
                task3 = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.AQUA + "[??????]" + ChatColor.WHITE + "??????" + ChatColor.DARK_PURPLE + "15???" + ChatColor.WHITE + "????????????");
                    }
                }.runTaskTimer(this, 35700L, 36000L);
                task4 = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.AQUA + "[??????]" + ChatColor.WHITE + "??????" + ChatColor.RED + "5???" + ChatColor.WHITE + "????????????");
                    }
                }.runTaskTimer(this, 35900L, 36000L);
                /**
                 * ???????????????????????????
                 */
                System.out.println("??????????????????????????????");
                player.sendMessage("???????????????????????????!");
                task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        DropItem item = new DropItem();
                        item.DropItems();
                    }
                }.runTaskTimer(this, 36000L, 36000L);
            } else {
                player.sendMessage(ChatColor.RED + "????????????????????????");
            }

        }
        if (command.getName().equalsIgnoreCase("clearItemOff")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                //???????????????????????????
                task.cancel();
                task2.cancel();
                task3.cancel();
                task4.cancel();
                if (task.isCancelled() && task2.isCancelled() && task3.isCancelled() && task4.isCancelled()) {
                    System.out.println("??????????????????????????????");
                } else {
                    player.sendMessage("????????????????????????");
                    System.out.println("????????????????????????");
                }

            } else {
                player.sendMessage(ChatColor.RED + "????????????????????????");
            }
        }
        if (command.getName().equalsIgnoreCase("reloadVirus")) {
            Player player = (Player) sender;
            if (player.isOp()) {
                Bukkit.reload();
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        task.cancel();
        System.out.println(ChatColor.RED + "?????????????????????????????????");
        System.out.println(ChatColor.RED + "???????????????");
    }
}
