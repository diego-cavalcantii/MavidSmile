package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Premio;

import java.util.List;

public interface BuscarNiveis {

    List<Nivel> buscarTodosOsNiveis();
}
