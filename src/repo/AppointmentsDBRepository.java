package repo;

import domain.Appointment;

import java.sql.*;

public class AppointmentsDBRepository extends AppointmentsRepository {
    public String databaseName;

    public AppointmentsDBRepository(String databaseName) throws ExceptionRepository {
        this.databaseName = databaseName;
        try(Connection appointmentsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName);)
        {
            PreparedStatement preparedStatement = appointmentsDBConnection.prepareStatement("SELECT * FROM appointments");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt(1);
                String patientName = resultSet.getString(2);
                String doctorName = resultSet.getString(3);
                String hour = resultSet.getString(4);
                String date = resultSet.getString(5);
                Appointment appointment = new Appointment(id, patientName, doctorName, hour, date);
                super.add(id,appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Integer id, Appointment appointment) throws ExceptionRepository {

        super.add(id,appointment);

        try(Connection appointmentsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = appointmentsDBConnection.prepareStatement("INSERT INTO appointments VALUES (?, ?, ?,?,?);");

            preparedStatement.setInt(1, appointment.getId());
            preparedStatement.setString(2, appointment.getPatientName());
            preparedStatement.setString(3, appointment.getDoctorName());
            preparedStatement.setString(4, appointment.getHour());
            preparedStatement.setString(5, appointment.getDate());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modify(Integer id, Appointment appointment) throws ExceptionRepository {

        super.modify(id,appointment);

        try(Connection appointmentsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = appointmentsDBConnection.prepareStatement("UPDATE appointments SET id = ?, patientName = ?, doctorName = ?, hour = ?, date = ? WHERE id = ?");

            preparedStatement.setInt(1, appointment.getId());
            preparedStatement.setString(2, appointment.getPatientName());
            preparedStatement.setString(3, appointment.getDoctorName());
            preparedStatement.setString(4, appointment.getHour());
            preparedStatement.setString(5, appointment.getDate());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Integer id) throws ExceptionRepository {

        super.delete(id);

        try(Connection appointmentsDBConnection = DriverManager.getConnection("jdbc:sqlite:"+this.databaseName))
        {
            PreparedStatement preparedStatement = appointmentsDBConnection.prepareStatement("DELETE FROM appointments WHERE id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }
