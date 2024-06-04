package dh.backend.clinicamvc.dao;

import dh.backend.clinicamvc.model.Odontologo;

import java.util.List;

public interface IDao <T>{
    T registrar (T t);
    T buscarPorId(Integer id);
    List<T> buscarTodos();
    void actulizar(T t);
    void eliminar(Integer id);

}
