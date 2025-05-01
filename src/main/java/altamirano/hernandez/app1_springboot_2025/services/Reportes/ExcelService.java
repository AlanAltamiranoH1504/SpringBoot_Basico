package altamirano.hernandez.app1_springboot_2025.services.Reportes;

import altamirano.hernandez.app1_springboot_2025.models.Producto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    //Metodo de generacion de excel
    public ByteArrayInputStream exportToExcel(String[] headers, List<Producto> productos) throws IOException {
        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet = workbook.createSheet("Productos");

            //Personalizacion de headers
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            //Haeders
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i<headers.length; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            //Data (Cuerpo excel)
            int rowIndex = 1;
            for (var producto: productos){
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(producto.getId());
                row.createCell(1).setCellValue(producto.getNombre());
                row.createCell(2).setCellValue(producto.getSlug());
                row.createCell(3).setCellValue(producto.getDescripcion());
                row.createCell(4).setCellValue(producto.getPrecio());
                row.createCell(5).setCellValue(producto.getCategoria().getNombre());
            }
            //Ajuste automatico de columnas
            for (int i = 0; i<headers.length; i++){
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
