package com.estacionamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estacionamento.model.Usuario;
import com.estacionamento.repository.UsuarioRepository;
import com.estacionamento.security.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getPerfis());
	}
}