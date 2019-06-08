package br.com.laboratorio.restfull;

import Interfaces.CrudRest;
import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.modelo.Produto;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author DaRocha
 */
@Path("produto")
public class ProdutoWs {

    public ProdutoWs() {
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getList() {
        Gson g = new Gson();
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> produtos = produtoDao.listarObjetos();
        return g.toJson(produtos);
    }
    
    //http://127.0.0.1:8080/DiskAguaMavem/rest/produto
     
//    
    
    //http://127.0.0.1:8080/DiskAguaMavem/rest/produto
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    public String buscarPorId(@PathParam("codigo") Long codigo) {
        Gson gson = new Gson();
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.buscarObjeto(codigo);

        String saida = gson.toJson(produto);
        return saida;
    }

//http://127.0.0.1:8080/DiskAguaMavem/rest/produto
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String incluir(String json) {
        Gson gson = new Gson();
        Produto produto = gson.fromJson(json, Produto.class);
        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.salvarOuAtualizarObjeto(produto);

        String saida = gson.toJson(produto);
        return saida;
    }

    //http://127.0.0.1:8080/DiskAguaMavem/rest/produto
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public String atualizar(String json) {
//        Gson gson = new Gson();
//        Produto produto = gson.fromJson(json, Produto.class);
//        ProdutoDao produtoDao = new ProdutoDao();
//        produtoDao.salvarOuAtualizarObjeto(produto);
//
//        String saida = gson.toJson(produto);
//        return saida;
//    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void atualizar(String json) {
        Gson gson = new Gson();
        Produto produto = gson.fromJson(json, Produto.class);
        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.salvarOuAtualizarObjeto(produto);

        //String saida = gson.toJson(produto);
        //return saida;
    }
    
    
    
    //http://127.0.0.1:8080/DiskAguaMavem/rest/produto
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    public String excluir(@PathParam("codigo") Long codigo){
        Gson gson = new Gson();
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.buscarObjeto(codigo);
        produtoDao.deletarObjeto(produto);
        String saida = gson.toJson(produto);
        return saida;
    }

    

}
