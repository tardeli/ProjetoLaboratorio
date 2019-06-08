package br.com.laboratorio.controle;

import br.com.laboratorio.dao.ClienteDao;
import br.com.laboratorio.dao.ItemPedidoDao;
import br.com.laboratorio.modelo.Cliente;
import br.com.laboratorio.modelo.ItemPedido;
import br.com.laboratorio.util.HibernateUtil;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class ItemPedidoBean implements Serializable {

    private ItemPedido itemPedido = new ItemPedido();
    private ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
    private List<ItemPedido> listaObjetos;

    private Date dataInicial;
    private Date dataFinal;

    public ItemPedidoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public boolean filterByDate(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }
        if (value == null) {
            return false;
        }

        DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

        Date filterDate = (Date) value;
        Date dateFrom;
        Date dateTo;
        try {
            String fromPart = filterText.substring(0, filterText.indexOf("-"));
            String toPart = filterText.substring(filterText.indexOf("-") + 1);
            dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
            dateTo = toPart.isEmpty() ? null : df.parse(toPart);
        } catch (ParseException pe) {
            //LOGGER.log(Level.SEVERE, "unable to parse date: " + filterText, pe);
            return false;
        }

        return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));

    }

    public void imprimir() {

        try {
            Map<String, Object> paramentros = new HashMap<>();
            paramentros.put("data_Inicio", this.dataInicial);
            paramentros.put("data_final", this.dataFinal);

            Connection conexao = HibernateUtil.getConnection();

            String src = Faces.getRealPath("/relatorio/itensPedido.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);//deixar false -senao fecha apliacaco
            viewer.setVisible(true);

            JasperPrintManager.printReport(jasperPrint, false);//deixar false -senao fecha apliacaco
            //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Da Rocha\\Desktop\\itensPedido.pdf");

            Messages.addGlobalInfo("Preparando relatório...!");
            this.dataInicial = null;
            this.dataFinal = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String voltar() {
        return "index";
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public List<ItemPedido> getListaObjetos() {
        return listaObjetos = itemPedidoDao.listarObjetos();
    }

    public void setListaObjetos(List<ItemPedido> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
