package ma.enset.gestionconsultationdbcc.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao <E,U>{
    void Create(E e) throws SQLException;
    void Update(E e) throws SQLException;
    void Delete(E e) throws SQLException;
    List<E> GetAll() throws SQLException;
    E GetById(U id) throws SQLException;
}
