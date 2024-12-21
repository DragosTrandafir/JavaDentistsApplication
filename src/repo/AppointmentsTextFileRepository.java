package repo;
import domain.Appointment;

import java.io.*;

public class AppointmentsTextFileRepository extends FileRepository<Integer, Appointment> {
    public AppointmentsTextFileRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ExceptionRepository {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = bufferedReader.readLine())!= null){
                String[] tokens = line.split(",");
                if(tokens.length != 5)
                    continue;
                Integer id = Integer.parseInt(tokens[0]);
                String patientName = tokens[1];
                String doctorName = tokens[2];
                String hour = tokens[3];
                String date = tokens[4];
                Appointment appointment = new Appointment(id, patientName, doctorName, hour, date);
                super.add(id, appointment);
            }
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void writeToFile() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileName))){
            for(Appointment appointment: super.getAll()){
                bufferedWriter.write(appointment.getId() + ","+ appointment.getPatientName() + ","+ appointment.getDoctorName()+","+appointment.getHour()+","+appointment.getDate()+"\n");
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}