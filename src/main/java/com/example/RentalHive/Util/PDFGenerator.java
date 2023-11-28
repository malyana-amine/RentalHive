package com.example.RentalHive.Util;

import com.example.RentalHive.entity.Contract;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFGenerator {
    public void generate(Contract contract, String filePath) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Creating PdfWriter instance with FileOutputStream
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Opening the created document to change it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Contract Details", fontTitle);

        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in the document
        document.add(paragraph1);

        // Creating a table of the 5 columns (including User and Total Price)
        PdfPTable table = new PdfPTable(5);

        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{1, 2, 2, 2, 2});
        table.setSpacingBefore(5);

        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();

        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings in the created table cell or header
        // Adding Cell to table
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Signature", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total Price", font));
        table.addCell(cell);

        // Adding contract data
        table.addCell(String.valueOf(contract.getId()));
        table.addCell(contract.getSignature());
        table.addCell(contract.getDescription());

        // Adding user name and total price
        table.addCell(contract.getDevis().getDemand().getUser().getUsername());
        table.addCell(String.valueOf(contract.getDevis().getPriceTotal()));

        document.add(table);

        // Closing the document
        document.close();

        // Close the writer
        writer.close();
    }

}
