package altamirano.hernandez.app1_springboot_2025.services.Reportes;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {
    public byte[] createPdf(String title, List<Producto> productosContenido){
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            //Documento
            Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
            document.add(new Paragraph(title).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));

            //Creacion de headers
            String[] headers = {"Nombre", "Slug", "Descripcion", "Precio", "Categoria"};
            for (var header: headers){
                Cell headerCell = new Cell()
                        .add(new Paragraph(header).setBold().setFontSize(12).setFontColor(ColorConstants.WHITE))
                        .setBackgroundColor(ColorConstants.BLUE)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setPadding(5);
                table.addHeaderCell(headerCell);
            }

            //Creacion de filas
            for (var producto : productosContenido){
                table.addCell(new Paragraph(producto.getNombre()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5));
                table.addCell(new Paragraph(producto.getSlug()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5));
                table.addCell(new Paragraph(producto.getDescripcion()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5));
                table.addCell(new Paragraph(producto.getPrecio()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5));
                table.addCell(new Paragraph(producto.getCategoria().getNombre()).setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setPadding(5));
            }

            //Agregar tabla al archivo y cierre de archivo
            document.add(table);
            document.close();
            return outputStream.toByteArray();
        }catch (Exception e){
            throw new RuntimeException("Error en generacion de PDF: " + e.getMessage());
        }
    }
}
