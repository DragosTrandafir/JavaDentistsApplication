package repo;

import domain.Dentist;

import java.io.*;

public class BinaryFileDentistsRepository extends FileRepository<Integer,Dentist> {

    public BinaryFileDentistsRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void writeToFile()  {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            //for(Dentist dentist: super.getAll())
                objectOutputStream.writeObject(this.objects);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void readFromFile()  {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName))) {
            this.objects = (java.util.HashMap<Integer, Dentist>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    }
