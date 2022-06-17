package at.technikum.tour_planner.BAL;

import at.technikum.tour_planner.dal.Dao;
import at.technikum.tour_planner.dal.TourFxDao;
import at.technikum.tour_planner.model.TourFx;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class PDFReportService implements ReportService {
    private final Dao<TourFx> mediaItemDao;
    public static final String TARGET_PDF = "report" + LocalDate.now() + ".pdf";

    public PDFReportService(Dao<TourFx> mediaItemDao) {
        this.mediaItemDao = mediaItemDao;
    }

    @Override
    public void generateReport() {
        final var mediaItemList = this.mediaItemDao.getAll();
        try {
            PdfWriter writer = new PdfWriter(TARGET_PDF);
            PdfDocument pdf = new PdfDocument(writer);
            try (Document document = new Document(pdf)) {
                document.add(generateTourHeader());
                document.add(generateTableHeader());
                Table table = setupTable();
                mediaItemList.forEach(mediaItem -> {
                    table.addCell(String.valueOf(mediaItem.getId()));
                    table.addCell(mediaItem.toString());
                    table.addCell(String.valueOf(mediaItem.getEstimatedTime()));
                    table.addCell(mediaItem.getDescription());
                });
                document.add(table);

                document.add(new AreaBreak());
                ImageData imageData = ImageDataFactory.create(new URL("https://www.technikum-wien.at/sites/default/files/logo-300x160.png"));
                document.add(new Image(imageData));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Table setupTable() {
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
        table.setFontSize(14).setBackgroundColor(ColorConstants.WHITE);
        table.addHeaderCell(getHeaderCell("Id"));
        table.addHeaderCell(getHeaderCell("Name"));
        table.addHeaderCell(getHeaderCell("Duration"));
        table.addHeaderCell(getHeaderCell("Content"));
        return table;
    }

    private static Cell getHeaderCell(String s) {
        return new Cell().add(new Paragraph(s)).setBold().setBackgroundColor(ColorConstants.GRAY);
    }

    private Paragraph generateTableHeader() throws IOException {
        return new Paragraph("Overview of MediaItems")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold();
    }

    private Paragraph generateTourHeader() throws IOException {
        return new Paragraph("Tour Report from " + LocalDate.now())
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(26)
                .setBold()
                .setFontColor(ColorConstants.BLUE);
    }
}
