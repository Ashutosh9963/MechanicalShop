package com.crud.practise.service;

import com.crud.practise.model.FinalResponse;
import com.crud.practise.model.Invoice;

public interface InvoiceService {
	
	public FinalResponse getAllInvoiceDetails();
	
	public FinalResponse getInvoiceDetailsById(int invoiceId);
	
	public FinalResponse insertInvoiceDetails(Invoice invoice);
	
	public FinalResponse updateInvoiceDetailsById(int invoiceId, Invoice invoice);
	
	public FinalResponse deleteInvoiceById(int invoiceId) throws Exception;

}
