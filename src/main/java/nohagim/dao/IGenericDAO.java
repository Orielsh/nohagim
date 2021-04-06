package nohagim.dao;

import nohagim.Entities.Entity;

import java.util.List;

public interface IGenericDAO<T extends Entity> {
    boolean save(T t);

    List<T> getListOfRecords(Class<T> type);
}

