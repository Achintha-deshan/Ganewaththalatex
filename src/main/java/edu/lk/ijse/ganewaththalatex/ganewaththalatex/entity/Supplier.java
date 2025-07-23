package edu.lk.ijse.ganewaththalatex.ganewaththalatex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String supplierID;
    private String supplierName;
    private String phoneNumber;
    private String bankName;
    private String bankAccount;
    private String branchName;

}
