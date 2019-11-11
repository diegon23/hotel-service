package io.arquitetura.hotelservice.service;

import io.arquitetura.hotelservice.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class ImportacaoClientesService {

    private final ClienteService clienteService;

    @Autowired
    public ImportacaoClientesService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public String encontrarNomeArquivo() throws Exception {
        File fl = new File("C:\\Users\\diego.nogueira\\Documents");
        File[] files = fl.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile() && "csv".equalsIgnoreCase(file.getName().substring(file.getName().lastIndexOf(".")+1));
            }
        });
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.isFile() && file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }

        if(choice != null) {
            return choice.getAbsolutePath();
        } else {
            throw new Exception("Não foi encontrado nenhum arquivo na pasta indicada!");
        }
    }

    public void importarClientes() throws Exception {
        File file= new File(this.encontrarNomeArquivo());

        List<List<String>> lines = getLines(file);

        List<Cliente> clientes = new ArrayList<>();
        for(List<String> line : lines){
            if(line.size() == 5) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(line.get(1));
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                Cliente cliente = new Cliente();
                cliente.setNome(line.get(0));
                cliente.setDtNascimento(timestamp);
                cliente.setCpf(line.get(2));
                cliente.setTelefone(line.get(3));
                cliente.setEmail(line.get(4));
                cliente.setDtCadastro(new Timestamp(System.currentTimeMillis()));
                clientes.add(cliente);
            }
        }

        clienteService.saveAll(clientes);
    }

    private List<List<String>> getLines(File file){
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(";");
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
