package com.cursojava.curso.controller;

import com.cursojava.curso.dao.UsuarioDAO;
import com.cursojava.curso.model.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UsuarioDAO usuarioDAO;

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsarios(@RequestHeader(value = "Authorization") String token) {
        if (!validarToken(token))
            return new ArrayList<>();
        return usuarioDAO.getUsuarios();
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable long id) {
        if (!validarToken(token))
            return;
        usuarioDAO.eliminar(id);
    }
    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void agregar( @RequestBody Usuario usuario) {
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDAO.agregar(usuario);
    }
    private boolean validarToken(String token) {
        String idUsuario = jwtUtil.getKey(token);
        return (idUsuario != null);
    }

}