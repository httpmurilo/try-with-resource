package io.murilo.store;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        imprimirUsandoTryWithResources();
        imprimirUsandoTry();
    }

    private static void imprimirUsandoTryWithResources() {

        //MAIS LEGÍVEL, DENTRO DO TRY É FORNECIDO O OBJETO
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://172.106.0.118:3306/sample?user=anakimskywalker&password=anakimskywalker123");

             PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente");) {
             ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    imprimirResultados(resultSet);
                }

            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirUsandoTry() throws SQLException {

        //É CRIADO OS OBJETOS FORA DO BLOCO TRY
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://172.106.0.118:3306/sample?user=anakimskywalker&password=anakimskywalker123");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                imprimirResultados(resultSet);
            }

            //PRECISAMOS FECHAR OS OBJETOS DE FORMA MANUAL
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirResultados(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String profissao = resultSet.getString("profissao");
        System.out.println("id: " + id + "nome: " + nome + "profissao: " + profissao);
    }

}
