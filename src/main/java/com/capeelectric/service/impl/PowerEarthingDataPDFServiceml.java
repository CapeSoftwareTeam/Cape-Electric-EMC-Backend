package com.capeelectric.service.impl;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.PowerEarthingDataException;
import com.capeelectric.service.PowerEarthingDataPDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PowerEarthingDataPDFServiceml implements PowerEarthingDataPDFService {

	@Override
	public void printPowerEarthingData(String userName, Integer emcId) throws PowerEarthingDataException {
		if (userName != null && !userName.isEmpty() && emcId != null && emcId != 0) {
			Document document = new Document(PageSize.A4, 68, 68, 62, 68);

			try {

				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PowerandEarthingData.pdf"));
				document.open();
				document.close();
				writer.close();

			} catch (

			Exception e) {
				e.printStackTrace();
			}

		} else

		{
			throw new PowerEarthingDataException("Invalid Inputs");
		}
	}
}
