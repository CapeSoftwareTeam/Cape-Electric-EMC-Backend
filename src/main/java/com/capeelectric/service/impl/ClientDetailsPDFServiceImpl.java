package com.capeelectric.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.repository.ClientDetailsRepository;
import com.capeelectric.service.ClientDetailsPDFService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ClientDetailsPDFServiceImpl implements ClientDetailsPDFService {

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Override
	public void printClientDetails(String userName, Integer emcId) throws ClientDetailsException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ClientDetails.pdf"));

				List<ClientDetails> clientDetailsRepo = clientDetailsRepository.findByEmcId(emcId);
				ClientDetails clientDetailsRepo1 = clientDetailsRepo.get(0);

				Font font8 = new Font(BaseFont.createFont(), 9, Font.NORMAL, BaseColor.BLACK);
				Font font9 = new Font(BaseFont.createFont(), 10, Font.NORMAL, BaseColor.BLACK);

				document.open();

			
				PdfPCell cell45 = new PdfPCell(
						new Paragraph(30, "Client Details", font9));
				cell45.setBorder(PdfPCell.NO_BORDER);
				cell45.setBackgroundColor(BaseColor.LIGHT_GRAY);

				Font font2 = new Font(BaseFont.createFont(), 10, Font.NORMAL | Font.BOLD, BaseColor.BLACK);
				PdfPTable table10 = new PdfPTable(1);
				table10.setWidthPercentage(100); // Width 100%
				table10.setSpacingBefore(10f); // Space before table
				table10.getDefaultCell().setBorder(0);
				table10.addCell(cell45);
				document.add(table10);

				float[] pointColumnWidths1 = { 90F, 90F };

				PdfPTable table = new PdfPTable(pointColumnWidths1); // 3 columns.
				table.setWidthPercentage(100); // Width 100%
				table.setSpacingBefore(10f); // Space before table
//				table7.setSpacingAfter(10f); // Space after table
				table.getDefaultCell().setBorder(0);

				PdfPCell cell = new PdfPCell(new Paragraph("Client Name:", font9));
				cell.setBorder(PdfPCell.NO_BORDER);
				cell.setGrayFill(0.92f);
				table.addCell(cell);
				PdfPCell cell1 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientName(), font9));
				cell1.setGrayFill(0.92f);
				cell1.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell1);

				PdfPCell cell2 = new PdfPCell(new Paragraph("Contact Number:", font9));
				cell2.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell2);
				PdfPCell cell3 = new PdfPCell(new Paragraph(clientDetailsRepo1.getContactNumber(), font9));
				cell3.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell3);

				PdfPCell cell4 = new PdfPCell(new Paragraph("Contact Person:", font9));
				cell4.setBorder(PdfPCell.NO_BORDER);
				cell4.setGrayFill(0.92f);
				table.addCell(cell4);
				PdfPCell cell5 = new PdfPCell(new Paragraph(clientDetailsRepo1.getContactPerson(), font9));
				cell5.setGrayFill(0.92f);
				cell5.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell5);

				PdfPCell cell6 = new PdfPCell(new Paragraph("Landmark:", font9));
				cell6.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell6);
				PdfPCell cell7 = new PdfPCell(new Paragraph(clientDetailsRepo1.getLandMark(), font9));
				cell7.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Paragraph("Location:", font9));
				cell8.setBorder(PdfPCell.NO_BORDER);
				cell8.setGrayFill(0.92f);
				table.addCell(cell8);
				PdfPCell cell9 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientLocation(), font9));
				cell9.setGrayFill(0.92f);
				cell9.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell9);

				PdfPCell cell10 = new PdfPCell(new Paragraph("Address:", font9));
				cell10.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell10);
				PdfPCell cell11 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientAddress(), font9));
				cell11.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell11);

				PdfPCell cell12 = new PdfPCell(new Paragraph(" Email:", font9));
				cell12.setBorder(PdfPCell.NO_BORDER);
				cell12.setGrayFill(0.92f);
				table.addCell(cell12);
				PdfPCell cell13 = new PdfPCell(new Paragraph(clientDetailsRepo1.getEmail(), font9));
				cell13.setGrayFill(0.92f);
				cell13.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell13);

				PdfPCell cell14 = new PdfPCell(new Paragraph("Address:", font9));
				cell14.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell14);
				PdfPCell cell15 = new PdfPCell(new Paragraph(clientDetailsRepo1.getClientAddress(), font9));
				cell15.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell15);

				PdfPCell cell16 = new PdfPCell(new Paragraph("Country:", font9));
				cell16.setBorder(PdfPCell.NO_BORDER);
				cell16.setGrayFill(0.92f);
				table.addCell(cell16);
				PdfPCell cell17 = new PdfPCell(new Paragraph(clientDetailsRepo1.getCountry(), font9));
				cell17.setGrayFill(0.92f);
				cell17.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell17);

				PdfPCell cell18 = new PdfPCell(new Paragraph("State:", font9));
				cell18.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell18);
				PdfPCell cell19 = new PdfPCell(new Paragraph(clientDetailsRepo1.getState(), font9));
				cell19.setBorder(PdfPCell.NO_BORDER);
				table.addCell(cell19);

				document.add(table);

				document.close();
				writer.close();

			} catch (

			Exception e) {
				e.printStackTrace();
			}

		} else

		{
			throw new ClientDetailsException("Invalid Inputs");
		}

	}
}
