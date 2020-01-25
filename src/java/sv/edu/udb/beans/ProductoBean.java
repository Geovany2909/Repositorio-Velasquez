package sv.edu.udb.beans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.IOUtils;
import sv.edu.udb.entities.CategoriaEntity;
import sv.edu.udb.entities.ProductoEntity;
import sv.edu.udb.model.ProductoModel;
import sv.edu.udb.util.JsfUtil;

@ManagedBean
@RequestScoped
public class ProductoBean {

    ProductoModel modelo = new ProductoModel();
    private ProductoEntity producto;
    private CategoriaEntity categoria;
    private Part fileUpload;

    public ProductoBean() {
        producto = new ProductoEntity();
        categoria = new CategoriaEntity();
        producto.setCategoria(categoria);
    }

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public List<ProductoEntity> getListaProductos() {
        return modelo.ListarProductos();
    }

    public String addProducto() throws IOException {
        byte[] bytes;
        if (fileUpload != null) {
                bytes = IOUtils.readFully(fileUpload.getInputStream(), -1, true);
                producto.setImagen(bytes);
        } else {
            BufferedImage bImage = ImageIO.read(getClass().getResource("/resources/default-image.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            bytes = bos.toByteArray();
            producto.setImagen(bytes);
        }
        
        if (modelo.agregarProductos(producto) != 1) {
            JsfUtil.setErrorMessage(null, "Ingrese los datos correctamente.");
            return null;
        } else {
            JsfUtil.setFlashMessage("exito", "Producto registrado exitosamente ");
            return "ProductoA?faces-redirect=true";
        }
    }

    public void findProductoById(int id) {
        this.producto = modelo.getProductosById(id);
    }

    public String updateProducto() throws IOException {
        if (fileUpload != null) {
            byte[] bytes = IOUtils.readFully(fileUpload.getInputStream(), -1, false);
            producto.setImagen(bytes);
        }else{
        BufferedImage bImage = ImageIO.read(getClass().getResource("/resources/default-image.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
           byte[] bytes = bos.toByteArray();
            producto.setImagen(bytes);
        }

        if (modelo.ActualizarProductos(producto) > 0) {
            JsfUtil.setFlashMessage("exito", "Producto modificado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo modificar el producto");
        }
        return "ProductoA?faces-redirect=true";
    }

    public String deleteProducto() {
        String codigo = JsfUtil.getRequest().getParameter("id");
        int id = Integer.parseInt(codigo);

        if (modelo.BorrarProductos(id) > 0) {
            JsfUtil.setFlashMessage("exito", "Producto eliminado exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo eliminar el producto con id: " + id);
        }
        return "ProductoA?faces-redirect=true";
    }

    public String displayImagen(byte[] data) throws IOException {

        String imageString = new String(Base64.encodeBase64(data));
        return imageString;
    }

}
