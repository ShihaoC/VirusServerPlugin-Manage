package com.virus.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.net.InetAddress;

public class PlayerLogin implements Listener {
    @EventHandler
    public void LoginIn(AsyncPlayerPreLoginEvent e){
        InetAddress address = e.getAddress();
        String s = address.getAddress().toString();
        System.out.println(s);
    }
}
