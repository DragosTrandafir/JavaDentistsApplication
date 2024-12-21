package repo;

import domain.Identifiable;

import java.io.FileNotFoundException;

public abstract class FileRepository<ID, T extends Identifiable<ID>> extends MemoryRepository<ID, T> {
    protected String fileName;

    public FileRepository(String fileName) throws ExceptionRepository{
        this.fileName = fileName;
        this.readFromFile();
    }
    abstract void readFromFile() throws ExceptionRepository;
    abstract void writeToFile() throws ExceptionRepository;

    @Override
    public void add(ID id, T object) throws ExceptionRepository {
        super.add(id, object);
        writeToFile();

    }
    @Override
    public void delete(ID id) throws ExceptionRepository {
        super.delete(id);
        writeToFile();
    }
    @Override
    public void modify(ID id, T object) throws ExceptionRepository{
        super.modify(id, object);
        writeToFile();
    }
}

