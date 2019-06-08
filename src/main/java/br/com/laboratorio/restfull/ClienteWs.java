package br.com.laboratorio.restfull;

import br.com.laboratorio.dao.ClienteDao;
import br.com.laboratorio.modelo.Cliente;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author DaRocha
 */
@Path("cliente")
public class ClienteWs {

    public ClienteWs() {
    }

    //http://127.0.0.1:8080/DiskAguaMavem/rest/cliente
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getList() {
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> clientes = clienteDao.listarObjetos();
        Gson g = new Gson();
        return g.toJson(clientes);
    }

    //http://127.0.0.1:8080/DiskAguaMavem/rest/cliente
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    public String buscarPorId(@PathParam("codigo") Long codigo) {
        Gson gson = new Gson();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscarObjeto(codigo);

        String saida = gson.toJson(cliente);
        return saida;
    }

//http://127.0.0.1:8080/DiskAguaMavem/rest/cliente
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String incluir(String json) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.salvarOuAtualizarObjeto(cliente);

        String saida = gson.toJson(cliente);
        return saida;
    }

    //http://127.0.0.1:8080/DiskAguaMavem/rest/cliente
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String atualizar(String json) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.salvarOuAtualizarObjeto(cliente);

        String saida = gson.toJson(cliente);
        return saida;
    }
    
    //http://127.0.0.1:8080/DiskAguaMavem/rest/cliente
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    public String excluir(@PathParam("codigo") Long codigo){
        Gson gson = new Gson();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscarObjeto(codigo);
        clienteDao.deletarObjeto(cliente);
        String saida = gson.toJson(cliente);
        return saida;
    }

}
