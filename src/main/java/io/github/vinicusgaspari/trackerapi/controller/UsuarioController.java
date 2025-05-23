package io.github.vinicusgaspari.trackerapi.controller;

import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioRequest;
import io.github.vinicusgaspari.trackerapi.controller.mapper.UsuarioMapper;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.PesquisasPorUsuarioResponse;
import io.github.vinicusgaspari.trackerapi.controller.entrypoint.usuario.UsuarioResponse;
import io.github.vinicusgaspari.trackerapi.model.Usuario;
import io.github.vinicusgaspari.trackerapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@Slf4j
@Tag(name = "Usuário")
@RequiredArgsConstructor
public class UsuarioController implements GenericController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PostMapping
    @Operation(
            summary = "Cadastrar novo usuário",
            description = "Cria um novo usuário na base de dados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado"),
            @ApiResponse(responseCode = "422", description = "Erro de validação nos dados")
    })
    public ResponseEntity<UsuarioResponse> salvar(@RequestBody(required = true) @Valid UsuarioRequest response) {
        Usuario usuario = service.salvar(mapper.toEntity(response));
        return ResponseEntity.created(generatorHeaderLocation(usuario.getId())).body(mapper.toDTO(usuario));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário pelo seu identificador único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UsuarioResponse> buscar(
            @Parameter(description = "UUID do Usuário", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        return ResponseEntity.ok(mapper.toDTO(service.buscarPorId(id)));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário do sistema pelo identificador único."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Formato inválido do ID"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "UUID do Usuário", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza um usuário existente na base de dados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UsuarioResponse> atualizar(
            @Parameter(description = "UUID do Usuário", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id,
            @RequestBody(required = true) UsuarioRequest response) {
        return ResponseEntity.ok(mapper.toDTO(service.atualizar(id, mapper.toEntity(response))));
    }

    @PreAuthorize("hasRole('ADMIN', 'ASSISTENTE')")
    @GetMapping
    @Operation(
            summary = "Buscar usuários por filtros",
            description = "Retorna usuários com base em critérios opcionais."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários encontrada")
    })
    public ResponseEntity<Page<PesquisasPorUsuarioResponse>> buscarComFiltro(
            @Parameter(description = "UUID", example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestParam(value = "id", required = false) UUID id,

            @Parameter(description = "Nome", example = "João Silva")
            @RequestParam(value = "nome", required = false) String nome,

            @Parameter(description = "Cidade", example = "São Paulo")
            @RequestParam(value = "cidade", required = false) String cidade,

            @Parameter(description = "Contrato", example = "123456")
            @RequestParam(value = "contrato", required = false) String contrato,

            @Parameter(description = "Papel", example = "ADMIN")
            @RequestParam(value = "role", required = false) String role,

            @Parameter(description = "Data de cadastro", example = "20230101")
            @RequestParam(value = "dataCadastro", required = false) Integer dataCadastro,

            @Parameter(description = "Número da página", example = "0")
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,

            @Parameter(description = "Tamanho da página", example = "5")
            @RequestParam(value = "tamanhoPagina", defaultValue = "5") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.buscarComFiltro(id, nome, cidade, contrato, role, dataCadastro, pagina, tamanhoPagina).map(mapper::toPesquisaDTO));
    }
}
