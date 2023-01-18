package com.cursojava.curso.controller;

import com.cursojava.curso.dao.UsuarioDAO;
import com.cursojava.curso.model.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UsuarioDAO usuarioDAO;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario usuarioAuth = usuarioDAO.ObtenerUsuarioPorCredenciales(usuario);
        if (usuarioAuth != null) {
            String token = jwtUtil.create(String.valueOf(usuarioAuth.getId()), usuarioAuth.getEmail());
            return token;
        }
        return "FAIL";
    }
}