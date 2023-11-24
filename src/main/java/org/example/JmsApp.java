package org.example;

import org.example.jms.MessageUtil;

import java.util.Scanner;


public class JmsApp

{

    public static void main( String[] args ) throws InterruptedException {

//        Input message content

        String input = "test jms";

        MessageUtil.sendMessage( input );
        MessageUtil.receiveMessages( );


    }

}
