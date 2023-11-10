package com.pluralsight;

import java.io.*;

public class ContractFileManager {
    public void saveContract(Contract contract){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))){
            String type = (contract instanceof LeaseContract)? "LEASE":"SALE";

            // Shared attributes
            writer.write(String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|", type, contract.getDate(), contract.getName(), contract.getEmail(),
                contract.getVehicle().getVin(), contract.getVehicle().getYear(), contract.getVehicle().getMake(), contract.getVehicle().getModel(),
                contract.getVehicle().getVehicleType(), contract.getVehicle().getColor(), contract.getVehicle().getOdometer(), contract.getVehicle().getPrice()));

            // Lease instance attributes
            if (contract instanceof LeaseContract){
                writer.write(String.format("%s|%s|%s|%s", ((LeaseContract) contract).getExpectedEndingValue(),  ((LeaseContract) contract).getLeaseFee(), ((LeaseContract)contract).getTotalPrice(),
                        ((LeaseContract)contract).getMonthlyPayment()));
                writer.newLine();
            }

            // Sale instance attributes
            else if (contract instanceof SalesContract){
                String finance = ((SalesContract) contract).isFinance()? "YES":"NO";
                writer.write(String.format( "%s|%s|%s|%s|%s|%s", ((SalesContract) contract).getSalesTaxAmount(), ((SalesContract) contract).getRecordingFee(),
                    ((SalesContract) contract).getProcessingFee(), ((SalesContract) contract).getTotalPrice(), finance, ((SalesContract)contract).getMonthlyPayment()));
                writer.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
