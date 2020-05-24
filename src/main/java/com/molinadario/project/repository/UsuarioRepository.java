package com.molinadario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.molinadario.project.entity.Usuarios;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

	public Usuarios findByUsername(String name);
}
