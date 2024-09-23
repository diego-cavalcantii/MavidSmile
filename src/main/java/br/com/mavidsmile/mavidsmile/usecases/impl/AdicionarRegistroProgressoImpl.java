package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ProgressoRepository;
import br.com.mavidsmile.mavidsmile.usecases.AdicionarRegistroProgresso;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarRegistroProgressoImpl implements AdicionarRegistroProgresso {

    private final ProgressoRepository progressoRepository;
    private final ClienteRepository clienteRepository;
    private final BuscarClientes buscarClientes;

    @Override
    public void adicionarRegistro(String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);// Encontra o cliente

        if(cliente.getProgresso() == null) {
            Progresso novoProgresso = Progresso.builder().registros(1).build();// Cria um novo progresso
            progressoRepository.save(novoProgresso);// Salva o progresso no banco de dados
            cliente.setProgresso(novoProgresso);// Associa o progresso ao cliente
            clienteRepository.save(cliente);// Salva o cliente no banco de dados
        }

        if(cliente.getProgresso() != null) {
            Progresso progressoCliente = cliente.getProgresso();// Pega o progresso do cliente
            progressoCliente.setRegistros(progressoCliente.getRegistros() + 1);// Incrementa o número de registros
            progressoRepository.save(progressoCliente);// Salva o progresso no banco de dados
        }
    }
}
