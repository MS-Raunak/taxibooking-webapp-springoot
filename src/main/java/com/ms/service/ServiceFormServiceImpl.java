package com.ms.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ms.dao.ServiceFormCrud;
import com.ms.model.ServiceForm;

import jakarta.transaction.Transactional;

@Service
public class ServiceFormServiceImpl implements ServiceFormService{

	@Autowired
	ServiceFormCrud serviceFormCrud;
	
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception {
		ServiceForm saveForm=null;
		try {
			saveForm = serviceFormCrud.save(serviceForm);
			
			if (saveForm!=null) {
				String path = "D:\\JAVA_J2EE\\springboot projects\\taxibooking\\src\\main\\resources\\static\\serviceImg\\" + multipartFile.getOriginalFilename();
				byte[]bytes = multipartFile.getBytes();
				
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(bytes);
			}
			
		} catch (Exception e) {
			saveForm=null;
			throw e;
		}
		return saveForm;
	}

	@Override
	public List<ServiceForm> readAllServices() {
		return serviceFormCrud.findAll();
	}
	
}
