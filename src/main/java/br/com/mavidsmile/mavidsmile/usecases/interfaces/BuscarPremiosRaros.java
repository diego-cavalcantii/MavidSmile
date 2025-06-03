package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.PremioRaro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuscarPremiosRaros {
    List<PremioRaro> buscarTodos();
}
