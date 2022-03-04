package com.capeelectric.service.impl;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.ElectromagneticCompatabilityException;
import com.capeelectric.service.ElectromagneticPDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ElectromagneticPDFServiceImpl implements ElectromagneticPDFService {

	@Override
	public void printElectromagneticData(String userName, Integer emcId) throws ElectromagneticCompatabilityException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ElectromagneticData.pdf"));
				document.open();
				document.close();
				writer.close();

			} catch (

			Exception e) {
				e.printStackTrace();
			}

		} else

		{
			throw new ElectromagneticCompatabilityException("Invalid Inputs");
		}
	}
}
