package fpt.edu.capstone.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fpt.edu.capstone.entities.QRCode;
import fpt.edu.capstone.implementService.IQRCodeService;
import fpt.edu.capstone.repo.QRCodeRepository;
import fpt.edu.capstone.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QRCodeService implements IQRCodeService {
    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public QRCode addQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

//    @Override
//    public QRCode saveImageToDB(MultipartFile file, QRCode qrCode){
////        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains("..")){
//            System.out.println("not valid");
//        }
//        try {
//            qrCode.setQRCodeImage2(Base64.getEncoder().encodeToString(file.getBytes()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        qrCode.setQRCodeImage(qrCode.getQRCodeImage());
//        qrCode.setQRCodeLink(qrCode.getQRCodeLink());
//        return qrCodeRepository.save(qrCode);
//    }

//    @Override
//    public QRCode addQRCodeToDB(MultipartFile file, QRCode qrCode) {
////        boolean checkQRCodeExist = qrCodeRepository.checkQRCodeExist(qrCode.getQRCodeImage());
//        try {
//            Map r = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type","auto"));
//            String img = (String) r.get("secure_url");
//            qrCode.setQRCodeImage(img);
//            qrCode.setQRCodeLink("link"+ qrCode.getQRCodeId());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return qrCodeRepository.save(qrCode);
//    }

    @Override
    public QRCode updateQRCode(QRCode qrCode) {
        if(qrCode != null){
            QRCode qrCodeUpdate = qrCodeRepository.getById(qrCode.getQRCodeId());
            if(qrCodeUpdate != null){
//                qrCodeUpdate.setQRCodeImage(qrCode.getQRCodeImage());
                qrCodeUpdate.setQRCodeLink(qrCode.getQRCodeLink());

                return qrCodeRepository.save(qrCodeUpdate);
            }
        }
        return null;
    }

    @Override
    public boolean deleteQRCode(Long id) {
        QRCode qrCode = qrCodeRepository.getById(id);
        if(qrCode != null){
            qrCodeRepository.delete(qrCode);
            return true;
        }
        return false;
    }

    @Override
    public List<QRCode> getAllQRCodes() {
        return qrCodeRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> getQRCodeById(Long id) {
        Optional<QRCode> qrCode = qrCodeRepository.findById(id);
//        byte[] byteData = qrCode.get().getQRCodeImage2().getBytes();
//        Blob blob = null;
//        InputStream in = null;
//        BufferedImage image = null;
//        try {
//            blob = new SerialBlob(byteData);
//            in = blob.getBinaryStream();
//            image = ImageIO.read(in);
//        } catch (SQLException | IOException e) {
//            throw new RuntimeException(e);
//        }
        if(qrCode.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "succsessfully",true, qrCode)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Can not find QRCodeID: "+id,false,"null")
            );
        }
    }

    @Override
    public boolean checkQRCodeExist(String qrCodeLink) {
        QRCode qrCode = qrCodeRepository.checkQRCodeExist(qrCodeLink);
        if(qrCode != null){
            return true;
        }
        return false;
    }

}