package com.cursojava.curso.dao;

import com.cursojava.curso.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    public List<Usuario> getUsuarios();

    void eliminar(Long id);

    void agregar(Usuario usuario);

    Usuario ObtenerUsuarioPorCredenciales(Usuario usuario);
}