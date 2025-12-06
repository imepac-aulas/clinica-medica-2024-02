package br.edu.imepac.agendamento.services;

import br.edu.imepac.agendamento.exceptions.ResourceNotFoundException;
import br.edu.imepac.agendamento.repositories.MedicoRepository;
import br.edu.imepac.common.entidades.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    // ================= READ =================
    @Transactional(readOnly = true)
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    // ================= CREATE =================
    @Transactional
    public Medico criarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // ================= UPDATE =================
    @Transactional
    public Medico atualizarMedico(Long id, Medico medicoAtualizado) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));

        medicoExistente.setNome(medicoAtualizado.getNome());
        medicoExistente.setCrm(medicoAtualizado.getCrm());
        medicoExistente.setEspecialidade(medicoAtualizado.getEspecialidade());

        return medicoRepository.save(medicoExistente);
    }

    // ================= DELETE =================
    @Transactional
    public void deletarMedico(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
        medicoRepository.delete(medico);
    }
}
