package com.crud.practise.repositoryImpl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crud.practise.model.Warehouse;
import com.crud.practise.repository.WarehouseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Object[]> getAllWarehouseDetails() {
		Query query = entityManager.createNativeQuery("SELECT * FROM WAREHOUSE");
		List<Object[]> wareHouseList = null;
		try {
			wareHouseList = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wareHouseList;
	}

	@Override
	public Object getWarehouseDetailById(int warehouseId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM  WAREHOUSE WHERE WAREHOUSEID = ? ");
		query.setParameter(1, warehouseId );
		Object warehouseDetail = null;
		try {
			warehouseDetail =  query.getSingleResult();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		return warehouseDetail;
	}

	@Override
	@Transactional
	public Warehouse insertWarehouseDetail(Warehouse warehouse) {
		Query query = entityManager.createNativeQuery("INSERT INTO WAREHOUSE (WAREHOUSENAME, QTY, SUPPLIERID, CREATED_DATE) VALUES (?,?,?,?)");
		query.setParameter(1, warehouse.getWarehouseName());
		query.setParameter(2, warehouse.getQty());
		query.setParameter(3, warehouse.getSupplierId());
		Date date = new Date();
		query.setParameter(4, date);
		int recordInserted = 0;
		try {
			recordInserted = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recordInserted > 0) {
		return warehouse;
		}
		else return null;
	}

	@Override
	@Transactional
	public Object updateWarehouseById(int warehouseId, Warehouse warehouse) {
		Query query = entityManager.createNativeQuery("UPDATE WAREHOUSE SET WAREHOUSENAME=?, QTY=?, SUPPLIERID=?, UPDATED_DATE=? WHERE WAREHOUSEID = ? ");
		query.setParameter(1, warehouse.getWarehouseName());
		query.setParameter(2, warehouse.getQty());
		query.setParameter(3, warehouse.getSupplierId());
		Date date = new Date();
		query.setParameter(4, date);
		query.setParameter(5, warehouseId);
		int recordUpdated = 0;
		try {
			recordUpdated =  query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (recordUpdated != 0) {
			return warehouse;
		} else 
			return null;
	}

	@Override
	@Transactional
	public Warehouse deleteSupplierDetailById(int warehouseId) throws Exception {
		Query query = entityManager.createNativeQuery("DELETE FROM WAREHOUSE WHERE WAREHOUSEID = ? ");
		query.setParameter(1, warehouseId);
		int deleteRecord = 0; 
		try {
			deleteRecord = query.executeUpdate();
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		if (deleteRecord > 0) {
			return null;
		}
		else
			throw new Exception("SupplierOrderDetails not found the given ID");
	}

	@Override
	@Transactional
	public List<Warehouse> insertMultipleDetails(List<Warehouse> warehouse) {
		for(Warehouse warehouses:warehouse) {
			Query query = entityManager.createNativeQuery("INSERT INTO WAREHOUSE  VALUES (?,?,?,?,?,?)");
			query.setParameter(1, warehouses.getWarehouseId());
			query.setParameter(2, warehouses.getWarehouseName());
			query.setParameter(3, warehouses.getQty());
			query.setParameter(4, warehouses.getSupplierId());
		    Date date = new Date();
			query.setParameter(5, date);
			query.setParameter(6, null);
			query.executeUpdate();
		}
		return warehouse;
	}

}
