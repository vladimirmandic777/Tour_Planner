package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.model.TourFx;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;


public class PDFListReportService implements ReportService {
    private final Dao<TourFx> mediaItemDao;
    public static final String TARGET_PDF = "src/main/resources/PDF/TourListReport" + LocalDate.now() + ".pdf";


    public PDFListReportService(Dao<TourFx> mediaItemDao) {
        this.mediaItemDao = mediaItemDao;
    }

    @Override
    public void generateReport() {
        final var mediaItemList = this.mediaItemDao.getAll();
        try {
            PdfWriter writer = new PdfWriter(TARGET_PDF);
            PdfDocument pdf = new PdfDocument(writer);
            try (Document document = new Document(pdf)) {
                ImageData imageData = ImageDataFactory.create(new URL("https://www.technikum-wien.at/sites/default/files/logo-300x160.png"));
                document.add(new Image(imageData));
                document.add(generateTourHeader());
                document.add(generateTableHeader("Overview of Tours"));
                Table table = setupTable();
                mediaItemList.forEach(mediaItem -> {
                    table.addCell(String.valueOf(mediaItem.toString()));
                    table.addCell(String.valueOf(mediaItem.getFromDestination()));
                    table.addCell(String.valueOf(mediaItem.getToDestination()));
                    table.addCell(String.valueOf(mediaItem.getDistance()));
                    table.addCell(String.valueOf(mediaItem.getEstimatedTime()));
                    table.addCell(mediaItem.getDescription());
                });
                document.add(table);
                //document.add(new AreaBreak());
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void generateTourReport(TourFx tourfx){
        try {
            PdfWriter writer = new PdfWriter("src/main/resources/PDF/TourReport-"+tourfx.getName() + "-" + LocalDate.now() + ".pdf");
            PdfDocument pdf = new PdfDocument(writer);
            try (Document document = new Document(pdf)) {
                ImageData imageData = ImageDataFactory.create(new URL("https://www.technikum-wien.at/sites/default/files/logo-300x160.png"));
                document.add(new Image(imageData));
                document.add(new Paragraph("Tour -" + tourfx.getName() + "- report")
                        .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                        .setFontSize(18)
                        .setBold());
                Table table = setupTable();
                table.addCell(String.valueOf(tourfx.getName()));
                table.addCell(String.valueOf(tourfx.getFromDestination()));
                table.addCell(String.valueOf(tourfx.getToDestination()));
                table.addCell(String.valueOf(tourfx.getDistance()));
                table.addCell(String.valueOf(tourfx.getEstimatedTime()));
                table.addCell(tourfx.getDescription());

                document.add(table);
                //LOG table:
                document.add(generateTableHeader("Logs for this tour"));
                Table logTable = setupLogTable();
                //TODO:ADD data to log table
                document.add(logTable);

                document.add(new AreaBreak());
                ImageData mapImage = ImageDataFactory.create(new URL("file:src/main/resources/images/mapImage" + String.valueOf(tourfx.getId()) + ".jpg"));
                document.add(new Image(mapImage).setFixedPosition(100,250));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Table setupTable() {
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth().setPaddingTop(20);
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Name"));
        table.addHeaderCell(getHeaderCell("Start"));
        table.addHeaderCell(getHeaderCell("Goal"));
        table.addHeaderCell(getHeaderCell("Distance"));
        table.addHeaderCell(getHeaderCell("Duration"));
        table.addHeaderCell(getHeaderCell("Description"));
        return table;
    }
    private Table setupLogTable() {
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Comment"));
        table.addHeaderCell(getHeaderCell("Date"));
        table.addHeaderCell(getHeaderCell("Difficulty"));
        table.addHeaderCell(getHeaderCell("Rating"));
        table.addHeaderCell(getHeaderCell("Time"));
        return table;
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setBold().setBackgroundColor(ColorConstants.GRAY);
    }

    private Paragraph generateTableHeader(String title) throws IOException {
        return new Paragraph(title)
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold();
    }

    private Paragraph generateTourHeader() throws IOException {
        return new Paragraph("Tours Report from " + LocalDate.now())
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(26)
                .setBold()
                .setFontColor(ColorConstants.BLUE);
    }
}
