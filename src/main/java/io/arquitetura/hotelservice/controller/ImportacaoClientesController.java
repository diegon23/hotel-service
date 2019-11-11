package io.arquitetura.hotelservice.controller;

import io.arquitetura.hotelservice.service.ImportacaoClientesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/importacao")
public class ImportacaoClientesController {

    private final ImportacaoClientesService importacaoClientesService;

    public ImportacaoClientesController(ImportacaoClientesService importacaoClientesService) {
        this.importacaoClientesService = importacaoClientesService;
    }

    @ApiOperation(value = "Importação de clientes",
            notes = "Importação de clientes através de arquivo gerado pelo sistema legado")
    @GetMapping(value = "/importar-clientes")
    public ResponseEntity<Void> importarClientes() throws Exception {

        importacaoClientesService.importarClientes();

        return ResponseEntity.ok().build();
    }

}
