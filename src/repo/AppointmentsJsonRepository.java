package repo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Appointment;


import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class AppointmentsJsonRepository extends FileRepository<Integer, Appointment> {
    public AppointmentsJsonRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ExceptionRepository {
        try(FileReader fileReader = new FileReader(this.fileName)) {
            Gson gsonObjectConverter = new Gson();

            Type listType = new TypeToken<List<Appointment>>() {}.getType();  // create the type of the json token
            List<Appointment> appointments = gsonObjectConverter.fromJson(fileReader, listType); //deserilize the entire JSON array into a list of Appointments objects

            for (Appointment appointment : appointments) {
                    super.add(appointment.getId(), appointment);

            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile()  {
        try(FileWriter fileWriter = new FileWriter(this.fileName)) {
            Gson gsonObjectConverter = new Gson();

            gsonObjectConverter.toJson(super.getAll(), fileWriter);

            //fileWriter.close(); try with resources automatically closes fileWriter
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
