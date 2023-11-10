package com.pluralsight;

import java.io.*;

public class ContractFileManager {
    public void saveContract(Contract contract){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))){
            String type = (contract instanceof LeaseContract)? "LEASE":"SALE";

            // Shared attributes
            writer.newLine();
            writer.write(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|", type, contract.getDate(), contract.getName(), contract.getEmail(),
                contract.getVehicle().getVin(), contract.getVehicle().getYear(), contract.getVehicle().getMake(), contract.getVehicle().getModel(),
                contract.getVehicle().getVehicleType(), contract.getVehicle().getColor(), contract.getVehicle().getOdometer(), contract.getVehicle().getPrice()));

            // Lease instance attributes
            if (contract instanceof LeaseContract lease){
                writer.write(String.format("%s|%s|%s|%s", lease.getExpectedEndingValue(),  lease.getLeaseFee(), lease.getTotalPrice(), lease.getMonthlyPayment()));
            }

            // Sale instance attributes
            else if (contract instanceof SalesContract sale){
                String finance = sale.isFinance()? "YES":"NO";
                writer.write(String.format( "%s|%s|%s|%s|%s|%s", sale.getSalesTaxAmount(), sale.getRecordingFee(), sale.getProcessingFee(), sale.getTotalPrice(), finance, sale.getMonthlyPayment()));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
