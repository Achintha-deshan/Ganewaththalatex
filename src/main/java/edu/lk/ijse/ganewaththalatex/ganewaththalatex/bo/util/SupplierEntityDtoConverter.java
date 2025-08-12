package edu.lk.ijse.ganewaththalatex.ganewaththalatex.bo.util;

import edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.SupplierDto;
import edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity.Supplier;

public class SupplierEntityDtoConverter {

    public SupplierDto getSupplierDto(Supplier supplier){
        if(supplier == null) return null;
        return new SupplierDto(
                supplier.getSupplierID(),
                supplier.getSupplierName(),
                supplier.getPhoneNumber(),
                supplier.getBankName(),
                supplier.getBankAccount(),
                supplier.getBranchName()
        );
    }

    public Supplier getSupplier(SupplierDto dto){
        if(dto == null) return null;
        return new Supplier(
                dto.getSupplierID(),
                dto.getSupplierName(),
                dto.getPhoneNumber(),
                dto.getBankName(),
                dto.getBankAccount(),
                dto.getBranchName()
        );
    }
}
