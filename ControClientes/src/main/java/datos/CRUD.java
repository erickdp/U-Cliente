package datos;

import java.util.List;

public interface CRUD<T> {

    int create(T dtoObject);

    List<T> read();

    int update(T dtoObject);

    int delete(T dtoObject);

    T readByIdentifier(T dtoObject);
}
