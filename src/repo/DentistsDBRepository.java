package repo;

import domain.Dentist;

import java.sql.*;

public class DentistsDBRepository extends DentistsRepository {
    public String databaseName;
    public DentistsDBRepository(String databaseName) throws ExceptionRepository {
        this.databaseName = databaseName;

        try(Connection dentistsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName);)
        {
            PreparedStatement statement = dentistsDBConnection.prepareStatement("SELECT * FROM dentists");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Integer age = resultSet.getInt(3);
                Dentist dentist = new Dentist(id, name, age);
                super.add(id,dentist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void add(Integer id, Dentist dentist) throws ExceptionRepository {

        super.add(id,dentist);

        try(Connection dentistsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = dentistsDBConnection.prepareStatement("INSERT INTO dentists VALUES (?, ?, ?);");

            preparedStatement.setInt(1, dentist.getId());
            preparedStatement.setString(2, dentist.getName());
            preparedStatement.setInt(3, dentist.getAge());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modify(Integer id, Dentist dentist) throws ExceptionRepository {

        super.modify(id,dentist);

        try(Connection dentistsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = dentistsDBConnection.prepareStatement("UPDATE dentists SET id = ?, name = ?, age=? WHERE id = ?");

            preparedStatement.setInt(1, dentist.getId());
            preparedStatement.setString(2, dentist.getName());
            preparedStatement.setInt(3, dentist.getAge());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Integer id) throws ExceptionRepository {

        super.delete(id);

        try(Connection dentistsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = dentistsDBConnection.prepareStatement("DELETE FROM dentists WHERE id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
