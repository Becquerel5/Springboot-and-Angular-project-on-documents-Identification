package com.fabrication.client.services;

import com.fabrication.agent.repositories.ReferenceDocumentRepository;
import com.fabrication.entities.Cni;
import com.fabrication.entities.Passport;
import com.fabrication.entities.Referencedocument;
import com.fabrication.exceptions.ResourceNotFoundException;
import com.fabrication.services.HeaderFooterPageEvent;
import com.fabrication.utils.StatusTreatmentSystemeList;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class BuildClientPDFServiceImpl implements BuildClientPDFService {

    private final PersonService personService;
    private final ReferenceDocumentRepository referencedocumentRepository;

    @Value("${structure.logo}")
    private String structureLogo;

    @Value("${structure.name}")
    private String structureName;

    @Value("${structure.email}")
    private String structureEmail;

    @Value("${structure.phone}")
    private String structurePhone;

    @Value("${structure.website}")
    private String structureWebSite;

    @Value("${copyright}")
    private String structureCopyRight;

    @Autowired
    public BuildClientPDFServiceImpl(PersonService personService, ReferenceDocumentRepository referencedocumentRepository) {
        this.personService = personService;
        this.referencedocumentRepository = referencedocumentRepository;
    }
    
    private String isPassPortOrCni(Object object){
        Cni cni = new Cni();
        if(object.getClass()==cni.getClass())
            return "cni";
        return "passport";
    }

    @Override
    public void exportForClient(HttpServletResponse response, Long idClient) throws DocumentException, IOException, ResourceNotFoundException {
        Optional<Object> optionalReferenceDocument = this.referencedocumentRepository.getCurrentReferenceDocumentByClientId(idClient, StatusTreatmentSystemeList.FINISH);
        if(!optionalReferenceDocument.isPresent())
            throw new ResourceNotFoundException("Ce client ne possede pas de dossier en cours");
        generatePDFForClient(response, optionalReferenceDocument.get());
    }

    private void generatePDFForClient(HttpServletResponse response, Object object) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(
                this.structureLogo,
                this.structureName,
                this.structureEmail,
                this.structurePhone,
                this.structureWebSite,
                this.structureCopyRight
        );
        writer.setPageEvent(event);

        // write to document
        document.open();

        String title = "Récépissé d'Identification / Receipt of Identification";
        Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD | Font.UNDERLINE);
        Paragraph head = new Paragraph(title, font);
        head.setAlignment(Element.ALIGN_CENTER);
        head.setIndentationLeft(20);

        PdfPTable basicInformation = new PdfPTable(3);
        basicInformation.setWidthPercentage(100);
        basicInformation.setWidths(new int[]{1, 3, 4});

        //Image User
        Image logo = Image.getInstance(Objects.requireNonNull(HeaderFooterPageEvent.class.getResource(this.structureLogo)));
        logo.scaleToFit(64, 64);
        logo.setScaleToFitHeight(true);
        logo.setBorder(Rectangle.NO_BORDER);

        PdfPCell img = new PdfPCell(logo);
        img.setBorder(Rectangle.NO_BORDER);
        basicInformation.addCell(img);
        //Add image in pdf
        //basicInformation.addCell(logo);

        //Add information
        PdfPCell basicInformationCell = new PdfPCell();
        basicInformationCell.setPaddingBottom(9);
        basicInformationCell.setPaddingLeft(15);
        basicInformationCell.setBorder(Rectangle.NO_BORDER);
        Font fontSmallTitle = new Font(Font.FontFamily.HELVETICA, 11, Font.UNDERLINE);
        fontSmallTitle.setColor(BaseColor.GREEN);
        basicInformationCell.addElement(new Phrase("Nom / Name: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Prenom / Surname: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Date Naissance / Date of birth: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Adresse / Address: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Profession / Occupation: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Sexe / Gender: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Mere / Mother: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Pere / Father: ", fontSmallTitle));


        basicInformation.addCell(basicInformationCell);
        basicInformation.addCell(setBasicInformationCellData((Referencedocument) object));


        //Add information in pdf


        head.add(basicInformation);

        head.add(passPortOrCni(font, object));
        PdfPTable referenceInformation = new PdfPTable(2);
        referenceInformation.setWidthPercentage(100);
        referenceInformation.setWidths(new int[]{3, 4});


        //Add information
        head.add(referenceInformationFunction(fontSmallTitle, referenceInformation, object));

        //Add information in pdf


        Image qr_image = createQrCode((Referencedocument) object);
        document.add(head);
        document.add(qr_image);
        document.close();
    }

    private void generatePDFForAgent(HttpServletResponse response, Object object) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        HeaderFooterPageEvent event = new HeaderFooterPageEvent(
                this.structureLogo,
                this.structureName,
                this.structureEmail,
                this.structurePhone,
                this.structureWebSite,
                this.structureCopyRight
        );
        writer.setPageEvent(event);

        // write to document
        document.open();

        String title = "Récépissé d'Identification / Receipt of Identification";
        Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD | Font.UNDERLINE);
        Paragraph head = new Paragraph(title, font);
        head.setAlignment(Element.ALIGN_CENTER);
        head.setIndentationLeft(20);

        PdfPTable basicInformation = new PdfPTable(3);
        basicInformation.setWidthPercentage(100);
        basicInformation.setWidths(new int[]{1, 3, 4});

        //Image User
        Referencedocument referencedocument = (Referencedocument) object;
        Image logo = Image.getInstance(referencedocument.getImageadditionaldocument().getPathDocumentImage());
        logo.scaleToFit(64, 64);
        logo.setScaleToFitHeight(true);
        logo.setBorder(Rectangle.NO_BORDER);

        PdfPCell img = new PdfPCell(logo);
        img.setBorder(Rectangle.NO_BORDER);
        basicInformation.addCell(img);
        //Add image in pdf
        //basicInformation.addCell(logo);

        //Add information
        PdfPCell basicInformationCell = new PdfPCell();
        basicInformationCell.setPaddingBottom(9);
        basicInformationCell.setPaddingLeft(15);
        basicInformationCell.setBorder(Rectangle.NO_BORDER);
        Font fontSmallTitle = new Font(Font.FontFamily.HELVETICA, 11, Font.UNDERLINE);
        fontSmallTitle.setColor(BaseColor.GREEN);
        basicInformationCell.addElement(new Phrase("Nom / Name: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Prenom / Surname: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Date Naissance / Date of birth: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Adresse / Address: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Profession / Occupation: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Sexe / Gender: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Mere / Mother: ", fontSmallTitle));
        basicInformationCell.addElement(new Phrase("Pere / Father: ", fontSmallTitle));


        basicInformation.addCell(basicInformationCell);
        basicInformation.addCell(setBasicInformationCellData((Referencedocument) object));


        //Add information in pdf


        head.add(basicInformation);

        head.add(passPortOrCni(font, object));

        PdfPTable referenceInformation = new PdfPTable(2);
        referenceInformation.setWidthPercentage(100);
        referenceInformation.setWidths(new int[]{3, 4});


        //Add information
        head.add(referenceInformationFunction(fontSmallTitle, referenceInformation, object));

        //Add information in pdf


        Image qr_image = createQrCode((Referencedocument) object);

        document.add(head);
        document.add(qr_image);
        document.close();
    }

    private Image createQrCode(Referencedocument referencedocument) throws BadElementException {
        BarcodeQRCode my_code = new BarcodeQRCode(referencedocument.getIdDocumentReference()+ referencedocument.getClient().getEmail()+ referencedocument.getAddress(), 1, 1, null);
        Image qr_image = my_code.getImage();
        qr_image.scaleToFit(100, 100);
        qr_image.setScaleToFitHeight(true);
        qr_image.setBorder(Rectangle.NO_BORDER);
        return qr_image;
    }

    private PdfPTable referenceInformationFunction(Font fontSmallTitle, PdfPTable pdfPTable, Object o) {
        PdfPCell referenceInformationCell1 = new PdfPCell();
        referenceInformationCell1.setPaddingBottom(9);
        referenceInformationCell1.setPaddingLeft(15);
        referenceInformationCell1.setBorder(Rectangle.NO_BORDER);
        referenceInformationCell1.addElement(new Phrase("Numero / Number: ", fontSmallTitle));
        referenceInformationCell1.addElement(new Phrase("Pays / Country: ", fontSmallTitle));
        if(this.isPassPortOrCni(o)=="cni")
            referenceInformationCell1.addElement(new Phrase("Poste ID / Id Poste: ", fontSmallTitle));
        referenceInformationCell1.addElement(new Phrase("Date delivrance / Date of delivery: ", fontSmallTitle));
        referenceInformationCell1.addElement(new Phrase("Date d'expiration / Expire date: ", fontSmallTitle));
        pdfPTable.addCell(referenceInformationCell1);


        PdfPCell referenceInformationCell2 = new PdfPCell();
        referenceInformationCell2.setPaddingBottom(9);
        referenceInformationCell2.setPaddingLeft(5);
        referenceInformationCell2.setBorder(Rectangle.NO_BORDER);
        Referencedocument referencedocument = (Referencedocument) o;
        referenceInformationCell2.addElement(new Phrase(referencedocument.getDocumentNumber().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        if(this.isPassPortOrCni(o)=="cni")
            referenceInformationCell2.addElement(new Phrase("Cameroun", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        if(this.isPassPortOrCni(o)=="passport") {

            Passport passport = (Passport) o;
            referenceInformationCell2.addElement(new Phrase(passport.getCountry(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        }
        if(this.isPassPortOrCni(o)=="cni") {

            Cni cni = (Cni) o;
            referenceInformationCell2.addElement(new Phrase(cni.getPosteIdentification(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        }
        referenceInformationCell2.addElement(new Phrase("12-06-2020", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        referenceInformationCell2.addElement(new Phrase("12-06-2025", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        pdfPTable.addCell(referenceInformationCell2);
        return pdfPTable;
    }

    private PdfPTable passPortOrCni(Font font, Object object) throws DocumentException {
        PdfPTable titleReference = new PdfPTable(1);
        titleReference.setWidthPercentage(100);
        titleReference.setWidths(new int[]{1});

        PdfPCell titleReferenceCell = new PdfPCell();
        titleReferenceCell.setPaddingBottom(9);
        titleReferenceCell.setPaddingLeft(5);
        titleReferenceCell.setBorder(Rectangle.NO_BORDER);
        if(this.isPassPortOrCni(object)=="cni")
            titleReferenceCell.addElement(new Paragraph("CNI", font));
        if(this.isPassPortOrCni(object)=="passport")
            titleReferenceCell.addElement(new Paragraph("PASSPORT", font));
        titleReference.addCell(titleReferenceCell);
        return titleReference;
    }

    private PdfPCell setBasicInformationCellData(Referencedocument referencedocument) {
        PdfPCell basicInformationCell1 = new PdfPCell();
        basicInformationCell1.setPaddingBottom(9);
        basicInformationCell1.setPaddingLeft(5);
        basicInformationCell1.setBorder(Rectangle.NO_BORDER);
        basicInformationCell1.addElement(new Phrase(referencedocument.getFirstName().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(referencedocument.getLastName(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(
                referencedocument.getDateOfBirth().getDay() +"/"+String.valueOf(referencedocument.getDateOfBirth().getMonth())+"/"+String.valueOf(referencedocument.getDateOfBirth().getYear()),
                new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)
            )
        );
        basicInformationCell1.addElement(new Phrase(referencedocument.getAddress(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(referencedocument.getProfession(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(String.valueOf(referencedocument.getGender()), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(referencedocument.getNameOfMother(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        basicInformationCell1.addElement(new Phrase(referencedocument.getNameOfFather(), new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
        return basicInformationCell1;
    }

    @Override
    public void exportForAgent(HttpServletResponse response, Long idClient) throws IOException, DocumentException {
        Optional<Object> optionalReferenceDocument = this.referencedocumentRepository.getCurrentReferenceDocumentByClientId(idClient, StatusTreatmentSystemeList.FINISH);
        if(!optionalReferenceDocument.isPresent())
            throw new ResourceNotFoundException("Ce client ne possede pas de dossier en cours");
        generatePDFForAgent(response, optionalReferenceDocument.get());
    }
}
