package br.com.sonner.estagio.servlet.endereco;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Test {

    public static void main(String[] args) {

        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            System.out.println(ip.getHostAddress());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }

    }

}