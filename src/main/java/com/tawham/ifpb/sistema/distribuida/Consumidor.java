package com.tawham.ifpb.sistema.distribuida;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumidor {
    public static void main(String[] args) throws Exception {
        String NOME_FILA = "filaOla";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("mqadmin");
        connectionFactory.setPassword("Admin123XX_");
        Connection conexao = connectionFactory.newConnection();
        Channel canal = conexao.createChannel();
        canal.queueDeclare(NOME_FILA, false, false, false, null);
        DeliverCallback callback = (consumerTag, delivery) -> {
            String mensagem = new String(delivery.getBody());
            System.out.println("Recebida a Mensagem: " + mensagem);
        };
        canal.basicConsume(NOME_FILA, true, callback, consumerTag -> {});
        System.out.println("Continuarei executando outras atividades enquanto não chega mensagem...");

    }
}
