
package com.crud.practise.repository;

import java.util.List;

import com.crud.practise.model.Invoice;

public interface InvoiceRepository {
	
	public List<Object[]> getAllInvoiceDetails();
	
	public Object getInvoiceDetailById(int invoiceId);
	
	public Invoice createInvoiceDetails(Invoice invoice);
	
	public Object updateSupplierOrderById(int invoiceId, Invoice invoice);
	
	public Invoice deleteInvoiceById(int invoiceId) throws Exception;
	
}
