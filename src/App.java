import java.sql.Connection;
// //import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.Scanner;

import controller.DatabaseConnection;
//import controller.Risco;

public class App {
    public static void main(String[] args) throws Exception { 
        try (Scanner input = new Scanner(System.in)) {
            try {
                Connection connection = DatabaseConnection.getDatabaseConnection();                
                boolean exit = false;
                while (!exit) {
                    System.out.println("\nSelecione uma opcao:");
                    System.out.println("1. Retornar registros da tabela 'riscos'");
                    System.out.println("2. Adicionar registro a tabela 'riscos'");
                    System.out.println("3. Remover registro da tabela 'riscos'");
                    System.out.println("4. Retornar numero de registros da tabela 'riscos'");
                    System.out.print("5. Sair\n\n>> ");
                    int opcao = input.nextInt();
                    
                    if (opcao == 1){
                        visualizar(connection);
                    } else if (opcao == 2){
                        adicionar(connection, input);
                    } else if (opcao == 3){
                        remover(connection, input);
                    } else if (opcao == 4){
                        retornar (connection);
                    } else if (opcao == 5) {
                        System.out.println("Encerrando...");
                        exit = true;
                        break;
                    } else {                  
                        System.out.println("Opção inválida. Por favor, escolha novamente.");
                        input.nextLine();
                    }
                }
    //             connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } 
 
    private static void visualizar (Connection connection) throws SQLException{
        String query = "SELECT * FROM riscos";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            
                System.out.println("\nRegistros da tabela 'riscos':\n");

                boolean hasRecords = false;

                while (resultSet.next()){
                    int codigo = resultSet.getInt("codigo");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    // Processar os dados
                    System.out.println("Codigo: " + codigo + "\nNome: " + nome + "\nDescricao: " + descricao + "\n");
                    hasRecords = true;
                }

                if (!hasRecords) {
                    System.out.println("Nenhum registro encontrado na tabela 'riscos'.\n");
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   
    
    private static void adicionar (Connection connection, Scanner input) throws SQLException{
        try{
            String continuar = "s";
            input.nextLine(); // Consumir a nova linha deixada por input.nextInt()

            while (continuar.equals("s")){
                System.out.print("\nDigite o NOME do novo risco:\n>> ");
                String nome = input.nextLine(); // Use input.nextLine() para capturar toda a linha

                System.out.print("Digite a DESCRICAO do novo risco:\n>> ");
                String descricao = input.nextLine();

                System.out.print("Digite o CODIGO do novo risco:\n>> ");
                int codigo = input.nextInt();
                
                // Query para inserir um novo registro
                String insertQuery = "INSERT INTO riscos (Codigo, Nome, Descricao) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, codigo);
                    preparedStatement.setString(2, nome);
                    preparedStatement.setString(3, descricao);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Novo registro de risco inserido com sucesso!\n");
                        System.out.print("Adicionar outro registro?('s' para continuar)\n>> ");
                        continuar = input.next();
                        input.nextLine();
                        }
                    else {
                        System.out.println("Falha ao inserir o registro de risco.\n");
                    }
                }
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: Não foi possível adicionar o registro de risco. O código já existe no banco de dados.");   
        }
    }
    
    private static void remover (Connection connection, Scanner input) throws SQLException{
        String continuar = "s";
        input.nextLine(); // Consumir a nova linha deixada por input.nextInt()

        while (continuar.equals("s")){
            System.out.print("\nDigite o CODIGO do risco que deseja remover:\n>>");
            int codigo = input.nextInt();

            // Query para remover um registro
            String deleteQuery = "DELETE FROM riscos WHERE codigo = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, codigo);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Registro de risco removido com sucesso!\n");
                    System.out.print("Remover outro registro?('s' para continuar)\n>>");
                    continuar = input.next();
                } else {
                    System.out.println("Nenhum registro correspondente encontrado ou falha na remoção.");
                }
            }
        }

    }
    
    private static void retornar (Connection connection) throws SQLException{
        String contQuery = "SELECT COUNT(*) AS total FROM riscos";

        try (PreparedStatement preparedStatement = connection.prepareStatement(contQuery);
            ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int Cont = resultSet.getInt("total");
                    System.out.println("\nTotal de registros na tabela 'riscos': " + Cont);
                } else {
                    System.out.println("Nenhum registro encontrado na tabela 'riscos'.");
                }
         } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}