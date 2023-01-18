package com.cursojava.curso.dao;

import com.cursojava.curso.model.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDAOImp implements UsuarioDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void agregar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario ObtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";

        List<Usuario> lista = entityManager
                .createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (!lista.isEmpty())
            return argon.verify(lista.get(0).getPassword(), usuario.getPassword()) ? lista.get(0) : null;

        else
            return null;
    }
}