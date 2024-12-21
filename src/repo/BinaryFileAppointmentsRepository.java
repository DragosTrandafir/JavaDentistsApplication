package repo;

import domain.Appointment;

import java.io.*;

public class BinaryFileAppointmentsRepository extends FileRepository<Integer,Appointment> {


    public BinaryFileAppointmentsRepository(String fileName) throws ExceptionRepository {
        super(fileName);
    }

    @Override
    protected void writeToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            objectOutputStream.writeObject(this.objects);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void readFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName))) {
            this.objects = (java.util.HashMap<Integer, Appointment>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
