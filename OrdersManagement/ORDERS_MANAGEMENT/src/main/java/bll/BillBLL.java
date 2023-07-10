package bll;

import dao.BillDAO;
import model.Bill;

import java.util.List;

public class BillBLL {
    private BillDAO billDAO;
    public BillBLL(){
        billDAO = new BillDAO();
    }

    public Bill insert(String clientName, String productName, int productPrice, int quantity){
        return billDAO.insert(clientName, productName, productPrice, quantity);
    }

    public List<Bill> findAll(){
        return billDAO.findAll();
    }
}
