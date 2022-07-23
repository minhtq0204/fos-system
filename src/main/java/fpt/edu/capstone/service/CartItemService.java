//package fpt.edu.capstone.service;
//
//import fpt.edu.capstone.entities.CartItem;
//import fpt.edu.capstone.entities.Customer;
//import fpt.edu.capstone.entities.Dishes;
//import fpt.edu.capstone.entities.QRCode;
//import fpt.edu.capstone.implementService.ICartItemService;
//import fpt.edu.capstone.repo.CartItemRepository;
//import fpt.edu.capstone.repo.DishesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CartItemService implements ICartItemService {
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private DishesRepository dishesRepository;
//
//    public List<CartItem> listCartItemByCustomer(Customer customer){
//        return cartItemRepository.findByCustomer(customer);
//    }
//
//    public List<CartItem> listCartItemByDishes(Dishes dishes){
//        return cartItemRepository.findByDishes(dishes);
//    }
//
//    @Override
//    public List<CartItem> listCartItemByQRCode(QRCode qrCode){
//        return cartItemRepository.findAllByQRCodeId(qrCode.getQRCodeId());
//    }
//
//    //        Integer addQuantity = cartItem.getQuantity();
////        Dishes dishes = dishesRepository.findById(cartItem.getDishes().getDishesId()).get();
////        CartItem cartItem1 = cartItemRepository.findByCustomerAndDishes(customer,dishes);
////        if(cartItem1 != null){
////            addQuantity = cartItem1.getQuantity() + cartItem.getQuantity();
////            cartItem1.setQuantity(addQuantity);
////        }else {
////            cartItem1 = new CartItem();
////            cartItem1.setQuantity(cartItem.getQuantity());
////            cartItem1.setCustomer(cartItem.getCustomer());
////            cartItem1.setDishes(cartItem.getDishes());
////            cartItem1.setQrCode(cartItem.getQrCode());
////        }
////        return cartItemRepository.save(cartItem1);
////        return addQuantity;
//
//    @Override
//    public CartItem addDishesToCart(CartItem cartItem){
//        CartItem cartItem1 = cartItemRepository.checkExistCartItem(cartItem.getQrCode().getQRCodeId(), cartItem.getDishes().getDishesId());
//        if (cartItem1 == null){
//            return cartItemRepository.save(cartItem);
//        }else {
//            cartItem1.setQuantity(cartItem.getQuantity() + cartItem1.getQuantity());
//            return cartItemRepository.save(cartItem1);
//        }
//    }
//
//    public CartItem updateDishesToCart(CartItem cartItem){
//        CartItem cartItem1 = cartItemRepository.checkExistCartItem(cartItem.getQrCode().getQRCodeId(), cartItem.getDishes().getDishesId());
//        if (cartItem1.getQuantity() == null){
//            return cartItem1;
//        }else {
////            if(cartItem1.getQuantity() > cartItem.getQuantity() || cartItem1.getQuantity() == cartItem.getQuantity()){
////                 cartItemRepository.removeDishesInCart(cartItem.getDishes().getDishesId());
////            }else {
//                cartItem1.setQuantity(cartItem.getQuantity());
//                return cartItemRepository.save(cartItem1);
//            }
//
//
//    }
//}