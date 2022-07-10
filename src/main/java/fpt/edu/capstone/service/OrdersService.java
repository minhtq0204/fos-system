package fpt.edu.capstone.service;

import fpt.edu.capstone.entities.Customer;
import fpt.edu.capstone.entities.Orders;
import fpt.edu.capstone.entities.QRCode;
import fpt.edu.capstone.implementService.IOrdersService;
import fpt.edu.capstone.repo.CustomerRepository;
import fpt.edu.capstone.repo.OrdersRepository;
import fpt.edu.capstone.repo.QRCodeRepository;
import fpt.edu.capstone.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Orders addOrder(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders updateOrder(Orders orders) {
        QRCode qrCode = qrCodeRepository.findByQRCodeId(orders.getQrCode().getQRCodeId());
        Customer customer = customerRepository.findByCustomerId(orders.getCustomer().getCustomerId());

        if(orders !=null){
            Orders orders1 = ordersRepository.getById(orders.getOrderId());
            if(orders1 != null){
                orders1.setCustomer(customer);
                orders1.setQrCode(qrCode);
//                orders1.setSubmitTime(orders.getSubmitTime());
                orders1.setCloseTime(orders.getCloseTime());
                orders1.setOtherFee(orders.getOtherFee());
                orders1.setStatus(orders.getStatus());
                orders1.setDescription(orders.getDescription());
                orders1.setOrderDiscount(orders.getOrderDiscount());

                return ordersRepository.save(orders1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteOrder(Long id) {
        Orders orders = ordersRepository.getOrdersById(id);
        if(orders != null){
            ordersRepository.delete(orders);
            return true;
        }
        return false;
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
       Orders orders = ordersRepository.getOrdersById(id);
       if (orders != null){
           return orders;
       }
       return null;
    }

    @Override
    public boolean checkOrderExist(Orders orders) {
        Orders orders1 = ordersRepository.findByCustomerIdAndQrCodeAndOrderStatus(orders.getCustomer().getCustomerId(), orders.getQrCode().getQRCodeId(), orders.getStatus());
        return orders1 == null ? false : true;
    }
}
